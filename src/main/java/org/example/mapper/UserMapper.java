package org.example.mapper;

import org.example.dto.DepartmentDto;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.UserSearchResponseDto;
import org.example.entity.Company;
import org.example.entity.Corporation;
import org.example.entity.Department;
import org.example.entity.User;
import org.springframework.beans.BeanUtils;

public class UserMapper {
    public static UserDto entityToDto(User user){
        UserDto dto = new UserDto();
        BeanUtils.copyProperties(user, dto);
        DepartmentDto departmentDto = DepartmentMapper.entityToDto(user.getDepartment());
        // entity get model quan he  -> hibernate thuc thi truy van cai bang quan he do
        dto.setDepartment(departmentDto);
        return dto;
    }
    public static UserSearchResponseDto entityToDtoLab1(User user){
        UserSearchResponseDto dto = new UserSearchResponseDto();
        dto.setUsername(user.getUsername());
        dto.setAddress(user.getAddress());
        Department department = user.getDepartment();
        Company company = department.getCompany();
        Corporation corporation = company.getCorporation();
        dto.setDepartmentName(department.getName());
        dto.setCompanyName(company.getName());
        dto.setCorporationName(corporation.getName());
        return dto;
    }
    public static User dtoToEntity(UserDto dto){
        User user = new User();
        BeanUtils.copyProperties(dto, user);
        return user;
    }
}
