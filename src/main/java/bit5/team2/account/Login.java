package bit5.team2.account;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class Login {
    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
