package  com.bankinc.credibanco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankinc.credibanco.domain.Cliente;
/**
* @author Javier Ricardo Agudelo
* 
*/

public interface  ClienteRepository extends JpaRepository<Cliente, Integer> {

}