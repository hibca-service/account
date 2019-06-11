package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class Register2 {
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
}
