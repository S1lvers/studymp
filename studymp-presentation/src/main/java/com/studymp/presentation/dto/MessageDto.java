package com.studymp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qwerty on 17.04.2017.
 */
public class MessageDto {
    @JsonProperty("text")
    public String text;

    @JsonProperty("Destination")
    public String destinationUsername;
}
