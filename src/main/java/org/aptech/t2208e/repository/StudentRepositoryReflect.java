package org.aptech.t2208e.repository;

import org.aptech.t2208e.entity.Student;
import org.aptech.t2208e.jpaRepository.main.impl.JpaExecutorImpl;

public class StudentRepositoryReflect extends JpaExecutorImpl<Student> {

    public StudentRepositoryReflect(Class<Student> clazz) {
        super(clazz);
    }

    public static void main(String[] args) {
        StudentRepositoryReflect s = new StudentRepositoryReflect(Student.class);
        s.findAll();
    }
}
