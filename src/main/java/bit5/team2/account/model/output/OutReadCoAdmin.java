package bit5.team2.account.model.output;

import java.sql.Timestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutReadCoAdmin {
	private String adminId;
	private String adminUsername;
	private String adminName;
	private String createdBy;
	private Timestamp createdDate;
	
}
