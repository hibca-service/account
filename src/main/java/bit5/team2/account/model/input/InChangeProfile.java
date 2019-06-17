package bit5.team2.account.model.input;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InChangeProfile {
	@NotEmpty
	private String username; //buat cari data user yang mau diganti dari database

	private String pathProfilePicture;

	private String password;

	private String name;

//	private String phoneNumber;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private String dateOfBirth;

	private String cityOfBirth;

	private String purpose;

//	private boolean oa;

//	private String status;
}
