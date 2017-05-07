package com.studymp.persistence.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qwerty on 07.05.2017.
 */
@Entity
@Table(name = "order")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false)
    @GeneratedValue
    private Long id;

    @Column(name = "order_name", nullable = false)
    private String name;

    @Column(name = "order_create_date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "order_description")
    private String description;

    @Column(name = "order_price", nullable = false)
    private double price;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User executor;

    @Column(name = "order_archive_date")
    private Date archive;

    public Order() {
    }

    public Order(String name, Date date, User creator, Section section, String description, double price) {
        this.name = name;
        this.date = date;
        this.creator = creator;
        this.section = section;
        this.description = description;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Section getSection() {
        return section;
    }

    public void setSection(Section section) {
        this.section = section;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Date getArchive() {
        return archive;
    }

    public void setArchive(Date archive) {
        this.archive = archive;
    }

    public Long getId() {
        return id;
    }
}
