package org.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "course" )
public class Course {
    // user vs  course  : many to many
    @Id
    Long id;
    @ManyToMany(mappedBy = "courses")
    List<User> users;
}
