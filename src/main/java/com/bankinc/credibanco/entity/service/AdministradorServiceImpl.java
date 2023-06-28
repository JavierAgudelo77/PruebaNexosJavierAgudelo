package com.bankinc.credibanco.entity.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bankinc.credibanco.domain.Administrador;
import com.bankinc.credibanco.domain.Tarjeta;
import com.bankinc.credibanco.exception.BankIncException;
import com.bankinc.credibanco.exception.BankIncMessManager;
import com.bankinc.credibanco.repository.AdministradorRepository;
import com.bankinc.credibanco.utility.Utilities;

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
class AdministradorServiceImpl implements AdministradorService {

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private Validator validator;

	@Override
	public void validate(Administrador administrador) throws ConstraintViolationException {

		Set<ConstraintViolation<Administrador>> constraintViolations = validator.validate(administrador);
		if (!constraintViolations.isEmpty()) {
			throw new ConstraintViolationException(constraintViolations);
		}

	}

	@Override
	@Transactional(readOnly = true)
	public Long count() {
		return administradorRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public List<Administrador> findAll() {
		log.debug("finding all Administrador instances");
		return administradorRepository.findAll();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Administrador save(Administrador entity) throws BankIncException {
		log.debug("saving Administrador instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Administrador");
		}

		validate(entity);

		return administradorRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Administrador entity) throws BankIncException {
		log.debug("deleting Administrador instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Administrador");
		}

		if (entity.getIdAdministrador() == null) {
			throw new BankIncMessManager().new EmptyFieldException("idAdministrador");
		}

		if (!administradorRepository.existsById(entity.getIdAdministrador())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		findById(entity.getIdAdministrador()).ifPresent(entidad -> {
			List<Tarjeta> tarjetas = entidad.getTarjetas();
			if (Utilities.validationsList(tarjetas)) {
				throw new BankIncMessManager().new DeletingException("tarjetas");
			}
		});

		administradorRepository.deleteById(entity.getIdAdministrador());
		log.debug("delete Administrador successful");

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Integer id) throws BankIncException {
		log.debug("deleting Administrador instance");
		if (id == null) {
			throw new BankIncMessManager().new EmptyFieldException("idAdministrador");
		}
		Optional<Administrador> optionalAdministrador = administradorRepository.findById(id);
		if (optionalAdministrador.isPresent()) {
			delete(optionalAdministrador.get());
		}
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Administrador update(Administrador entity) throws BankIncException {

		log.debug("updating Administrador instance");

		if (entity == null) {
			throw new BankIncMessManager().new NullEntityExcepcion("Administrador");
		}

		validate(entity);

		if (!administradorRepository.existsById(entity.getIdAdministrador())) {
			throw new BankIncMessManager(BankIncMessManager.ENTITY_WITHSAMEKEY);
		}

		return administradorRepository.save(entity);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Administrador> findById(Integer idAdministrador) {
		log.debug("getting Administrador instance");
		return administradorRepository.findById(idAdministrador);
	}

}
