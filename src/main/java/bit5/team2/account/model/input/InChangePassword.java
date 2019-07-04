package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InChangePassword {

	private String oldPassword;

	private String newPassword;
}
