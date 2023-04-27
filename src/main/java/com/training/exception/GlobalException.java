package com.training.exception;

import com.training.config.variable.AppConstant;
import com.training.model.response.GeneralResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalException extends ResponseEntityExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity<GeneralResponse> handleException(Exception e) {
        e.printStackTrace();
        GeneralResponse commonRs = new GeneralResponse();
        commonRs.setCode(500);
        commonRs.setStatus(AppConstant.MESSAGE_FAILED);
        return new ResponseEntity<GeneralResponse>(commonRs, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler({CustomNotfoundException.class})
    public ResponseEntity<GeneralResponse> handleNotFoundException(Exception e) {
        e.printStackTrace();
        GeneralResponse commonRs = new GeneralResponse();
        commonRs.setCode(404);
        commonRs.setStatus(e.getMessage());
        return new ResponseEntity<GeneralResponse>(commonRs, HttpStatus.NOT_FOUND);

    }
}
