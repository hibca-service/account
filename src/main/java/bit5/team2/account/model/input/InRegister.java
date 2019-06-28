package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
public class InRegister {
	@NotEmpty
	private String username;

	@NotEmpty
	private String password;

//	@Email
//	@Nullable
//	private String email;

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{9,12})")
	@Size(min = 9, max = 12)
	private String phoneNumber;

    @NotNull
    @NotEmpty
	@Size(max = 1)
	private String oa;
}
