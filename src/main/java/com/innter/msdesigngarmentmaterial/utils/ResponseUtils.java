package com.innter.msdesigngarmentmaterial.utils;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;
@Component
public class ResponseUtils {
    public SuccessResponse successResponseOK(Object data, String message) {
        SuccessResponse responseSuccess = new SuccessResponse();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setData(data);
        responseSuccess.setMessage(message);
        responseSuccess.setUUID(UUID.randomUUID().toString());
        return responseSuccess;
    }

    public SuccessResponse successResponseCreate(Object data, String message) {
        SuccessResponse responseSuccess = new SuccessResponse();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setData(data);
        responseSuccess.setMessage(message);
        responseSuccess.setUUID(UUID.randomUUID().toString());
        return responseSuccess;
    }

    public SuccessResponse successResponseOKVoid(String message) {
        SuccessResponse responseSuccess = new SuccessResponse();
        responseSuccess.setCode(HttpStatus.OK.value());
        responseSuccess.setMessage(message);
        responseSuccess.setUUID(UUID.randomUUID().toString());
        return responseSuccess;
    }
}
