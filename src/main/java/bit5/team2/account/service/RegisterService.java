package bit5.team2.account.service;

import bit5.team2.account.model.RegisterValidator;
import bit5.team2.library.input.account.InRegister;

public interface RegisterService {
	RegisterValidator register(InRegister input);
}
