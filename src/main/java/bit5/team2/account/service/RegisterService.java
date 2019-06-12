package bit5.team2.account.service;

import bit5.team2.account.model.input.Register1;
import bit5.team2.account.model.input.Register2;
import bit5.team2.account.model.input.Register3;

public interface RegisterService {
	String registerStep1(Register1 input);
	String registerStep2(Register2 input);
	void registerStep3(Register3 input);
}
