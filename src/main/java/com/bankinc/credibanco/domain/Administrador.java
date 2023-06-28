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
@Table(name = "administrador")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Administrador implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id_administrador", unique = true, nullable = false)
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idAdministrador;

	@NotNull
	@NotEmpty
	@Size(max = 255)
	@Column(name = "nombreadministrador", nullable = false)
	private String nombreadministrador;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "administrador")
	private List<Tarjeta> tarjetas = new ArrayList<>();

}