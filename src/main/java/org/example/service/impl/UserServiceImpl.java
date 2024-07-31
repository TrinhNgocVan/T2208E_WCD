package org.example.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.config.properties.CommonProperties;
import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.UserSearchRequestDto;
import org.example.entity.Department;
import org.example.entity.User;
import org.example.mapper.UserMapper;
import org.example.repository.DepartmentRepositoty;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.example.specification.UserSpecification;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

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
    @Override
    public PageDto search_lab1(UserSearchRequestDto criteria) {
        if(criteria.getPageSize() <= 0 || criteria.getPageNumber() < 0){
            criteria.setPageNumber(commonProperties.getDefaultPageNumber());
            criteria.setPageSize(commonProperties.getDefaultPageSize());
        }
        Page<User> page = userRepository.findAll(userSpecification.filterLab1(criteria),
                PageRequest.of(criteria.getPageNumber(),criteria.getPageSize(),
                        Sort.by("id").ascending()));


        return null;
    }
}
