package com.studymp.persistence.entity;

/**
 * Created by qwerty on 11.04.2017.
 */
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "chatroom")
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(cascade = CascadeType.REMOVE)
    private List<ChatMessage> chatMessages;

    @Column(name = "uniqueUsersChatKey")
    private String uniqueUsersChatKey;

    public ChatRoom() {

    }

    public ChatRoom(String uniqueUsersChatKey) {
        this.uniqueUsersChatKey = uniqueUsersChatKey;
    }

    public String getUniqueUsersChatKey() {
        return uniqueUsersChatKey;
    }

    public void setUniqueUsersChatKey(String uniqueUsersChatKey) {
        this.uniqueUsersChatKey = uniqueUsersChatKey;
    }

    public Long getId() {
        return id;
    }

    public List<ChatMessage> getChatMessages() {
        return chatMessages;
    }


}