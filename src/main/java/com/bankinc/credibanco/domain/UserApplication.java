package  com.bankinc.credibanco.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
* @author Javier Ricardo Agudelo
* 
*/


@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserApplication {

	private String username;

	private String password;

}
