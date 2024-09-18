package com.example.identity_service.exception;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    USER_EXISTED (1001, "Username existed", HttpStatus.BAD_REQUEST ),
    USER_NOT_EXISTED (1005, "User not existed", HttpStatus.NOT_FOUND),
    UNCATEGORIED_EXCEPTION (1002, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    USERNAME_INVALID (1003, "Username must have at least {min} characters", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID (1004, "Password must have at least {min} characters", HttpStatus.BAD_REQUEST),
    KEY_INVALID (1007, "Invalid message key", HttpStatus.BAD_REQUEST),
    UNAUTHENTICATED (1006, "Unauthenticated", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED (1008, "You do not have permission", HttpStatus.FORBIDDEN),
    DOB_INVALID (1009, "Your age must be at least {min}", HttpStatus.BAD_REQUEST),
    EMAIL_EXISTED (1010, "Username existed", HttpStatus.BAD_REQUEST ),
    PHONE_EXISTED (1011, "Username existed", HttpStatus.BAD_REQUEST ),
    OLD_PASSWORD_INVALID (1012, "Your current password is invalid", HttpStatus.BAD_REQUEST),
    ;
    private int code;
    private String messgae;
    private HttpStatusCode statusCode;

    ErrorCode(int code, String messgae, HttpStatusCode statusCode){
        this.code = code;
        this.messgae = messgae;
        this.statusCode = statusCode;
    }

}
