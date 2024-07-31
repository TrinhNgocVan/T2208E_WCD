package org.example.service.impl;


import org.example.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class CacheService {
    // solution : if you only get value  , get from cache
    // if update data  -> clear or update cache
    // @Cacheable  : if value not have in cache  -> get from db  .... ,
    // if value exited in cache -> get from cache
    @Autowired
    RedisTemplate redisTemplate;

    @Cacheable(value = "T2208e:dihoc", key = "{'test', #departmentId}", unless = "null")
    public DepartmentDto getCacheDepartment(long departmentId)
            throws InterruptedException {
//        // mock get department from db : select * from department where id  = :id
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(departmentId);
        departmentDto.setName("Demo");
        Thread.sleep(5000);
        redisTemplate.opsForValue().set("test","test"); // TTL = -1 (no_expire)
        return departmentDto;
    }
    // 1. if update real value  -> update cache (% wrong data)
    // 2. if update real value  -> clear cache  (100%)

    @CacheEvict(value = "T2208e", key = "{'test', #id}")
    public DepartmentDto updateDepartment(DepartmentDto department, long id) throws InterruptedException {
        // update department
        Thread.sleep(5000);
        return department;
    }
}
