package com.bankinc.credibanco.dto;

import java.util.Date;
import java.io.Serializable;
import java.sql.*;

import jakarta.persistence.Column;
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
public class TransaccionDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Date fechatransaccion;
	@NotNull
	private Integer idcomercio;
	@NotNull
	private Integer idtransaccion;
	@NotNull
	private Double monto;
	private Long numerotarjetaTarjeta;
	@NotNull
	private String estado;

}
