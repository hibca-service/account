package bit5.team2.account.model.output;

import bit5.team2.library.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutGetProfile extends User {
	private Integer follower;
	private Integer following;

	public OutGetProfile init(User user) {
		this.setUserId(user.getUserId());
		this.setOa(user.getOa());
		this.setUsername(user.getUsername());
		this.setPassword(user.getPassword());
		this.setName(user.getName());
		this.setDateOfBirth(user.getDateOfBirth());
		this.setCityOfBirth(user.getCityOfBirth());
		this.setUserGender(user.getUserGender());
		this.setPhoneNumber(user.getPhoneNumber());
		this.setPurpose(user.getPurpose());
		this.setFirebaseToken(user.getFirebaseToken());
		this.setFirebaseUUID(user.getFirebaseUUID());
		this.setEmail(user.getEmail());
		return this;
	}
}
