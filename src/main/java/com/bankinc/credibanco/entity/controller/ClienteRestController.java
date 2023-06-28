package com.bankinc.credibanco.entity.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Date;

import com.bankinc.credibanco.mapper.ClienteMapper;
import com.bankinc.credibanco.entity.service.ClienteService;
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
import com.bankinc.credibanco.dto.ClienteDTO;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/


@RestController
@RequestMapping("/api/v1/cliente")
@Slf4j
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteMapper clienteMapper;

	@GetMapping(value = "/{idcliente}")
	public ResponseEntity<?> findById(@PathVariable("idcliente") Integer idcliente) throws BankIncException {
		log.debug("Request to findById() Cliente");

		Optional<Cliente> optionalCliente = clienteService.findById(idcliente);

		Cliente cliente = optionalCliente.isPresent() ? optionalCliente.get() : null;

		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws BankIncException {
		log.debug("Request to findAll() Cliente");

		return ResponseEntity.ok().body(clienteMapper.listClienteToListClienteDTO(clienteService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ClienteDTO clienteDTO) throws BankIncException {
		log.debug("Request to save Cliente: {}", clienteDTO);

		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		cliente = clienteService.save(cliente);
		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));

	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ClienteDTO clienteDTO) throws BankIncException {
		log.debug("Request to update Cliente: {}", clienteDTO);

		Cliente cliente = clienteMapper.clienteDTOToCliente(clienteDTO);
		cliente = clienteService.update(cliente);

		return ResponseEntity.ok().body(clienteMapper.clienteToClienteDTO(cliente));

	}

	@DeleteMapping(value = "/{idcliente}")
	public ResponseEntity<?> delete(@PathVariable("idcliente") Integer idcliente) throws BankIncException {
		log.debug("Request to delete Cliente");

		clienteService.deleteById(idcliente);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(clienteService.count());
	}

}
