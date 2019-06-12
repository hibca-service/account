package bit5.team2.account.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutGetProfile {
	private String username;
	private String name;
	private String status;
	private int followers;
	private int following;
}
