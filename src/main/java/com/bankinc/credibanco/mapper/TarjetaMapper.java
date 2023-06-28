package com.bankinc.credibanco.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankinc.credibanco.domain.Tarjeta;
import com.bankinc.credibanco.dto.TarjetaDTO;

/**
* @author Javier Ricardo Agudelo
* 
*/

@Mapper
public interface TarjetaMapper {

	@Mapping(source = "administrador.idAdministrador", target = "idAdministradorAdministrador")
	@Mapping(source = "cliente.idcliente", target = "idclienteCliente")
	@Mapping(source = "producto.idproducto", target = "idproductoProducto")

	public TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta);

	@Mapping(source = "idAdministradorAdministrador", target = "administrador.idAdministrador")
	@Mapping(source = "idclienteCliente", target = "cliente.idcliente")
	@Mapping(source = "idproductoProducto", target = "producto.idproducto")

	public Tarjeta tarjetaDTOToTarjeta(TarjetaDTO tarjetaDTO);

	public List<TarjetaDTO> listTarjetaToListTarjetaDTO(List<Tarjeta> tarjetas);

	public List<Tarjeta> listTarjetaDTOToListTarjeta(List<TarjetaDTO> tarjetaDTOs);
}
