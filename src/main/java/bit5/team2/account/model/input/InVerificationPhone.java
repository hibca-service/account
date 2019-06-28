package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InVerificationPhone {
	private String userId;
	private String firebaseToken;
	private String firebaseUUID;
}
