package com.studymp.persistence.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.DoubleSummaryStatistics;

/**
 * Created by qwerty on 07.05.2017.
 */
@Entity
@Table(name = "studymp_order")
public class Order {
    @Id
    @Column(name = "order_id", nullable = false, unique = true)
    @GeneratedValue
    private Long id;

    @Column(name = "order_name", nullable = false)
    private String name;

    @Column(name = "order_create_date")
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User creator;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    private Section section;

    @Column(name = "order_description")
    private String description;

    @Column(name = "order_price")
    private Double price;

    @ManyToOne
    private User executor;

    @Column(name = "order_archive_date")
    private Date archiveDate;

    public Order() {
    }

    public Order(String name, Date createDate, User creator, Section section, String description, Double price) {
        this.name = name;
        this.createDate = createDate;
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
        return createDate;
    }

    public void setDate(Date createDate) {
        this.createDate = createDate;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public User getExecutor() {
        return executor;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public Date getArchive() {
        return archiveDate;
    }

    public void setArchive(Date archiveDate) {
        this.archiveDate = archiveDate;
    }

    public Long getId() {
        return id;
    }
}
