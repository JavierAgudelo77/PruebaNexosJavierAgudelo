/**
 * Esta clase representa PruebaServiceImpl, que es responsable de manejar varias operaciones relacionadas con el servicio de prueba.
 */
package com.bankinc.credibanco.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bankinc.credibanco.domain.Cliente;
import com.bankinc.credibanco.domain.Producto;
import com.bankinc.credibanco.domain.Tarjeta;
import com.bankinc.credibanco.domain.Transaccion;
import com.bankinc.credibanco.dto.ClienteDTO;
import com.bankinc.credibanco.dto.TarjetaDTO;
import com.bankinc.credibanco.dto.TransaccionDTO;
import com.bankinc.credibanco.entity.service.ClienteService;
import com.bankinc.credibanco.entity.service.ProductoService;
import com.bankinc.credibanco.entity.service.TarjetaService;
import com.bankinc.credibanco.entity.service.TransaccionService;
import com.bankinc.credibanco.exception.BankIncException;
import com.bankinc.credibanco.exception.BankIncMessManager;
import com.bankinc.credibanco.mapper.ClienteMapper;
import com.bankinc.credibanco.mapper.TarjetaMapper;
import com.bankinc.credibanco.mapper.TransaccionMapper;

import lombok.extern.slf4j.Slf4j;


/**
* @author Javier Ricardo Agudelo
* 
*/

@Service("PruebaServiceImpl")
@Slf4j
public class PruebaServiceImpl {

	@Autowired
	private ProductoService productoService;

	@Autowired
	private TarjetaService tarjetaService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	private ClienteMapper clienteMapper;

	@Autowired
	private TarjetaMapper tarjetaMapper;

	@Autowired
	private TransaccionService transaccionService;

	@Autowired
	private TransaccionMapper transaccionMapper;

	/**
	 * Genera un número de tarjeta basado en el ID de producto proporcionado.
	 *
	 * @param idproducto El ID del producto.
	 * @return El número de tarjeta generado.
	 * @throws BankIncException Si ocurre un error durante la generación del número de tarjeta.
	 */
	public String generarNumeroTarjeta(Integer idproducto) throws BankIncException {
		Optional<Producto> optionalProducto = productoService.findById(idproducto);
		Producto producto = optionalProducto.isPresent() ? optionalProducto.get() : null;
		if (producto == null) {
			throw new BankIncMessManager("No existe el producto con ese número de ID");
		}
		String numeroTarjeta = "";
		boolean cadControl = true;
		while (cadControl) {
			try {
				numeroTarjeta = generarNumero(idproducto);
				cadControl = false;
			} catch (BankIncMessManager e) {
				log.debug("La tarjeta ya existe");
			}
		}
		return numeroTarjeta;
	}

