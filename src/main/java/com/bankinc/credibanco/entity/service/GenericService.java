package  com.bankinc.credibanco.entity.service;
import java.util.List;
import java.util.Optional;

import com.bankinc.credibanco.exception.BankIncException;

/**
* @author Javier Ricardo Agudelo
* 
*/

public interface GenericService <T,ID>{
	
	 	public List<T> findAll();
	 	
	 	public Optional<T> findById(ID id);

	    public T save(T entity) throws BankIncException;

	    public T update(T entity) throws BankIncException;

	    public void delete(T entity) throws BankIncException;

	    public void deleteById(ID id) throws BankIncException;	    

	    public void validate(T entity) throws BankIncException;
	    
	    public Long count();

}
