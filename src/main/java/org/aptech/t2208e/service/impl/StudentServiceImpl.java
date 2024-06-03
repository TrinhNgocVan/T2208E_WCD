package org.aptech.t2208e.service.impl;

import org.aptech.t2208e.dto.StudentDto;
import org.aptech.t2208e.entity.Student;
import org.aptech.t2208e.mapper.StudentMapper;
import org.aptech.t2208e.mapper.impl.StudentMapperImpl;
import org.aptech.t2208e.repository.StudentRepository;
import org.aptech.t2208e.repository.impl.StudentRepositoryImpl;
import org.aptech.t2208e.service.StudentService;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl  implements StudentService {

    // create instance of StudentRepositoryImpl --> not work
    StudentRepository studentRepository = new StudentRepositoryImpl();
    StudentMapper studentMapper = new StudentMapperImpl();
    // StudentService >>> StudentRepository
    // instead of create instance -> create instance somewhere , inject to class depended

    @Override
    public StudentDto getById(Long id) {
        Optional<List<Student>> optionalStudents =  studentRepository.getById(String.valueOf(id));
        if(optionalStudents.isPresent()){
            List<Student> students = optionalStudents.get();
            if(!CollectionUtils.isEmpty(students)){
                return studentMapper.entityToDto(students.get(0));
            }
        }
        return null;
    }
}
