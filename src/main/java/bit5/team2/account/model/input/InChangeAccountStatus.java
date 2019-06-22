package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class InChangeAccountStatus {
	@NotEmpty
	private String userId;

	private boolean isActive;
}
