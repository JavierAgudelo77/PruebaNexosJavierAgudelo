package com.bankinc.credibanco.exception;

/**
* @author Javier Ricardo Agudelo
* 
*/

public class SystemException extends BankIncException {

	private static final long serialVersionUID = 1L;

	public SystemException(String exception) {
        super(500, exception, null);
    }
    
    public SystemException(String exception, Exception e) {
        super(500, exception, e);
    }
}
