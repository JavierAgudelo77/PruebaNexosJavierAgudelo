package com.bankinc.credibanco.mapper;

import com.bankinc.credibanco.domain.Cliente;
import com.bankinc.credibanco.dto.ClienteDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public ClienteDTO clienteToClienteDTO(Cliente cliente) {
        if ( cliente == null ) {
            return null;
        }

        ClienteDTO clienteDTO = new ClienteDTO();

        clienteDTO.setIdcliente( cliente.getIdcliente() );
        clienteDTO.setPrimerapellido( cliente.getPrimerapellido() );
        clienteDTO.setPrimernombre( cliente.getPrimernombre() );
        clienteDTO.setSegundoapellido( cliente.getSegundoapellido() );
        clienteDTO.setSegundonombre( cliente.getSegundonombre() );

        return clienteDTO;
    }

    @Override
    public Cliente clienteDTOToCliente(ClienteDTO clienteDTO) {
        if ( clienteDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setIdcliente( clienteDTO.getIdcliente() );
        cliente.setPrimerapellido( clienteDTO.getPrimerapellido() );
        cliente.setPrimernombre( clienteDTO.getPrimernombre() );
        cliente.setSegundoapellido( clienteDTO.getSegundoapellido() );
        cliente.setSegundonombre( clienteDTO.getSegundonombre() );

        return cliente;
    }

    @Override
    public List<ClienteDTO> listClienteToListClienteDTO(List<Cliente> clientes) {
        if ( clientes == null ) {
            return null;
        }

        List<ClienteDTO> list = new ArrayList<ClienteDTO>( clientes.size() );
        for ( Cliente cliente : clientes ) {
            list.add( clienteToClienteDTO( cliente ) );
        }

        return list;
    }

    @Override
    public List<Cliente> listClienteDTOToListCliente(List<ClienteDTO> clienteDTOs) {
        if ( clienteDTOs == null ) {
            return null;
        }

        List<Cliente> list = new ArrayList<Cliente>( clienteDTOs.size() );
        for ( ClienteDTO clienteDTO : clienteDTOs ) {
            list.add( clienteDTOToCliente( clienteDTO ) );
        }

        return list;
    }
}
