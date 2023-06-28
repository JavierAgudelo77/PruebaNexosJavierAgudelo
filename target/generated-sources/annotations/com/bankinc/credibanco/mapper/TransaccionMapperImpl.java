package com.bankinc.credibanco.mapper;

import com.bankinc.credibanco.domain.Tarjeta;
import com.bankinc.credibanco.domain.Transaccion;
import com.bankinc.credibanco.dto.TransaccionDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class TransaccionMapperImpl implements TransaccionMapper {

    @Override
    public TransaccionDTO transaccionToTransaccionDTO(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }

        TransaccionDTO transaccionDTO = new TransaccionDTO();

        transaccionDTO.setNumerotarjetaTarjeta( transaccionTarjetaNumerotarjeta( transaccion ) );
        transaccionDTO.setEstado( transaccion.getEstado() );
        transaccionDTO.setFechatransaccion( transaccion.getFechatransaccion() );
        transaccionDTO.setIdcomercio( transaccion.getIdcomercio() );
        transaccionDTO.setIdtransaccion( transaccion.getIdtransaccion() );
        transaccionDTO.setMonto( transaccion.getMonto() );

        return transaccionDTO;
    }

    @Override
    public Transaccion transaccionDTOToTransaccion(TransaccionDTO transaccionDTO) {
        if ( transaccionDTO == null ) {
            return null;
        }

        Transaccion transaccion = new Transaccion();

        transaccion.setTarjeta( transaccionDTOToTarjeta( transaccionDTO ) );
        transaccion.setEstado( transaccionDTO.getEstado() );
        transaccion.setFechatransaccion( transaccionDTO.getFechatransaccion() );
        transaccion.setIdcomercio( transaccionDTO.getIdcomercio() );
        transaccion.setIdtransaccion( transaccionDTO.getIdtransaccion() );
        transaccion.setMonto( transaccionDTO.getMonto() );

        return transaccion;
    }

    @Override
    public List<TransaccionDTO> listTransaccionToListTransaccionDTO(List<Transaccion> transaccions) {
        if ( transaccions == null ) {
            return null;
        }

        List<TransaccionDTO> list = new ArrayList<TransaccionDTO>( transaccions.size() );
        for ( Transaccion transaccion : transaccions ) {
            list.add( transaccionToTransaccionDTO( transaccion ) );
        }

        return list;
    }

    @Override
    public List<Transaccion> listTransaccionDTOToListTransaccion(List<TransaccionDTO> transaccionDTOs) {
        if ( transaccionDTOs == null ) {
            return null;
        }

        List<Transaccion> list = new ArrayList<Transaccion>( transaccionDTOs.size() );
        for ( TransaccionDTO transaccionDTO : transaccionDTOs ) {
            list.add( transaccionDTOToTransaccion( transaccionDTO ) );
        }

        return list;
    }

    private Long transaccionTarjetaNumerotarjeta(Transaccion transaccion) {
        if ( transaccion == null ) {
            return null;
        }
        Tarjeta tarjeta = transaccion.getTarjeta();
        if ( tarjeta == null ) {
            return null;
        }
        Long numerotarjeta = tarjeta.getNumerotarjeta();
        if ( numerotarjeta == null ) {
            return null;
        }
        return numerotarjeta;
    }

    protected Tarjeta transaccionDTOToTarjeta(TransaccionDTO transaccionDTO) {
        if ( transaccionDTO == null ) {
            return null;
        }

        Tarjeta tarjeta = new Tarjeta();

        tarjeta.setNumerotarjeta( transaccionDTO.getNumerotarjetaTarjeta() );

        return tarjeta;
    }
}
