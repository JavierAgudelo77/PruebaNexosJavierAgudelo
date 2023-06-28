package com.bankinc.credibanco.entity.service;

import java.math.*;
import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.bankinc.credibanco.exception.*;
import com.bankinc.credibanco.repository.*;
import com.bankinc.credibanco.utility.Utilities;

import com.bankinc.credibanco.domain.*;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/


@Scope("singleton")
@Service
@Slf4j
class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Cliente cliente) throws ConstraintViolationException {

		Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return clienteRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		log.debug("finding all Cliente instances");
		return clienteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente entity) throws BankIncException {
		log.debug("saving Cliente instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Cliente");
		}

		validate(entity);

		return clienteRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Cliente entity) throws BankIncException {
		log.debug("deleting Cliente instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Cliente");
		}

		if (entity.getIdcliente() == null) {
			throw new BankIncMessManager().new EmptyFieldException("idcliente");
		}

		if (!clienteRepository.existsById(entity.getIdcliente())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdcliente()).ifPresent(entidad -> {
			List<Tarjeta> tarjetas = entidad.getTarjetas();
			if (Utilities.validationsList(tarjetas)) {
				throw new BankIncMessManager().new DeletingException("tarjetas");
			}
		});

		clienteRepository.deleteById(entity.getIdcliente());
		log.debug("delete Cliente successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws BankIncException {
		log.debug("deleting Cliente instance");
		if (id == null) {
			throw new BankIncMessManager().new EmptyFieldException("idcliente");
		}
		Optional<Cliente> optionalCliente = clienteRepository.findById(id);
		if (optionalCliente.isPresent()) {
			delete(optionalCliente.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente update(Cliente entity) throws BankIncException {

		log.debug("updating Cliente instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Cliente");
		}

		validate(entity);

		if (!clienteRepository.existsById(entity.getIdcliente())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		return clienteRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Cliente> findById(Integer idcliente) {
		log.debug("getting Cliente instance");
		return clienteRepository.findById(idcliente);
	}

}
