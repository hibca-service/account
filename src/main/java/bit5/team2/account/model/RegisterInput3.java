package bit5.team2.account.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.sql.Date;

@Getter
@Setter
public class RegisterInput3 {
	@NotEmpty
	private String id;
	
	@NotEmpty
	private String name;

    @JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;

	private String purpose;
}
