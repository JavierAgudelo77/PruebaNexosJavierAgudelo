package  com.bankinc.credibanco.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bankinc.credibanco.domain.Administrador;
/**
* @author Javier Ricardo Agudelo
* 
*/

public interface  AdministradorRepository extends JpaRepository<Administrador, Integer> {

}