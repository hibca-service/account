package bit5.team2.account.model.input;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InGetUser {
    private Boolean isOa;
    private Boolean isOaApproved;
    private List<String> userId;
}
