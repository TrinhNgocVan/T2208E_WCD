package org.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
public class DepartmentDto implements Serializable {
    private long id;
    private String name;
    private List<UserDto> userDto;
}
