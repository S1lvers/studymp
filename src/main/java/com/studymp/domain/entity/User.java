package com.studymp.domain.entity;

import org.springframework.boot.orm.jpa.EntityScan;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by qwerty on 13.03.2017.
 */
@Entity
@Table(name = "users", schema = "studymp")
public class User {
}
