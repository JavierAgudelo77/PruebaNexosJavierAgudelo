package com.bankinc.credibanco.entity.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Date;

import com.bankinc.credibanco.mapper.TarjetaMapper;
import com.bankinc.credibanco.entity.service.TarjetaService;
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
import com.bankinc.credibanco.dto.TarjetaDTO;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/


@RestController
@RequestMapping("/api/v1/tarjeta")
@Slf4j
public class TarjetaRestController {

	@Autowired
	private TarjetaService tarjetaService;

	@Autowired
	private TarjetaMapper tarjetaMapper;

	@GetMapping(value = "/{numerotarjeta}")
	public ResponseEntity<?> findById(@PathVariable("numerotarjeta") Long numerotarjeta) throws BankIncException {
		log.debug("Request to findById() Tarjeta");

		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(numerotarjeta);

		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;

		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws BankIncException {
		log.debug("Request to findAll() Tarjeta");

		return ResponseEntity.ok().body(tarjetaMapper.listTarjetaToListTarjetaDTO(tarjetaService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody TarjetaDTO tarjetaDTO) throws BankIncException {
		log.debug("Request to save Tarjeta: {}", tarjetaDTO);

		Tarjeta tarjeta = tarjetaMapper.tarjetaDTOToTarjeta(tarjetaDTO);
		tarjeta = tarjetaService.save(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody TarjetaDTO tarjetaDTO) throws BankIncException {
		log.debug("Request to update Tarjeta: {}", tarjetaDTO);
		Tarjeta tarjeta = tarjetaMapper.tarjetaDTOToTarjeta(tarjetaDTO);
		tarjeta = tarjetaService.update(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));
	}

	@DeleteMapping(value = "/{numerotarjeta}")
	public ResponseEntity<?> delete(@PathVariable("numerotarjeta") Long numerotarjeta) throws BankIncException {
		log.debug("Request to delete Tarjeta");

		tarjetaService.deleteById(numerotarjeta);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(tarjetaService.count());
	}

}
