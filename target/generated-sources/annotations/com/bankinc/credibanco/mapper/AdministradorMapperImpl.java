package com.bankinc.credibanco.mapper;

import com.bankinc.credibanco.domain.Administrador;
import com.bankinc.credibanco.dto.AdministradorDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class AdministradorMapperImpl implements AdministradorMapper {

    @Override
    public AdministradorDTO administradorToAdministradorDTO(Administrador administrador) {
        if ( administrador == null ) {
            return null;
        }

        AdministradorDTO administradorDTO = new AdministradorDTO();

        administradorDTO.setIdAdministrador( administrador.getIdAdministrador() );
        administradorDTO.setNombreadministrador( administrador.getNombreadministrador() );

        return administradorDTO;
    }

    @Override
    public Administrador administradorDTOToAdministrador(AdministradorDTO administradorDTO) {
        if ( administradorDTO == null ) {
            return null;
        }

        Administrador administrador = new Administrador();

        administrador.setIdAdministrador( administradorDTO.getIdAdministrador() );
        administrador.setNombreadministrador( administradorDTO.getNombreadministrador() );

        return administrador;
    }

    @Override
    public List<AdministradorDTO> listAdministradorToListAdministradorDTO(List<Administrador> administradors) {
        if ( administradors == null ) {
            return null;
        }

        List<AdministradorDTO> list = new ArrayList<AdministradorDTO>( administradors.size() );
        for ( Administrador administrador : administradors ) {
            list.add( administradorToAdministradorDTO( administrador ) );
        }

        return list;
    }

    @Override
    public List<Administrador> listAdministradorDTOToListAdministrador(List<AdministradorDTO> administradorDTOs) {
        if ( administradorDTOs == null ) {
            return null;
        }

        List<Administrador> list = new ArrayList<Administrador>( administradorDTOs.size() );
        for ( AdministradorDTO administradorDTO : administradorDTOs ) {
            list.add( administradorDTOToAdministrador( administradorDTO ) );
        }

        return list;
    }
}
