package com.studymp.persistence.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by qwerty on 11.04.2017.
 */
@Entity
@Table(name = "chatmessage")
public class ChatMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "text")
    private String text;

    @ManyToOne
    private User author;

    @ManyToOne
    private User destination;

    @ManyToOne
    private ChatRoom chatRoom;

    @Column
    private Date createDate;

    public ChatMessage() {

    }
    public ChatMessage(String text, User author, User destination, ChatRoom chatRoom, Date createDate) {
        this.text = text;
        this.author = author;
        this.chatRoom = chatRoom;
        this.createDate = createDate;
        this.destination = destination;
    }

    public ChatRoom getChatRoom() {
        return chatRoom;
    }

    public void setChatRoom(ChatRoom chatRoom) {
        this.chatRoom = chatRoom;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public User getCompanion() {
        return destination;
    }

    public void setCompanion(User companion) {
        this.destination = companion;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", author=" + author.getUsername() +
                ", companion=" + destination.getUsername() +
                ", createDate=" + createDate +
                '}';
    }
}
