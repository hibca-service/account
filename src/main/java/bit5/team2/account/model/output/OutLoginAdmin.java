package bit5.team2.account.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutLoginAdmin {
	private String accessToken;
    private String refreshToken;
    private boolean superAdmin;
}
