package org.example.controller;

import org.example.dto.DepartmentDto;
import org.example.dto.UserDto;
import org.example.service.impl.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CacheController {
    @Autowired
    private CacheService cacheService;
    @GetMapping("/itemCache")
    public ResponseEntity<?> get() throws InterruptedException {
        return ResponseEntity.ok(cacheService.getCacheDepartment(1L));
    }
    @GetMapping("/clearCache")
    public ResponseEntity<?> clearCache() throws InterruptedException {
        return ResponseEntity.ok(cacheService.updateDepartment(new DepartmentDto(),1L));
    }
}
