package com.bridgelabz.bookstoreproject.DTO;

import java.util.List;

public class ResponseDTO {
    private String message;
    private Object data;

    public ResponseDTO() {
    }

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
