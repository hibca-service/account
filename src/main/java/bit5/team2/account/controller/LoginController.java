package bit5.team2.account.controller;

import bit5.team2.account.Login;
import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.output.Token;
import bit5.team2.account.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/account")
public class LoginController extends BaseController {
    @Autowired
    LoginService loginService;

    @PostMapping(value = "/login", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> login(@RequestBody @Valid Login input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            loginService.set();

            Token token = loginService.login(input.getUsername(),input.getPassword());
            if (token == null) {
                return this.failed();
            }
            else {
                return this.success(token);
            }
        }
        else return errorInput;

    }
}
