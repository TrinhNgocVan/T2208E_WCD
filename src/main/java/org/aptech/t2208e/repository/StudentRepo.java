package org.aptech.t2208e.repository;

import org.aptech.t2208e.entity.Student;

import java.util.List;
import java.util.Optional;

public interface  StudentRepo {
    Optional<List<Student>> getById(String id);
    List<Student> getAll();

}
