package bit5.team2.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.input.ChangeProfile;
import bit5.team2.account.service.ProfileService;

@RestController
@RequestMapping("/account")
public class ProfileController extends BaseController{
	@Autowired
	ProfileService profileService;
	
	@PostMapping(value = "/change-profile", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> changeProfile(@RequestBody @Valid ChangeProfile input, BindingResult bindingResult){
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	int output = profileService.changeProfile(input);
        	if (output == 0) {
				return this.success(null);
			} else {
				return this.failed();
			}
        }
        else {
        	return errorInput;
		}
    }
}
