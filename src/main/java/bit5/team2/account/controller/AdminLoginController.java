package bit5.team2.account.controller;

import bit5.team2.account.service.AdminLoginService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.input.account.InLogin;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminLoginController extends BaseController {
    @Autowired
    AdminLoginService adminLoginService;

    @PostMapping(value = "/login", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> login(@RequestBody @Valid InLogin input,
                               BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            Object output = adminLoginService.login(input.getUsername(),input.getPassword());
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
