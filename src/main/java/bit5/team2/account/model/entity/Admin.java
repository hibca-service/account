package bit5.team2.account.model.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Admin {
	private String id;
	private String username;
	private String password;
	private boolean superAdmin;
}
