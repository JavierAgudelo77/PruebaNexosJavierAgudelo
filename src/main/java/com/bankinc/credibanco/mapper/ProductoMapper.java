package com.bankinc.credibanco.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bankinc.credibanco.domain.Producto;
import com.bankinc.credibanco.dto.ProductoDTO;



/**
* @author Javier Ricardo Agudelo
* 
*/

@Mapper
public interface ProductoMapper {

	
	public ProductoDTO productoToProductoDTO(Producto producto);
	
	
	public Producto productoDTOToProducto(ProductoDTO productoDTO);
	
	public List<ProductoDTO> listProductoToListProductoDTO(List<Producto> productos);

	public List<Producto> listProductoDTOToListProducto(List<ProductoDTO> productoDTOs);
}
