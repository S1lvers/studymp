package com.studymp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qwerty on 22.03.2017.
 */
public class ChangePasswordDto {
    @JsonProperty("hash")
    public String hash;

    @JsonProperty("password")
    public String password;
}
