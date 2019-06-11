package bit5.team2.account.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RegisterInput1 {
	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{9,12})")
	@Size(min = 9, max = 12)
	private String phoneNumber;

    @NotNull
	private boolean oa;
}
