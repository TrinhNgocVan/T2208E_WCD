package org.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "department" )
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;   // note  : Long  != long
    @Column(name = "name")
    private String name;
    @OneToMany( mappedBy = "department",fetch = FetchType.LAZY



            , cascade = CascadeType.ALL)
    // fetchType  : LAZY (load khi nao chuong trinh goi ) , EAGER (load ngay lap tuc )
    private List<User> users;  // 1 object department have many user inside

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
