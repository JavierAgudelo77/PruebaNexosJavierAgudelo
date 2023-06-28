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
public class AdminCardDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
		 @Size(max=255)	
		 private String estado;	
		 private Date fechacreacion;	
		 private Date fechavencimiento;	
		 private Long numerotarjeta;		
		 private Double saldo;	
		 private Integer idAdministradorAdministrador;	
		 private Integer idclienteCliente;	
		 private Integer idproductoProducto;
		 private Double price;
		 private Integer idtransaccion;
		 
	}
