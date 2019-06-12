package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class Login {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
