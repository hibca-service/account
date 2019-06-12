package bit5.team2.account.service;

import bit5.team2.account.model.input.InRegister;
import bit5.team2.account.model.input.InRegister1;
import bit5.team2.account.model.input.InRegister2;
import bit5.team2.account.model.input.InRegister3;

public interface RegisterService {
//	String registerStep1(InRegister1 input);
//	String registerStep2(InRegister2 input);
//	void registerStep3(InRegister3 input);
	public int register (InRegister input);
}
