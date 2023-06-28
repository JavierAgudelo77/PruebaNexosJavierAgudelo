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
public class TarjetaDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
		 @NotNull	
		 @NotEmpty	
		 @Size(max=255)	
		 private String estado;	
		 @NotNull	
		 private Date fechacreacion;	
		 @NotNull	
		 private Date fechavencimiento;	
		 @NotNull	
		 private Long numerotarjeta;	
		 @NotNull	
		 private Double saldo;	
		 private Integer idAdministradorAdministrador;	
		 private Integer idclienteCliente;	
		 private Integer idproductoProducto;	
	}
