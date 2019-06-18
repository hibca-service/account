package bit5.team2.account.controller;

import bit5.team2.account.model.RegisterValidator;
import bit5.team2.account.service.RegisterService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.input.account.InRegister;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class RegisterController extends BaseController {
    @Autowired
    RegisterService registerService;
    
    @PostMapping(value = "/register", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> register(@RequestBody @Valid InRegister input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	RegisterValidator validator = registerService.register(input);
        	if (validator.getUserId() == null) {
        		return this.failed(validator.getInvalid());
			} else {
        		return this.success(validator.getUserId());
			}
        }
        else {
            return errorInput;
        }
    }
}
