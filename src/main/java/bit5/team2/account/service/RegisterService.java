package bit5.team2.account.service;

import bit5.team2.account.model.RegisterInput1;
import bit5.team2.account.model.RegisterInput2;
import bit5.team2.account.model.RegisterInput3;

public interface RegisterService {
	public String registerStep1(RegisterInput1 input);
	public String registerStep2(RegisterInput2 input);
	public void registerStep3(RegisterInput3 input);
}
