package com.bankinc.credibanco.entity.controller;

import jakarta.validation.Valid;
import java.util.Optional;
import java.util.Date;

import com.bankinc.credibanco.mapper.ProductoMapper;
import com.bankinc.credibanco.entity.service.ProductoService;
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
import com.bankinc.credibanco.dto.ProductoDTO;

import lombok.extern.slf4j.Slf4j;

/**
* @author Javier Ricardo Agudelo
* 
*/


@RestController
@RequestMapping("/api/v1/producto")
@Slf4j
public class ProductoRestController {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private ProductoMapper productoMapper;

	@GetMapping(value = "/{idproducto}")
	public ResponseEntity<?> findById(@PathVariable("idproducto") Integer idproducto) throws BankIncException {
		log.debug("Request to findById() Producto");
		Optional<Producto> optionalProducto = productoService.findById(idproducto);
		Producto producto = optionalProducto.isPresent() ? optionalProducto.get() : null;
		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));

	}

	@GetMapping()
	public ResponseEntity<?> findAll() throws BankIncException {
		log.debug("Request to findAll() Producto");

		return ResponseEntity.ok().body(productoMapper.listProductoToListProductoDTO(productoService.findAll()));

	}

	@PostMapping()
	public ResponseEntity<?> save(@Valid @RequestBody ProductoDTO productoDTO) throws BankIncException {
		log.debug("Request to save Producto: {}", productoDTO);
		Producto producto = productoMapper.productoDTOToProducto(productoDTO);
		producto = productoService.save(producto);
		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));
	}

	@PutMapping()
	public ResponseEntity<?> update(@Valid @RequestBody ProductoDTO productoDTO) throws BankIncException {
		log.debug("Request to update Producto: {}", productoDTO);

		Producto producto = productoMapper.productoDTOToProducto(productoDTO);
		producto = productoService.update(producto);

		return ResponseEntity.ok().body(productoMapper.productoToProductoDTO(producto));

	}

	@DeleteMapping(value = "/{idproducto}")
	public ResponseEntity<?> delete(@PathVariable("idproducto") Integer idproducto) throws BankIncException {
		log.debug("Request to delete Producto");

		productoService.deleteById(idproducto);

		return ResponseEntity.ok().build();

	}

	@GetMapping(value = "/count")
	public ResponseEntity<?> count() {
		return ResponseEntity.ok().body(productoService.count());
	}

}
