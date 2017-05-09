package com.studymp.persistence.entity;

import javax.persistence.*;

/**
 * Created by qwerty on 07.05.2017.
 */
@Entity
@Table(name = "responders")
public class Responder {
    @Id
    @Column(name = "responder_id", nullable = false)
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User responder;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public User getResponder() {
        return responder;
    }

    public void setResponder(User responder) {
        this.responder = responder;
    }

    public Long getId() {
        return id;
    }

    public Responder(Order order, User responder) {
        this.order = order;
        this.responder = responder;
    }

    public Responder() {
    }
}
