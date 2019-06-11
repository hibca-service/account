package bit5.team2.account.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterInput2 {
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;
}
