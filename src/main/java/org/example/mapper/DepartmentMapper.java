package org.example.mapper;

import org.example.dto.DepartmentDto;
import org.example.entity.Department;
import org.example.entity.User;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

public class DepartmentMapper {
    public static DepartmentDto entityToDto(Department department){
        DepartmentDto dto = new DepartmentDto();
        BeanUtils.copyProperties(department,dto);
        List<User> users = department.getUsers();
        dto.setUserDto(users.stream().map(UserMapper::entityToDto).collect(Collectors.toList()));
        return dto;
    }
}
