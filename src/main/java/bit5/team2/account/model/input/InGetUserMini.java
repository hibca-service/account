package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;

@Getter
@Setter
public class InGetUserMini {
    @NotEmpty
    private ArrayList<String> userId;
}
