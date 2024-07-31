package org.example.controller;


import org.example.dto.PageDto;
import org.example.dto.UserDto;
import org.example.dto.lab1.user.UserSearchRequestDto;
import org.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class Lab1Controller {
    @Autowired
    UserService userService;

    @PostMapping("/users/_search_lab1")
    public ResponseEntity<?> search(@RequestBody UserSearchRequestDto criteria){
        return ResponseEntity.ok(userService.search_lab1(criteria));
    }
}
