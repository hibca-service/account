package bit5.team2.account.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class User {
    private String id;
    private String name;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private Date dateOfBirth;
    private String purpose;
    private boolean oa;
    private boolean emailVerified;
    private boolean phoneVerified;
}
