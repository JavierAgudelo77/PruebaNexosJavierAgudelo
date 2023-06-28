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
class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private TransaccionRepository transaccionRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Transaccion transaccion) throws ConstraintViolationException {

		Set<ConstraintViolation<Transaccion>> constraintViolations = validator.validate(transaccion);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return transaccionRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Transaccion> findAll() {
		log.debug("finding all Transaccion instances");
		return transaccionRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaccion save(Transaccion entity) throws BankIncException {
		log.debug("saving Transaccion instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Transaccion");
		}

		validate(entity);

		return transaccionRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Transaccion entity) throws BankIncException {
		log.debug("deleting Transaccion instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Transaccion");
		}

		if (entity.getIdtransaccion() == null) {
			throw new BankIncMessManager().new EmptyFieldException("idtransaccion");
		}

		if (!transaccionRepository.existsById(entity.getIdtransaccion())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		transaccionRepository.deleteById(entity.getIdtransaccion());
		log.debug("delete Transaccion successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws BankIncException {
		log.debug("deleting Transaccion instance");
		if (id == null) {
			throw new BankIncMessManager().new EmptyFieldException("idtransaccion");
		}
		Optional<Transaccion> optionalTransaccion = transaccionRepository.findById(id);
		if (optionalTransaccion.isPresent()) {
			delete(optionalTransaccion.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Transaccion update(Transaccion entity) throws BankIncException {

		log.debug("updating Transaccion instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Transaccion");
		}

		validate(entity);

		if (!transaccionRepository.existsById(entity.getIdtransaccion())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		return transaccionRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Transaccion> findById(Integer idtransaccion) {
		log.debug("getting Transaccion instance");
		return transaccionRepository.findById(idtransaccion);
	}

}
