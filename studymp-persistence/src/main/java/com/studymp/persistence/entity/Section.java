package com.studymp.persistence.entity;

import javax.persistence.*;

/**
 * Created by qwerty on 07.05.2017.
 */
@Entity
@Table(name = "section")
public class Section {
    @Id
    @Column(name = "section_id", nullable = false, unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "section_name")
    private String name;

    @Column(name = "description")
    private String description;

    public Section(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Section() {
    }

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
