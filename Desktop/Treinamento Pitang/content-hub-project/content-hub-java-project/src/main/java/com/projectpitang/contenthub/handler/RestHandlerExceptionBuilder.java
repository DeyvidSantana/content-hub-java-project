package com.projectpitang.contenthub.handler;

import com.projectpitang.contenthub.error.ResourceNotFoundException;
import com.projectpitang.contenthub.error.ResourceNotFoundExceptionDetail;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestHandlerExceptionBuilder {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handlerResourceNotFoundException(ResourceNotFoundException resourceNotFoudException) {
        ResourceNotFoundExceptionDetail resourceNotFoundDetails = ResourceNotFoundExceptionDetail.ResourceBuilder.builder()
                .title("The resource wasn't found !")
                .detail(resourceNotFoudException.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .developerMessage(resourceNotFoudException.getClass().getName()).build();

        return new ResponseEntity<>(resourceNotFoundDetails, HttpStatus.NOT_FOUND);

    }

}
