package com.bankinc.credibanco.entity.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Date;

import com.bankinc.credibanco.mapper.AdministradorMapper;
import com.bankinc.credibanco.entity.service.AdministradorService;
import com.bankinc.credibanco.exception.BankIncException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankinc.credibanco.domain.*;
import com.bankinc.credibanco.dto.AdministradorDTO;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/

@RestController
@RequestMapping("/api/v1/administrador")
@Slf4j
public class AdministradorRestController {

	@Autowired
	private AdministradorService administradorService;

	@Autowired
	private AdministradorMapper administradorMapper;

	@GetMapping(value = "/{idAdministrador}")
	public ResponseEntity<?> findById(@PathVariable("idAdministrador") Integer idAdministrador)
			throws BankIncException {
		log.debug("Request to findById() Administrador");

		Optional<Administrador> optionalAdministrador = administradorService.findById(idAdministrador);

		Administrador administrador = optionalAdministrador.isPresent() ? optionalAdministrador.get() : null;

		return ResponseEntity.ok().body(administradorMapper.administradorToAdministradorDTO(administrador));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws BankIncException {
		log.debug("Request to findAll() Administrador");

		return ResponseEntity.ok()
				.body(administradorMapper.listAdministradorToListAdministradorDTO(administradorService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody AdministradorDTO administradorDTO) throws BankIncException {
		log.debug("Request to save Administrador: {}", administradorDTO);

		Administrador administrador = administradorMapper.administradorDTOToAdministrador(administradorDTO);
		administrador = administradorService.save(administrador);
		return ResponseEntity.ok().body(administradorMapper.administradorToAdministradorDTO(administrador));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody AdministradorDTO administradorDTO) throws BankIncException {
		log.debug("Request to update Administrador: {}", administradorDTO);

		Administrador administrador = administradorMapper.administradorDTOToAdministrador(administradorDTO);
		administrador = administradorService.update(administrador);

		return ResponseEntity.ok().body(administradorMapper.administradorToAdministradorDTO(administrador));

	}

	@DeleteMapping(value = "/{idAdministrador}")
	public ResponseEntity<?> delete(@PathVariable("idAdministrador") Integer idAdministrador)
			throws BankIncException {
		log.debug("Request to delete Administrador");

		administradorService.deleteById(idAdministrador);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(administradorService.count());
	}

}
