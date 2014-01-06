package edu.npu.library.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.npu.library.domain.Error;
import edu.npu.library.exception.DomainException;

/**
 * Exception handler to handle exceptions and send errors accordingly
 * @author Neeta Vekariya
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
 
	private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);
	
	@Autowired
	private MessageSource messageSource;

	/**
	 * Handles customized DomainException
	 * @param ex
	 * @return error object that sends human readable exception message
	 */
	@ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    Error handleException(DomainException ex) {
        Error errorMessage = new Error(ex.getMessage());
        return errorMessage;
    }
    
	/**
	 * Overridden method, that handles field/object validation errors
	 * converts errors into customized Error object
	 * and returns the same
	 */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	Locale currentLocale =  LocaleContextHolder.getLocale();
    	logger.info("execute handleMethodArgumentNotValid--Locale:"+currentLocale);
    	
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<String>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
        	error = messageSource.getMessage(fieldError, currentLocale);
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
        	error = messageSource.getMessage(objectError, currentLocale);
            errors.add(error);
        }
        Error errorMessage = new Error(errors);
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
 
    /**
	 * Overridden method, that invalid media type errors for given request
	 * converts errors into customized Error object
	 * and returns the same
	 */
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	logger.info("execute handleHttpMediaTypeNotSupported");
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());
        Error errorMessage = new Error(unsupported, supported);
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }
 
    /**
	 * Overridden method, that handles errors that generated while reading request data into given format
	 * converts errors into customized error object
	 * and returns the same
	 */
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
    	logger.info("execute handleHttpMessageNotReadable");
        Throwable mostSpecificCause = ex.getMostSpecificCause();
        Error errorMessage;
        Locale currentLocale =  LocaleContextHolder.getLocale();
    	
        String userMessage = messageSource.getMessage("invalid.data", null, currentLocale);
        
        if (mostSpecificCause != null) {
        	 errorMessage = new Error(userMessage);
        } else {
            errorMessage = new Error(ex.getMessage(), userMessage);
        }
        return new ResponseEntity<Object>(errorMessage, headers, status);
    }

    
    

    
	
}