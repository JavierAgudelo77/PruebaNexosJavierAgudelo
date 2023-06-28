package com.bankinc.credibanco.exception;

/**
* @author Javier Ricardo Agudelo
* 
*/

public class UserException extends BankIncException {

	private static final long serialVersionUID = 1L;

	public UserException(String exception) {
        super(400, exception, null);
    }
    
    public UserException(String exception, Exception e) {
        super(400, exception, e);
    }
}