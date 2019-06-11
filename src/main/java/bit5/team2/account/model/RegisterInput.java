package bit5.team2.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
public class RegisterInput {
	@NotEmpty
	private String name;

	@NotEmpty
	private String username;

	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	private String password;

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{9,12})")
	@Size(min = 9, max = 12)
	private String phoneNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String purpose;

    @NotNull
	private boolean oa;
}
