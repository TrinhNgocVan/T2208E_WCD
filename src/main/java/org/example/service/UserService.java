package org.example.service;

import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.UserSearchRequestDto;
import org.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    User findById(long id);
    List<User> saveAll(List<UserDto> users);
    PageDto search();
    PageDto search(UserDto userDto);

    //  for lab1
    PageDto search_lab1(UserSearchRequestDto criteria);

}
