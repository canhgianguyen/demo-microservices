package com.example.orderservice.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralResponse<T> {
    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("data")
    private T data;
}
