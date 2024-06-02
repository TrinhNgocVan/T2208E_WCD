package org.aptech.t2208e.repository.impl;

import org.aptech.t2208e.entity.Student;
import org.aptech.t2208e.repository.StudentRepo;
import org.aptech.t2208e.utils.ConnectionPool;
import org.aptech.t2208e.utils.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepositoryImpl  implements StudentRepo {
    private static final String SQL_QUERY_STUDENT_BY_ID = "Select * from student where id  = ?";

    public static void main(String[] args) {
        StudentRepo studentRepo = new StudentRepositoryImpl();
        Optional<List<Student>> s = studentRepo.getById("'1' or 0 =0 ");
        if(s.isPresent()){
            System.err.println("Student with id 1 : "+ s.get());
        }
    }
    @Override
    public Optional<List<Student>> getById(String id) {
//        Connection connection = DatabaseHelper.getConnection();
        List<Student> students = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        Connection con = connectionPool.getConnection();
        try {
            // fixme  : sql injection
            PreparedStatement pt = con.prepareStatement(SQL_QUERY_STUDENT_BY_ID);
            pt.setString(1,String.valueOf(id));
            ResultSet rs = pt.executeQuery();
//            Statement statement = con.createStatement();
//            ResultSet rs = statement.executeQuery(SQL_QUERY_STUDENT_BY_ID.replace("?"
//                    ,String.valueOf(id)));
            while (rs.next()){
                Student s = new Student();
                s.setId(rs.getLong("id"));
                s.setFirstName(rs.getString("first_name"));
                s.setLastName(rs.getString("last_name"));
                s.setAddress(rs.getString("address"));
                s.setAge(rs.getInt("age"));
                students.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  Optional.of(students);
    }

    @Override
    public List<Student> getAll() {
        return List.of();
    }
    // extend vs implement
}
