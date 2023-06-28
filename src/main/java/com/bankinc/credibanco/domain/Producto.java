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
@Table(name = "producto")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Producto implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idproducto", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idproducto;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "descripcionproducto", nullable = false)
	private String descripcionproducto;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "producto")
	private List<Tarjeta> tarjetas = new ArrayList<>();

}