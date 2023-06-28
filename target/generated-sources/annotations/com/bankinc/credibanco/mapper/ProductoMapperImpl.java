package com.bankinc.credibanco.mapper;

import com.bankinc.credibanco.domain.Producto;
import com.bankinc.credibanco.dto.ProductoDTO;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.33.0.v20230218-1114, environment: Java 19.0.2 (Oracle Corporation)"
)
@Component
public class ProductoMapperImpl implements ProductoMapper {

    @Override
    public ProductoDTO productoToProductoDTO(Producto producto) {
        if ( producto == null ) {
            return null;
        }

        ProductoDTO productoDTO = new ProductoDTO();

        productoDTO.setDescripcionproducto( producto.getDescripcionproducto() );
        productoDTO.setIdproducto( producto.getIdproducto() );

        return productoDTO;
    }

    @Override
    public Producto productoDTOToProducto(ProductoDTO productoDTO) {
        if ( productoDTO == null ) {
            return null;
        }

        Producto producto = new Producto();

        producto.setDescripcionproducto( productoDTO.getDescripcionproducto() );
        producto.setIdproducto( productoDTO.getIdproducto() );

        return producto;
    }

    @Override
    public List<ProductoDTO> listProductoToListProductoDTO(List<Producto> productos) {
        if ( productos == null ) {
            return null;
        }

        List<ProductoDTO> list = new ArrayList<ProductoDTO>( productos.size() );
        for ( Producto producto : productos ) {
            list.add( productoToProductoDTO( producto ) );
        }

        return list;
    }

    @Override
    public List<Producto> listProductoDTOToListProducto(List<ProductoDTO> productoDTOs) {
        if ( productoDTOs == null ) {
            return null;
        }

        List<Producto> list = new ArrayList<Producto>( productoDTOs.size() );
        for ( ProductoDTO productoDTO : productoDTOs ) {
            list.add( productoDTOToProducto( productoDTO ) );
        }

        return list;
    }
}
