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
@Table(name = "cliente")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cliente implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "idcliente", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idcliente;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "primerapellido", nullable = false)
	private String primerapellido;
	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "primernombre", nullable = false)
	private String primernombre;
	@Column(name = "segundoapellido")
	private String segundoapellido;
	@Column(name = "segundonombre")
	private String segundonombre;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
	private List<Tarjeta> tarjetas = new ArrayList<>();

}