package bit5.team2.account.model.input;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InChangeProfile {
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
