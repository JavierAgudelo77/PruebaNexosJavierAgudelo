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
class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository productoRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Producto producto) throws ConstraintViolationException {

		Set<ConstraintViolation<Producto>> constraintViolations = validator.validate(producto);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return productoRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Producto> findAll() {
		log.debug("finding all Producto instances");
		return productoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto save(Producto entity) throws BankIncException {
		log.debug("saving Producto instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Producto");
		}

		validate(entity);

		return productoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Producto entity) throws BankIncException {
		log.debug("deleting Producto instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Producto");
		}

		if (entity.getIdproducto() == null) {
			throw new BankIncMessManager().new EmptyFieldException("idproducto");
		}

		if (!productoRepository.existsById(entity.getIdproducto())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdproducto()).ifPresent(entidad -> {
			List<Tarjeta> tarjetas = entidad.getTarjetas();
			if (Utilities.validationsList(tarjetas)) {
				throw new BankIncMessManager().new DeletingException("tarjetas");
			}
		});

		productoRepository.deleteById(entity.getIdproducto());
		log.debug("delete Producto successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws BankIncException {
		log.debug("deleting Producto instance");
		if (id == null) {
			throw new BankIncMessManager().new EmptyFieldException("idproducto");
		}
		Optional<Producto> optionalProducto = productoRepository.findById(id);
		if (optionalProducto.isPresent()) {
			delete(optionalProducto.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Producto update(Producto entity) throws BankIncException {

		log.debug("updating Producto instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Producto");
		}

		validate(entity);

		if (!productoRepository.existsById(entity.getIdproducto())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		return productoRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Producto> findById(Integer idproducto) {
		log.debug("getting Producto instance");
		return productoRepository.findById(idproducto);
	}

}
