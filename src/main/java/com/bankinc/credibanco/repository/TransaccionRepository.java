package  com.bankinc.credibanco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankinc.credibanco.domain.Transaccion;
/**
* @author Javier Ricardo Agudelo
* 
*/


public interface  TransaccionRepository extends JpaRepository<Transaccion, Integer> {

}