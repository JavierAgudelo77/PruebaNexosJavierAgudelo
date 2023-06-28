package com.bankinc.credibanco.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankinc.credibanco.domain.Transaccion;
import com.bankinc.credibanco.dto.TransaccionDTO;



/**
* @author Javier Ricardo Agudelo
* 
*/

@Mapper
public interface TransaccionMapper {

	@Mapping(source = "tarjeta.numerotarjeta",  target = "numerotarjetaTarjeta")

	public TransaccionDTO transaccionToTransaccionDTO(Transaccion transaccion);
	
	@Mapping(source = "numerotarjetaTarjeta",  target = "tarjeta.numerotarjeta")

	public Transaccion transaccionDTOToTransaccion(TransaccionDTO transaccionDTO);
	
	public List<TransaccionDTO> listTransaccionToListTransaccionDTO(List<Transaccion> transaccions);

	public List<Transaccion> listTransaccionDTOToListTransaccion(List<TransaccionDTO> transaccionDTOs);
}
