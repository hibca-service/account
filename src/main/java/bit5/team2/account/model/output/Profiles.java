package bit5.team2.account.model.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Profiles {
    private String userId;
    private String oa;
    private String username;
    private String password;
    private String name;
    private String pathProfilePicture;
    private String dateOfBirth;
    private String cityOfBirth;
    private String userGender;
    private String phoneNumber;
    private String purpose;
    private String oaApprove;
    private String active;
    private String firebaseToken;
    private String firebaseUUID;
    private Long follower;
    private Long following;
    private Boolean alreadyFollowed;
}
