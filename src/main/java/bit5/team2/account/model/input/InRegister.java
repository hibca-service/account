package bit5.team2.account.model.input;

import java.sql.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InRegister {
	@Email
	@NotEmpty
	private String email;

	@NotEmpty
	@Pattern(regexp = "(^$|[0-9]{9,12})")
	@Size(min = 9, max = 12)
	private String phoneNumber;

    @NotNull
    @AssertTrue
	private boolean oa;
    
    @NotEmpty
	private String username;

	@NotEmpty
	private String password;
	
	//////////////////
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
}
