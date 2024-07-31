package org.example.specification;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.UserSearchRequestDto;
import org.example.entity.Company;
import org.example.entity.Department;
import org.example.entity.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserSpecification {
    public Specification<User> filter(UserDto criteria){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if(!StringUtils.isEmpty(criteria.getUsername())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("username")),
                        "%"+ criteria.getUsername().toUpperCase() + "%"));
            }
            if(!StringUtils.isEmpty(criteria.getFirstname())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("firstname")),
                        "%"+ criteria.getFirstname().toUpperCase() + "%"));
            }
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
    public Specification<User> filterLab1(UserSearchRequestDto criteria){
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Join<User, Department> departmentRoot = root.join("department");
            Join<Department, Company> companyRoot = root.join("company");


            if(!StringUtils.isEmpty(criteria.getUsername())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("username")),
                        "%"+ criteria.getUsername().toUpperCase() + "%"));
            }
            if(!StringUtils.isEmpty(criteria.getAddress())){
                // menh de : like  , ko phan biet hoa thuong
                predicates.add( cb.like(cb.upper(root.get("address")),
                        "%"+ criteria.getAddress().toUpperCase() + "%"));
            }
            return cb.and(predicates.stream().toArray(Predicate[]::new));
        };
    }
}
