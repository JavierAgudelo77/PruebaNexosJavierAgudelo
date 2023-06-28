package com.bankinc.credibanco.mapper;

import com.bankinc.credibanco.domain.Administrador;
import com.bankinc.credibanco.domain.Cliente;
import com.bankinc.credibanco.domain.Producto;
import com.bankinc.credibanco.domain.Tarjeta;
import com.bankinc.credibanco.dto.TarjetaDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TarjetaMapperImpl implements TarjetaMapper {

    @Override
    public TarjetaDTO tarjetaToTarjetaDTO(Tarjeta tarjeta) {
        if ( tarjeta == null ) {
            return null;
        }

        TarjetaDTO tarjetaDTO = new TarjetaDTO();

        tarjetaDTO.setIdAdministradorAdministrador( tarjetaAdministradorIdAdministrador( tarjeta ) );
        tarjetaDTO.setIdclienteCliente( tarjetaClienteIdcliente( tarjeta ) );
        tarjetaDTO.setIdproductoProducto( tarjetaProductoIdproducto( tarjeta ) );
        tarjetaDTO.setEstado( tarjeta.getEstado() );
        tarjetaDTO.setFechacreacion( tarjeta.getFechacreacion() );
        tarjetaDTO.setFechavencimiento( tarjeta.getFechavencimiento() );
        tarjetaDTO.setNumerotarjeta( tarjeta.getNumerotarjeta() );
        tarjetaDTO.setSaldo( tarjeta.getSaldo() );

        return tarjetaDTO;
    }

    @Override
    public Tarjeta tarjetaDTOToTarjeta(TarjetaDTO tarjetaDTO) {
        if ( tarjetaDTO == null ) {
            return null;
        }

        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setAdministrador( tarjetaDTOToAdministrador( tarjetaDTO ) );
        tarjeta.setCliente( tarjetaDTOToCliente( tarjetaDTO ) );
        tarjeta.setProducto( tarjetaDTOToProducto( tarjetaDTO ) );
        tarjeta.setEstado( tarjetaDTO.getEstado() );
        tarjeta.setFechacreacion( tarjetaDTO.getFechacreacion() );
        tarjeta.setFechavencimiento( tarjetaDTO.getFechavencimiento() );
        tarjeta.setNumerotarjeta( tarjetaDTO.getNumerotarjeta() );
        tarjeta.setSaldo( tarjetaDTO.getSaldo() );

        return tarjeta;
    }

    @Override
    public List<TarjetaDTO> listTarjetaToListTarjetaDTO(List<Tarjeta> tarjetas) {
        if ( tarjetas == null ) {
            return null;
        }

        List<TarjetaDTO> list = new ArrayList<TarjetaDTO>( tarjetas.size() );
        for ( Tarjeta tarjeta : tarjetas ) {
            list.add( tarjetaToTarjetaDTO( tarjeta ) );
        }

        return list;
    }

    @Override
    public List<Tarjeta> listTarjetaDTOToListTarjeta(List<TarjetaDTO> tarjetaDTOs) {
        if ( tarjetaDTOs == null ) {
            return null;
        }

        List<Tarjeta> list = new ArrayList<Tarjeta>( tarjetaDTOs.size() );
        for ( TarjetaDTO tarjetaDTO : tarjetaDTOs ) {
            list.add( tarjetaDTOToTarjeta( tarjetaDTO ) );
        }

        return list;
    }

    private Integer tarjetaAdministradorIdAdministrador(Tarjeta tarjeta) {
        if ( tarjeta == null ) {
            return null;
        }
        Administrador administrador = tarjeta.getAdministrador();
        if ( administrador == null ) {
            return null;
        }
        Integer idAdministrador = administrador.getIdAdministrador();
        if ( idAdministrador == null ) {
            return null;
        }
        return idAdministrador;
    }

    private Integer tarjetaClienteIdcliente(Tarjeta tarjeta) {
        if ( tarjeta == null ) {
            return null;
        }
        Cliente cliente = tarjeta.getCliente();
        if ( cliente == null ) {
            return null;
        }
        Integer idcliente = cliente.getIdcliente();
        if ( idcliente == null ) {
            return null;
        }
        return idcliente;
    }

    private Integer tarjetaProductoIdproducto(Tarjeta tarjeta) {
        if ( tarjeta == null ) {
            return null;
        }
        Producto producto = tarjeta.getProducto();
        if ( producto == null ) {
            return null;
        }
        Integer idproducto = producto.getIdproducto();
        if ( idproducto == null ) {
            return null;
        }
        return idproducto;
    }

    protected Administrador tarjetaDTOToAdministrador(TarjetaDTO tarjetaDTO) {
        if ( tarjetaDTO == null ) {
            return null;
        }

        Administrador administrador = new Administrador();

        administrador.setIdAdministrador( tarjetaDTO.getIdAdministradorAdministrador() );

        return administrador;
    }

    protected Cliente tarjetaDTOToCliente(TarjetaDTO tarjetaDTO) {
        if ( tarjetaDTO == null ) {
            return null;
        }

        Cliente cliente = new Cliente();

        cliente.setIdcliente( tarjetaDTO.getIdclienteCliente() );

        return cliente;
    }

    protected Producto tarjetaDTOToProducto(TarjetaDTO tarjetaDTO) {
        if ( tarjetaDTO == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setIdproducto( tarjetaDTO.getIdproductoProducto() );

        return producto;
    }
}
