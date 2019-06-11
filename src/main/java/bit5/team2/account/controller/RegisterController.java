package bit5.team2.account.controller;


import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.input.Register1;
import bit5.team2.account.model.input.Register2;
import bit5.team2.account.model.input.Register3;
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

    @PostMapping(value = "/register-step-1", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> registerStep1(@RequestBody @Valid Register1 input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	String output = new String();
        	output = registerService.registerStep1(input);
        	if (output == null)
        		return this.failed();
        	else
        		return this.success(output);
        }
        else return errorInput;
    }
    
    @PostMapping(value = "/register-step-2", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> registerStep2(@RequestBody @Valid Register2 input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	String output = new String();
        	output = registerService.registerStep2(input);
        	if (output == null)
        		return this.failed();
        	else
        		return this.success(output);
        }
        else return errorInput;
    }
    
    @PostMapping(value = "/register-step-3", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> registerStep3(@RequestBody @Valid Register3 input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	registerService.registerStep3(input);
            return this.success(null);
        }
        else return errorInput;
    }
}
