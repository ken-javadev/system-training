package com.training.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneralResponse<T> {
    private Integer code;
    private T data;
    private String status;

    public GeneralResponse(Integer code, T data, String status) {
        this.code = code;
        this.data = data;
        this.status = status;
    }

    public GeneralResponse() {
    }
}
