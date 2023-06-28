package com.bankinc.credibanco.dto;
import java.util.Date;
import java.io.Serializable;
import java.sql.*;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* @author Javier Ricardo Agudelo
* 
*/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
		 @NotNull	
		 @NotEmpty	
		 @Size(max=255)	
		 private String descripcionproducto;	
		 @NotNull	
		 private Integer idproducto;	
	}
