package com.toy.pbpost.common.exception;

import com.toy.pbpost.common.exception.feign.FeignClientException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;

@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler {

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final String value = e.getValue() == null ? "" : e.getValue().toString();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(e.getName(), value, e.getErrorCode());
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.INVALID_TYPE_VALUE, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    protected ResponseEntity<ErrorResponse> handleEntityNotFoundException(RuntimeException e) throws IOException {
        log.error("handleEntityNotFoundException", e);
        final String message = e.getMessage() == null ? "데이터를 찾을 수 없음" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.ENTITY_NOT_FOUND, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { IllegalArgumentException.class, IllegalStateException.class })
    protected ResponseEntity<ErrorResponse> handleIllegalArgumentException(RuntimeException e) throws IOException {
        log.error("handleIllegalArgumentException", e);
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.ENTITY_NOT_FOUND, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { ProcessException.class})
    protected ResponseEntity<ErrorResponse> handleProcessException(RuntimeException e) {
        log.error("ProcessException : "  +  e.getMessage());
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.PROCESS_ERROR, errors);
        return new ResponseEntity<>(response, HttpStatus.GONE);
    }

    @ExceptionHandler(value = { FeignClientException.class})
    protected ResponseEntity<ErrorResponse> handleFeignClientException(FeignClientException e) {
        log.error("ProcessException : "  +  e.getMessage());
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.INTERNAL_SERVER_ERROR, errors);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = { TimeIsExpiredException.class })
    protected ResponseEntity<ErrorResponse> handleTimeIsExpiredException(RuntimeException e) throws IOException {
        log.error("handleIllegalArgumentException", e);
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.HANDLE_ACCESS_DENIED, errors);
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = { DuplicateKeyException.class })
    protected ResponseEntity<ErrorResponse> handleDuplicateKeyExceptionException(RuntimeException e) throws IOException {
        log.error("handleIllegalArgumentException", e);
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.DUPLICATE_KEY, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = { javax.validation.ConstraintViolationException.class })
    protected ResponseEntity<ErrorResponse> validationException(RuntimeException e) throws IOException {
        final String message = e.getMessage() == null ? "" : e.getMessage();
        final List<ErrorResponse.FieldError> errors = ErrorResponse.FieldError.of(null, null, message);
        final ErrorResponse response = ErrorResponse.of(ErrorMessage.INVALID_INPUT_VALUE, errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
