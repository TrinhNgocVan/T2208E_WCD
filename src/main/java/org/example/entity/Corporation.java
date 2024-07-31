package org.example.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "corporation")
public class Corporation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long  id;
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "corporation", fetch = FetchType.LAZY)
    private List<Company> companies;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Company> getCompanies() {
        return companies;
    }

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
