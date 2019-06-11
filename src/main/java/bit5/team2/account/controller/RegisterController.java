package bit5.team2.account.controller;


import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.RegisterInput;
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
    ResultEntity<Object> register(@RequestBody @Valid RegisterInput input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	registerService.register(input);
            return this.success("ok");
        }
        else return errorInput;
    }
}
