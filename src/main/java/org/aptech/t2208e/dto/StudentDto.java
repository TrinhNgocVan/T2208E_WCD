package org.aptech.t2208e.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private int age;
}
