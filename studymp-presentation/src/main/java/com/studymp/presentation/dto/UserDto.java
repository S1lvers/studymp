package com.studymp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qwerty on 14.03.2017.
 */
public class UserDto {
    @JsonProperty("email")
    public String email;

    @JsonProperty("password")
    public String password;

    @JsonProperty("username")
    public String username;

    @JsonProperty("enabled")
    public boolean enabled;
}
