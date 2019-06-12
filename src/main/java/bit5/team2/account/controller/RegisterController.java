package bit5.team2.account.controller;

import bit5.team2.account.lib.BaseController;

import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.input.InRegister;
import bit5.team2.account.service.RegisterService;

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
    ResultEntity<Object> register(@RequestBody @Valid InRegister input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	int output = registerService.register(input);
        
        	if (output == 0) {
        		return this.success(null);
        	} else if ( output == 1 ) {
        		return this.failed("password");
        	} else if ( output == 2 ) {
        		return this.failed("email, phone number, username already taken");
        	} else if ( output == 3 ) {
        		return this.failed("email, phone number already taken");
        	} else if ( output == 4 ) {
        		return this.failed("email, username already taken");
        	} else if ( output == 5 ) {
        		return this.failed("phone number, username already taken");
        	} else if ( output == 6 ) {
        		return this.failed("email already taken");
        	} else if ( output == 7 ) {
        		return this.failed("phone number already taken");
        	} else if ( output == 8 ) {
        		return this.failed("username already taken");
        	} else if ( output == 9 ) {
        		return this.failed("Phone number already verified");
        	} else if ( output == 10 ) {
        		return this.failed("Email already verified");
        	}
        	return this.success(null);
        }
        else {
            return errorInput;
        }
    }

    
}

//@PostMapping(value = "/register-step-1", consumes = "application/json")
//public @ResponseBody
//ResultEntity<Object> registerStep1(@RequestBody @Valid InRegister1 input, BindingResult bindingResult){
//    ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//    if (errorInput == null) {
//    	String output = registerService.registerStep1(input);
//    	if (output == null) {
//            return this.failed();
//        } else {
//            return this.success(output);
//        }
//    }
//    else {
//        return errorInput;
//    }
//}
//
//@PostMapping(value = "/register-step-2", consumes = "application/json")
//public @ResponseBody
//ResultEntity<Object> registerStep2(@RequestBody @Valid InRegister2 input, BindingResult bindingResult){
//    ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//    if (errorInput == null) {
//    	String output = new String();
//    	output = registerService.registerStep2(input);
//    	if (output == null) {
//            return this.failed();
//        } else {
//            return this.success(output);
//        }
//    }
//    else {
//        return errorInput;
//    }
//}
//
//@PostMapping(value = "/register-step-3", consumes = "application/json")
//public @ResponseBody
//ResultEntity<Object> registerStep3(@RequestBody @Valid InRegister3 input, BindingResult bindingResult){
//    ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//    if (errorInput == null) {
//    	registerService.registerStep3(input);
//        return this.success(null);
//    }
//    else {
//        return errorInput;
//    }
//}