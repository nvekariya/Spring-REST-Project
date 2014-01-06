package edu.npu.library.exception;

/**
 * Custom Exception that will be thrown for some domain errors
 * @author Neeta Vekariya
 *
 */
public class DomainException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public DomainException(String message){
		super(message);
	}	
}
