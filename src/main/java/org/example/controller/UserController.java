package org.example.controller;

import org.example.dto.DepartmentDetailProjection;
import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.repository.DepartmentRepositoty;
import org.example.repository.UserRepository;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentRepositoty departmentRepositoty;
    @GetMapping("/users/{id}")
    public ResponseEntity<?> get(){
//        List<DepartmentDetailProjection> deparmentProjectionList
//                = departmentRepositoty.getDepartcmdmentDetail();
//        return ResponseEntity.ok(deparmentProjectionList);
        List<UserDto> userDtos = new ArrayList<>();
        for (int i = 0; i < 1000000; i++) {
            UserDto userDto = new UserDto();
            userDto.setUsername("test"+i);
            userDtos.add(userDto);
        }
        userService.saveAll(userDtos);
        return ResponseEntity.ok(null);
    }
    @PostMapping("/users/_search")
    public PageDto search(@RequestBody  UserDto userDto){
        return userService.search(userDto);
    }
    @PostMapping("/user")
    public PageDto add(@RequestBody  UserDto userDto){
        return userService.search(userDto);
    }

    @PutMapping("/user")
    public PageDto update(@RequestBody  UserDto userDto){
        return userService.search(userDto);
    }
    @DeleteMapping("/user")
    public PageDto delete(@RequestBody  UserDto userDto){
        return userService.search(userDto);
    }
}
