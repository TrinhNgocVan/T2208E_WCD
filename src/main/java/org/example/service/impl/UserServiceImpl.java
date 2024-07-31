package org.example.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.config.properties.CommonProperties;
import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.PersistUserDto;
import org.example.dto.lab1.user.UserSearchRequestDto;
import org.example.dto.lab1.user.UserSearchResponseDto;
import org.example.entity.Company;
import org.example.entity.Corporation;
import org.example.entity.Department;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.DepartmentRepositoty;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.specification.UserSpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private DepartmentRepositoty departmentRepositoty;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSpecification userSpecification;
    @Autowired
    private CommonProperties commonProperties;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public List<User> saveAll(List<UserDto> userDtos) {
        // normal  , java 7 before
//        userRepository.saveAllAndFlush(userDtos
//                .stream()
//                .map(UserMapper::dtoToEntity)
//                .toList()
//        );
        Session session = entityManager.unwrap(Session.class)
                .getSessionFactory()
                .openSession();
        Transaction tx = session.beginTransaction();
        String sql  = "insert into user(user_name) values(?);";
        int batchSize = 1000;
        session.doWork(conn -> {
            PreparedStatement pt  = conn.prepareStatement(sql);
            int i  = 0;
            List<UserDto> currentBatchUser = new ArrayList<>();
            for(UserDto u : userDtos){
                i++;
                currentBatchUser.add(u);
                if(i == batchSize){
                    // insert batch
                    for(UserDto user : currentBatchUser){
                        pt.setString(1, user.getUsername());
                        pt.addBatch();
                    }
                    pt.executeBatch();
                    currentBatchUser.clear();
                    session.flush();
                    session.clear();
                }
            }
            if(i > 0 ){
                for(UserDto user : currentBatchUser){
                    pt.setString(1, user.getUsername());
                    pt.addBatch();
                }
                pt.executeBatch();
                session.flush();
                session.clear();
            }
        });
        tx.commit();
        session.close();

        return new ArrayList<>();

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public PageDto search() {
        UserDto dto =  new UserDto();
//        dto.setPageNumber(commonProperties.getDefaultPageNumber());
//        dto.setPageSize(commonProperties.getDefaultPageSize());
        return search(dto);
    }

    @Override
    public PageDto search(UserDto dto) {
        if(dto.getPageSize() <= 0 || dto.getPageNumber() < 0){
            dto.setPageNumber(commonProperties.getDefaultPageNumber());
            dto.setPageSize(commonProperties.getDefaultPageSize());
        }
        Page<User> page = userRepository.findAll(userSpecification.filter(dto),
                PageRequest.of(dto.getPageNumber(),dto.getPageSize(),
                        Sort.by("id").ascending()));
        // findAll  = select , count
        PageDto pageDto = new PageDto();
        pageDto.setContent(page.getContent()
                .stream()
                .map(UserMapper::entityToDto)
                .collect(Collectors.toList()));
        pageDto.setTotalElements(page.getTotalElements());
        pageDto.setNumber(page.getNumber());
        pageDto.setNumberOfElements(page.getNumberOfElements());
        pageDto.setPageNumber(page.getNumber());
        pageDto.setPageSize(page.getSize());
        pageDto.setTotalPages(page.getTotalPages());
        return pageDto;
    }
    public void update(PersistUserDto u , long userId){
        // validate not change username when edit user

        Optional<User> currentUser = userRepository.findById(userId);
        if(!currentUser.isPresent()){
            // not valid

        }



        // edit : user must have id
        // user  vs department : user update u.department
        User user  = new User();
        Department department = new Department();
        department.setId(u.getDepartmentId());
        user.setDepartment(department);
        BeanUtils.copyProperties(u,user);
        userRepository.save(user);
    }
    public void insert(PersistUserDto u){
        // edit : user must have id
        // user  vs department : user update u.department
        User user  = new User();
        Department department = new Department();
        department.setId(u.getDepartmentId());
        user.setDepartment(department);
        BeanUtils.copyProperties(u,user);
        userRepository.save(user);
    }
    @Override
    public PageDto search_lab1(UserSearchRequestDto criteria) {
        if(criteria.getPageSize() <= 0 || criteria.getPageNumber() < 0){
            criteria.setPageNumber(commonProperties.getDefaultPageNumber());
            criteria.setPageSize(commonProperties.getDefaultPageSize());
        }
        Page<User> page = userRepository.findAll(userSpecification.filterLab1(criteria),
                PageRequest.of(criteria.getPageNumber(),criteria.getPageSize(),
                        Sort.by("id").ascending()));
        List<User> users = page.getContent();
        // todo  : map list user entity -> list userSearchReponseDto

        List<UserSearchResponseDto> userSearchResponseDtos = new ArrayList<>();
        for (User u: users){
            // add to list
            userSearchResponseDtos.add(getUserSearchResponse(u.getId(), u));
        }
        PageDto pageDto = new PageDto();
        pageDto.setContent(userSearchResponseDtos);
        pageDto.setPageNumber(page.getNumber());
        pageDto.setPageSize(page.getSize());
        return pageDto;
    }
    // from userId , get from cache
//    @Cacheable(value = "T2208e", key = "{'userInfo', #userId}", unless = "null")
    public UserSearchResponseDto getUserSearchResponse(long userId, User u){
        UserSearchResponseDto res = new UserSearchResponseDto();
        redisTemplate.opsForValue().set(userId, userId);
        // set common field : id , username , address
        res.setId(u.getId());
        res.setAddress(u.getAddress());
        res.setUsername(u.getUsername());
        // set field from another table : departmentName , companyName , coporationName
        Department d = u.getDepartment();
        res.setDepartmentName(d.getName());
        Company c = d.getCompany();
        res.setCompanyName(c.getName());
        Corporation corp = c.getCorporation();
        res.setCorporationName(corp.getName());
        return res;
    }
}
