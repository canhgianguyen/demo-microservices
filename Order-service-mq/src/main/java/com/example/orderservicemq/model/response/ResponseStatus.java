package com.example.orderservicemq.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseStatus {
    @JsonProperty("code")
    private String code;

    @JsonProperty("message")
    private String message;
}
