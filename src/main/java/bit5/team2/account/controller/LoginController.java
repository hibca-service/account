package bit5.team2.account.controller;

import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.input.InLogin;
import bit5.team2.account.model.output.OutLogin;
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
    ResultEntity<Object> login(@RequestBody @Valid InLogin input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            OutLogin output = loginService.login(input.getUsername(),input.getPassword());
            if (output == null) {
                return this.failed();
            } else {
                return this.success(output);
            }
        }
        else {
            return errorInput;
        }
    }
}
