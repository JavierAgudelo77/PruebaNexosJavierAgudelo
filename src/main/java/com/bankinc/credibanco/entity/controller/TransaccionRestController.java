package com.bankinc.credibanco.entity.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Date;

import com.bankinc.credibanco.mapper.TransaccionMapper;
import com.bankinc.credibanco.entity.service.TransaccionService;
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
import com.bankinc.credibanco.dto.TransaccionDTO;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/


@RestController
@RequestMapping("/api/v1/transaccion")
@Slf4j
public class TransaccionRestController {

	@Autowired
	private TransaccionService transaccionService;

	@Autowired
	private TransaccionMapper transaccionMapper;

	@GetMapping(value = "/{idtransaccion}")
	public ResponseEntity<?> findById(@PathVariable("idtransaccion") Integer idtransaccion) throws BankIncException {
		log.debug("Request to findById() Transaccion");
		Optional<Transaccion> optionalTransaccion = transaccionService.findById(idtransaccion);
		Transaccion transaccion = optionalTransaccion.isPresent() ? optionalTransaccion.get() : null;
		return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));
	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws BankIncException {
		log.debug("Request to findAll() Transaccion");

		return ResponseEntity.ok()
				.body(transaccionMapper.listTransaccionToListTransaccionDTO(transaccionService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody TransaccionDTO transaccionDTO) throws BankIncException {
		log.debug("Request to save Transaccion: {}", transaccionDTO);

		Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);
		transaccion = transaccionService.save(transaccion);
		return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody TransaccionDTO transaccionDTO) throws BankIncException {
		log.debug("Request to update Transaccion: {}", transaccionDTO);

		Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);
		transaccion = transaccionService.update(transaccion);

		return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));

	}

	@DeleteMapping(value = "/{idtransaccion}")
	public ResponseEntity<?> delete(@PathVariable("idtransaccion") Integer idtransaccion) throws BankIncException {
		log.debug("Request to delete Transaccion");

		transaccionService.deleteById(idtransaccion);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(transaccionService.count());
	}

}
