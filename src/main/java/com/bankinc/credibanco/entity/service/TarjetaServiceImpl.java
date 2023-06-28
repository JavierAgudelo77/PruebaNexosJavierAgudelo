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
class TarjetaServiceImpl implements TarjetaService {

	@Autowired
	private TarjetaRepository tarjetaRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Tarjeta tarjeta) throws ConstraintViolationException {

		Set<ConstraintViolation<Tarjeta>> constraintViolations = validator.validate(tarjeta);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return tarjetaRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findAll() {
		log.debug("finding all Tarjeta instances");
		return tarjetaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tarjeta save(Tarjeta entity) throws BankIncException {
		log.debug("saving Tarjeta instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Tarjeta");
		}

		validate(entity);

		return tarjetaRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Tarjeta entity) throws BankIncException {
		log.debug("deleting Tarjeta instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Tarjeta");
		}

		if (entity.getNumerotarjeta() == null) {
			throw new BankIncMessManager().new EmptyFieldException("numerotarjeta");
		}

		if (!tarjetaRepository.existsById(entity.getNumerotarjeta())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getNumerotarjeta()).ifPresent(entidad -> {
			List<Transaccion> transaccions = entidad.getTransaccions();
			if (Utilities.validationsList(transaccions)) {
				throw new BankIncMessManager().new DeletingException("transaccions");
			}
		});

		tarjetaRepository.deleteById(entity.getNumerotarjeta());
		log.debug("delete Tarjeta successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws BankIncException {
		log.debug("deleting Tarjeta instance");
		if (id == null) {
			throw new BankIncMessManager().new EmptyFieldException("numerotarjeta");
		}
		Optional<Tarjeta> optionalTarjeta = tarjetaRepository.findById(id);
		if (optionalTarjeta.isPresent()) {
			delete(optionalTarjeta.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Tarjeta update(Tarjeta entity) throws BankIncException {

		log.debug("updating Tarjeta instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Tarjeta");
		}

		validate(entity);

		if (!tarjetaRepository.existsById(entity.getNumerotarjeta())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		return tarjetaRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Tarjeta> findById(Long numerotarjeta) {
		log.debug("getting Tarjeta instance");
		return tarjetaRepository.findById(numerotarjeta);
	}

}
