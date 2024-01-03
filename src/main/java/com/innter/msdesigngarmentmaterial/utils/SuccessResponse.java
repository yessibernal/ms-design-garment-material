package com.innter.msdesigngarmentmaterial.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse {
    private int code;
    private Object data;
    private String message;
    private String UUID;
}
