package com.bankinc.credibanco.domain;

import java.util.ArrayList;
import java.util.List;

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
@Table(name = "tarjeta")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tarjeta implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "numerotarjeta", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long numerotarjeta;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idadministrador")
	//@NotNull
	private Administrador administrador;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcliente")
	@NotNull
	private Cliente cliente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idproducto")
	@NotNull
	private Producto producto;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "estado", nullable = false)
	private String estado;
	
	@NotNull
	@Column(name = "fechacreacion", nullable = false)
	private Date fechacreacion;
	@NotNull
	@Column(name = "fechavencimiento", nullable = false)
	private Date fechavencimiento;
	@NotNull
	@Column(name = "saldo", nullable = false)
	private Double saldo;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tarjeta")
	private List<Transaccion> transaccions = new ArrayList<>();

}