	private String generarNumero(Integer idproducto) {
		String strProductId = String.format("%06d", idproducto);
		long randomNum = ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L);
		String numerotarjeta = strProductId + randomNum;
		Long id_Tarjeta = Long.parseLong(numerotarjeta);
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(id_Tarjeta);
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;
		if (tarjeta != null) {
			throw new BankIncMessManager("La tarjeta ya existe");
		}
		return numerotarjeta;
	}

	/**
	 * Encuentra un cliente basado en el ID del cliente proporcionado.
	 *
	 * @param idcliente El ID del cliente.
	 * @return El DTO del cliente encontrado.
	 * @throws BankIncException Si ocurre un error durante la búsqueda del cliente.
	 */
	public ClienteDTO findClient(Integer idcliente) throws BankIncException {
		log.debug("Solicitud para findById() Cliente");
		Optional<Cliente> optionalCliente = clienteService.findById(idcliente);
		Cliente cliente = optionalCliente.isPresent() ? optionalCliente.get() : null;
		return clienteMapper.clienteToClienteDTO(cliente);
	}

	/**
	 * Crea una tarjeta con los detalles proporcionados.
	 *
	 * @param tarjetaDTO Los detalles de la tarjeta.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante la creación de la tarjeta.
	 */
	public ResponseEntity<?> createCard(TarjetaDTO tarjetaDTO) throws BankIncException {
		tarjetaDTO.setFechacreacion(new Date());
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.YEAR, 3);
		Date fechaExpiracion = calendar.getTime();
		tarjetaDTO.setFechavencimiento(fechaExpiracion);
		tarjetaDTO.setIdAdministradorAdministrador(1);
		tarjetaDTO.setEstado("INACTIVA");
		tarjetaDTO.setSaldo(Double.parseDouble("0.0"));
		Tarjeta tarjeta = tarjetaMapper.tarjetaDTOToTarjeta(tarjetaDTO);
		tarjeta = tarjetaService.save(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));
	}

	/**
	 * Activa una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param idCard El ID de la tarjeta.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante la activación de la tarjeta.
	 */
	public ResponseEntity<?> activateCard(String idCard) throws BankIncException {
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(Long.parseLong(idCard));
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;
		tarjeta.setEstado("ACTIVA");
		tarjeta = tarjetaService.update(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));
	}

	/**
	 * Bloquea una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param idCard El ID de la tarjeta.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante el bloqueo de la tarjeta.
	 */
	public ResponseEntity<?> blockCard(String idCard) throws BankIncException {
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(Long.parseLong(idCard));
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;
		tarjeta.setEstado("INACTIVA");
		tarjeta = tarjetaService.update(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));
	}

	/**
	 * Recarga el saldo de una tarjeta con los detalles proporcionados.
	 *
	 * @param idCard  El ID de la tarjeta.
	 * @param balance El saldo a recargar.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante la recarga del saldo.
	 */
	public ResponseEntity<?> balanceCard(String idCard, BigDecimal balance) throws BankIncException {
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(Long.parseLong(idCard));
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;
		tarjeta.setSaldo(balance.doubleValue());
		tarjeta = tarjetaService.update(tarjeta);
		return ResponseEntity.ok().body(tarjetaMapper.tarjetaToTarjetaDTO(tarjeta));
	}

	/**
	 * Obtiene el saldo de una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param idCard El ID de la tarjeta.
	 * @return El saldo de la tarjeta.
	 * @throws BankIncException Si ocurre un error durante la obtención del saldo.
	 */
	public Double getBalanceCard(String idCard) throws BankIncException {
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(Long.parseLong(idCard));
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;

		return tarjetaMapper.tarjetaToTarjetaDTO(tarjeta).getSaldo();
	}

	/**
	 * Realiza una transacción de compra con los detalles de tarjeta y precio proporcionados.
	 *
	 * @param idCard El ID de la tarjeta.
	 * @param price  El precio de la compra.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante la transacción de compra.
	 */
	public ResponseEntity<?> transactionPurchase(Long idCard, Double price) throws BankIncException {
		Optional<Tarjeta> optionalTarjeta = tarjetaService.findById(idCard);
		Tarjeta tarjeta = optionalTarjeta.isPresent() ? optionalTarjeta.get() : null;
		TarjetaDTO tarjetaDTO = tarjetaMapper.tarjetaToTarjetaDTO(tarjeta);
		Double balance = tarjetaDTO.getSaldo();
		if (price > balance) {
			throw new BankIncMessManager(BankIncMessManager.NOT_FUNDS);
		} else {
			Double newBalance = balance - price;
			tarjeta.setSaldo(newBalance);
			tarjeta = tarjetaService.update(tarjeta);
			TransaccionDTO transaccionDTO = new TransaccionDTO();
			transaccionDTO.setFechatransaccion(new Date());
			transaccionDTO.setIdcomercio(0);
			transaccionDTO.setMonto(price);
			transaccionDTO.setNumerotarjetaTarjeta(tarjetaDTO.getNumerotarjeta());
			transaccionDTO.setEstado("CONFIRMADA");
			int randomNum = ThreadLocalRandom.current().nextInt(1_000_000, 1_000_000_000);
			transaccionDTO.setIdtransaccion(randomNum);
			Transaccion transaccion = transaccionMapper.transaccionDTOToTransaccion(transaccionDTO);
			transaccion = transaccionService.save(transaccion);
			return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));
		}
	}

	/**
	 * Consulta una transacción con el ID de transacción proporcionado.
	 *
	 * @param idtransaccion El ID de la transacción.
	 * @return El ResponseEntity.
	 */
	public ResponseEntity<?> consultarTransaccion(Integer idtransaccion) {
		Optional<Transaccion> optionalTransaccion = transaccionService.findById(idtransaccion);
		Transaccion transaccion = optionalTransaccion.isPresent() ? optionalTransaccion.get() : null;
		return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));
	}

	/**
	 * Cancela una transacción con el ID de transacción proporcionado.
	 *
	 * @param idtransaccion El ID de la transacción.
	 * @return El ResponseEntity.
	 * @throws BankIncException Si ocurre un error durante la cancelación de la transacción.
	 */
	public ResponseEntity<?> transactionAnulation(Integer idtransaccion) throws BankIncException {

		Optional<Transaccion> optionalTransaccion = transaccionService.findById(idtransaccion);

		Transaccion transaccion = optionalTransaccion.isPresent() ? optionalTransaccion.get() : null;
		TransaccionDTO transaccionDTO = transaccionMapper.transaccionToTransaccionDTO(transaccion);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(transaccionDTO.getFechatransaccion());
		calendar.add(Calendar.HOUR, 24);
		Date fechaLimite = calendar.getTime();
		Date fechaActual = new Date();
		if (fechaActual.before(fechaLimite)) {
			transaccion.setEstado("ANULADA");
			transaccion = transaccionService.update(transaccion);
			return ResponseEntity.ok().body(transaccionMapper.transaccionToTransaccionDTO(transaccion));
		} else {
			throw new BankIncMessManager(BankIncMessManager.DEADLINE);
		}
	}
}
