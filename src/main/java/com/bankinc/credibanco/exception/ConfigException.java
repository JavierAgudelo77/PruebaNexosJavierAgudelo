package com.bankinc.credibanco.exception;

public class ConfigException extends BankIncException {


/**
* @author Javier Ricardo Agudelo
* 
*/


	
private static final long serialVersionUID = 1L;
	
	public ConfigException(String exception) {
        super(412, exception, null);
    }
    
    public ConfigException(String exception, Exception e) {
        super(412, exception, e);
    }
}