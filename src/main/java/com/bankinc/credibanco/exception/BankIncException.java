package com.bankinc.credibanco.exception;

import lombok.Getter;
import lombok.Setter;

/**
* @author Javier Ricardo Agudelo
* 
*/


@Getter
@Setter
public class BankIncException extends Exception {
    private static final long serialVersionUID = 1L;
    private final Integer code;
    private final String exception;

    public BankIncException(Integer code, String exception, Exception e) {
    	super(e);
    	this.code = code;
    	this.exception = exception;
    }

    public BankIncException(String exception, Exception e) {
    	super(e);
    	this.code = null;
    	this.exception = exception;
    }
}
