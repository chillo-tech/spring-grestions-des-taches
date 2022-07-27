package com.gdt.dto;

import com.gdt.enums.ErrorsCode;

public class ErrorDTO {
    private ErrorsCode code;
    private String message;

    public ErrorDTO() {
    }

    public ErrorDTO(ErrorsCode code, String message) {
        this.code = code;
        this.message = message;
    }

    public ErrorsCode getCode() {
        return code;
    }

    public void setCode(ErrorsCode code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
