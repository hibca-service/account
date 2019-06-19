package bit5.team2.account.model.input;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InCreateCoAdmin {
	private String adminUsername;
	
	private String adminPassword;
	
	private String adminName;
}
