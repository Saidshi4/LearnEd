package com.example.learned.model;


import lombok.Data;

@Data
public class DataResult<T>{
    private String message;
    private Integer responseCode;
    private T data;

    public DataResult(String message, Integer responseCode, T data) {
        this.message = message;
        this.responseCode = responseCode;
        this.data = data;
    }
}

