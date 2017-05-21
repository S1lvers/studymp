package com.studymp.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by qwerty on 19.05.2017.
 */
public class OrderDto {

    @JsonProperty("id")
    public Long id;

    @JsonProperty("name")
    public String name;

    @JsonProperty("description")
    public String description;

    @JsonProperty("section")
    public String section;

    @JsonProperty("cost")
    public Double cost;

}
