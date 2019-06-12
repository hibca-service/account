package bit5.team2.account.model.input;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeProfileInput {
	@NotEmpty
	private String username; //buat cari data user yang mau diganti dari database
	private String password;
	private String name;
	private String email;
	private String phoneNumber;
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date dateOfBirth;
	private String purpose;
	private boolean oa;
}