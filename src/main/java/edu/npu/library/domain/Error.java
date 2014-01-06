package edu.npu.library.domain;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Used to wrap runtime exceptions nicely
 * @author Neeta Vekariya
 *
 */
public class Error {
	 
    private List<String> errors;
    public Error() {
    }
 
    public Error(List<String> errors) {
    	this.errors = errors;
    }
 
    public Error(String error) {
        this(Collections.singletonList(error));
    }
 
    public Error(String ... errors) {
        this(Arrays.asList(errors));
    }
 
    public List<String> getErrors() {
        return errors;
    }
 
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

	 
}
