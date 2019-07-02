package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class InChangeAccountStatus {
	@NotEmpty
	private List<String> userId;

	private boolean isActive;
}
