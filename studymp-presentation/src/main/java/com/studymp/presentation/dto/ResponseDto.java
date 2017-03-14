package com.studymp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qwerty on 14.03.2017.
 */
public class ResponseDto<T> {
    @JsonProperty("body")
    public T body;
    @JsonProperty("status")
    public Boolean status;
    @JsonProperty("error")
    public String error;
}
