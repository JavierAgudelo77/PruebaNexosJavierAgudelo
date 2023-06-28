package com.bankinc.credibanco.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankinc.credibanco.domain.Cliente;
import com.bankinc.credibanco.dto.ClienteDTO;



/**
* @author Javier Ricardo Agudelo
* 
*/

@Mapper
public interface ClienteMapper {

	
	public ClienteDTO clienteToClienteDTO(Cliente cliente);
	
	
	public Cliente clienteDTOToCliente(ClienteDTO clienteDTO);
	
	public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes);

	public List<Cliente> listClienteDTOToListCliente(List<ClienteDTO> clienteDTOs);
}
