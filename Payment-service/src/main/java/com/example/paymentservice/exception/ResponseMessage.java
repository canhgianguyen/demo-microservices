package com.example.paymentservice.exception;

import com.example.paymentservice.model.response.ResponseStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
    @JsonProperty("status")
    private ResponseStatus status;

    @JsonProperty("data")
    private Object data;
}
