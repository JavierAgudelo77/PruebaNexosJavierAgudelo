package com.bankinc.credibanco.domain;

import java.util.Date;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
* @author Javier Ricardo Agudelo
* 
*/

@Entity
@Table(name = "transaccion")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaccion implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idtransaccion", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idtransaccion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numerotarjeta")
	@NotNull
	private Tarjeta tarjeta;

	@NotNull
	@Column(name = "fechatransaccion", nullable = false)
	private Date fechatransaccion;
	@NotNull
	@Column(name = "idcomercio", nullable = false)
	private Integer idcomercio;
	@NotNull
	@Column(name = "monto", nullable = false)
	private Double monto;
	
	@NotNull
	@Column(name = "estado", nullable = false)
	private String estado;

}