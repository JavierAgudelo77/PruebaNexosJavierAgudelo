package  com.bankinc.credibanco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankinc.credibanco.domain.Tarjeta;
/**
* @author Javier Ricardo Agudelo
* 
*/

public interface  TarjetaRepository extends JpaRepository<Tarjeta, Long> {

}