package  com.bankinc.credibanco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankinc.credibanco.domain.Producto;
/**
* @author Javier Ricardo Agudelo
* 
*/

public interface  ProductoRepository extends JpaRepository<Producto, Integer> {

}