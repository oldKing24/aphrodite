package com.oldking.user.handler;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.oldking.response.AphroditeResponse;
import com.oldking.response.ResponseCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(value = JWTVerificationException.class)
    @ResponseBody
    public AphroditeResponse checkExceptionHandler(JWTVerificationException e) {
        log.error("", e);

        AphroditeResponse response = new AphroditeResponse();
        response.setCode(ResponseCode.TOKEN_INVALID.getCode());
        response.setMsg(e.getMessage());
        response.setSuccess(false);
        return response;
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseBody
    public AphroditeResponse checkExceptionHandler(IllegalArgumentException e) {
        log.error("", e);
        AphroditeResponse response = new AphroditeResponse();
        response.setCode(ResponseCode.PARAMETER_INVALID.getCode());
        response.setMsg(e.getMessage());
        response.setSuccess(false);
        return response;
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public AphroditeResponse validatedBindException(BindException e) {
        log.error(e.getMessage(), e);
        AphroditeResponse response = new AphroditeResponse();
        String message = e.getAllErrors().stream().map(DefaultMessageSourceResolvable
                ::getDefaultMessage).collect(Collectors.joining(";"));
        response.setCode(ResponseCode.PARAMETER_INVALID.getCode());
        response.setMsg(message);
        response.setSuccess(false);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public AphroditeResponse handlerCheckException(Exception e) {
        log.error("", e);

        AphroditeResponse response = new AphroditeResponse();
        response.setCode(ResponseCode.SYSTEM_ERROR.getCode());
        response.setMsg(ResponseCode.SYSTEM_ERROR.getMessage());
        response.setSuccess(false);
        return response;
    }
}
