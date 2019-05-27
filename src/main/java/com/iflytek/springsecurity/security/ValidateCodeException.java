package com.iflytek.springsecurity.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author cool
 * @version V1.0
 * @className ValidateCodeException
 * @description Code Is Poetry.
 * @createDate 2019年05月22日
 */
public class ValidateCodeException extends AuthenticationException {


    private static final long serialVersionUID = 1L;

    public ValidateCodeException(String msg) {
        super(msg);
    }

}
