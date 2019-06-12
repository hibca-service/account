package bit5.team2.account.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profile {
	private String username;
	private String id;
	private String name;
	private String status;
	private int followers;
	private int following;
}
