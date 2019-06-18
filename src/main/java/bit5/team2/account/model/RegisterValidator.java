package bit5.team2.account.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class RegisterValidator {
    private String userId;
    private ArrayList<String> invalid;
}
