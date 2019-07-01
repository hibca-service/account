package bit5.team2.account.service;

import bit5.team2.account.model.input.InVerificationPhone;

public interface VerificationService {
//	int viaEmail(String id);
	boolean viaPhone(InVerificationPhone id);
}
