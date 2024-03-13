package com.example.learned.model;


import lombok.Data;

@Data
public class DataResult<T>{
    private Integer status;
    private String message;
    private T data;

    public DataResult(String message, Integer status, T data) {
        this.message = message;
        this.status = status;
        this.data = data;
    }
}

