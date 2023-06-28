package com.bankinc.credibanco.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankinc.credibanco.domain.Administrador;
import com.bankinc.credibanco.dto.AdministradorDTO;



/**
* @author Javier Ricardo Agudelo
* 
*/

@Mapper
public interface AdministradorMapper {

	
	public AdministradorDTO administradorToAdministradorDTO(Administrador administrador);
	
	
	public Administrador administradorDTOToAdministrador(AdministradorDTO administradorDTO);
	
	public List<AdministradorDTO> listAdministradorToListAdministradorDTO(List<Administrador> administradors);

	public List<Administrador> listAdministradorDTOToListAdministrador(List<AdministradorDTO> administradorDTOs);
}
