/**
Esta clase representa el controlador PruebaController, que se encarga de manejar varias operaciones relacionadas con la API de prueba.
*/
package com.bankinc.credibanco.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bankinc.credibanco.dto.AdminCardDTO;
import com.bankinc.credibanco.dto.ClienteDTO;
import com.bankinc.credibanco.dto.TarjetaDTO;
import com.bankinc.credibanco.exception.BankIncException;
import com.bankinc.credibanco.service.PruebaServiceImpl;

import jakarta.validation.Valid;

/**
* @author Javier Ricardo Agudelo
* 
*/

@RestController
@RequestMapping("/api/v1/prueba")
public class PruebaController {

	@Autowired
	private PruebaServiceImpl pruebaServiceImpl;

//@Autowired
//private TransaccionService transaccionService;

	/**
	 * Genera un número de tarjeta basado en el ID de producto proporcionado.
	 *
	 * @param idproducto El ID del producto.
	 * @return El número de tarjeta generado.
	 * @throws NumberFormatException Si el ID de producto no es un número válido.
	 * @throws BankIncException      Si ocurre un error durante la generación del
	 *                               número de tarjeta.
	 */
	@GetMapping("/numeroTarjeta/{idproducto}")
	public String generarNumeroTarjeta(@PathVariable("idproducto") String idproducto)
			throws NumberFormatException, BankIncException {
		return pruebaServiceImpl.generarNumeroTarjeta(Integer.valueOf(idproducto));
	}

	/**
	 * Emite una nueva tarjeta basada en los detalles de la tarjeta proporcionados.
	 *
	 * @param crearTarjetaDTO Los detalles de la tarjeta.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante la emisión de la tarjeta.
	 */
	@PostMapping("/issueCard")
	public ResponseEntity<?> issueCard(@Valid @RequestBody AdminCardDTO crearTarjetaDTO) throws BankIncException {
		String cardNumber = pruebaServiceImpl
				.generarNumeroTarjeta(Integer.valueOf(crearTarjetaDTO.getIdproductoProducto()));
		ClienteDTO cliente = pruebaServiceImpl.findClient(crearTarjetaDTO.getIdclienteCliente());
		TarjetaDTO tarjetaDTO = new TarjetaDTO();
		tarjetaDTO.setIdproductoProducto(crearTarjetaDTO.getIdproductoProducto());
		tarjetaDTO.setIdclienteCliente(cliente.getIdcliente());
		tarjetaDTO.setIdproductoProducto(Integer.valueOf(crearTarjetaDTO.getIdproductoProducto()));
		tarjetaDTO.setNumerotarjeta(Long.parseLong(cardNumber));
		pruebaServiceImpl.createCard(tarjetaDTO);
		return pruebaServiceImpl.createCard(tarjetaDTO);
	}

	/**
	 * Activa una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param cardId El ID de la tarjeta.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante la activación de la
	 *                          tarjeta.
	 */
	@PostMapping("/card/enroll")
	public ResponseEntity<?> activarTarjeta(@Valid @RequestBody String cardId) throws BankIncException {
		return pruebaServiceImpl.activateCard(cardId);
	}

	/**
	 * Bloquea una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param cardId El ID de la tarjeta.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante el bloqueo de la tarjeta.
	 */
	@DeleteMapping("/card/{cardId}")
	public ResponseEntity<?> bloquearTarjeta(@PathVariable("cardId") String cardId) throws BankIncException {
		return pruebaServiceImpl.blockCard(cardId);
	}

	/**
	 * Recarga el saldo de una tarjeta con los detalles proporcionados.
	 *
	 * @param crearTarjetaDTO Los detalles de la tarjeta.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante la recarga de saldo.
	 */
	@PostMapping("/card/balance")
	public ResponseEntity<?> recargarSaldo(@Valid @RequestBody AdminCardDTO crearTarjetaDTO) throws BankIncException {
		String cardId = crearTarjetaDTO.getNumerotarjeta().toString();
		BigDecimal balance = new BigDecimal(crearTarjetaDTO.getSaldo());
		return pruebaServiceImpl.balanceCard(cardId, balance);
	}

	/**
	 * Recupera el saldo de una tarjeta con el ID de tarjeta proporcionado.
	 *
	 * @param cardId El ID de la tarjeta.
	 * @return El saldo de la tarjeta.
	 * @throws BankIncException Si ocurre un error durante la recuperación del
	 *                          saldo.
	 */
	@GetMapping("/card/balance/{cardId}")
	public Double consultarSaldo(@PathVariable("cardId") String cardId) throws BankIncException {
		return pruebaServiceImpl.getBalanceCard(cardId);
	}

	/**
	 * Realiza una transacción de compra con los detalles de tarjeta y precio
	 * proporcionados.
	 *
	 * @param adminTarjetaDTO Los detalles de la tarjeta y el precio.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante la transacción de compra.
	 */
	@PostMapping("/transaction/purchase")
	public ResponseEntity<?> transactionPurchase(@Valid @RequestBody AdminCardDTO adminTarjetaDTO)
			throws BankIncException {
		return pruebaServiceImpl.transactionPurchase(adminTarjetaDTO.getNumerotarjeta(), adminTarjetaDTO.getPrice());
	}

	/**
	 * Consulta una transacción con el ID de transacción proporcionado.
	 *
	 * @param idtransaccion El ID de la transacción.
	 * @return La entidad de respuesta.
	 */
	@GetMapping("/transaction/{transactionId}")
	public ResponseEntity<?> consultarTransaccion(@PathVariable("transactionId") Integer idtransaccion) {
		return pruebaServiceImpl.consultarTransaccion(idtransaccion);
	}

	/**
	 * Cancela una transacción con el ID de tarjeta proporcionado.
	 *
	 * @param adminTarjetaDTO Los detalles de la tarjeta.
	 * @return La entidad de respuesta.
	 * @throws BankIncException Si ocurre un error durante la cancelación de la
	 *                          transacción.
	 */
	@PostMapping("/transaction/anulation")
	public ResponseEntity<?> transactionAnulation(@Valid @RequestBody AdminCardDTO adminTarjetaDTO)
			throws BankIncException {
		return pruebaServiceImpl.transactionAnulation(adminTarjetaDTO.getIdtransaccion());
	}
}