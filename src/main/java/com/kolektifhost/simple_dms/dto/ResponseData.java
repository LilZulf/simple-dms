/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.kolektifhost.simple_dms.dto;

/**
 *
 * @author najib
 */
public class ResponseData<T> {
    private boolean status;
    private String message;
    private T data;
    private int code;

    public ResponseData(boolean status, String message, int code, T data) {
        this.status = status;
        this.message = message;
        this.code = code;
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }   

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {

        return data;
    }   

    public void setData(T data) {
        this.data = data;
    }   

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }   
}
