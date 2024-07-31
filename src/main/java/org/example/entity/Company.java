package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "company")
public class Company {
    // name  , 1-n department
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private List<Department> departments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "corporation_id", nullable = true)
    private Corporation corporation;


    public Corporation getCorporation() {
        return corporation;
    }

    public void setCorporation(Corporation corporation) {
        this.corporation = corporation;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
