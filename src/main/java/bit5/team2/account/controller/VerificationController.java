package bit5.team2.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.model.input.InVerificationPhone;
import bit5.team2.account.service.VerificationService;

@RestController
@RequestMapping("/verification")
public class VerificationController extends BaseController{
	@Autowired
	VerificationService verificationService;
	
	@GetMapping(value = "/via-email")
	public @ResponseBody ResultEntity<Object> viaEmail(@RequestParam String id) {
		int output = verificationService.viaEmail(id);
    	if (output == 0) {
    		return this.success(null);
    	} else {
    		return this.failed("email already verified");
    	}
	}
	
	@PostMapping(value = "/via-phone")
	public @ResponseBody ResultEntity<Object> viaPhone(@RequestBody InVerificationPhone input, 
			BindingResult bindingResult){
		ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
    	int output = verificationService.viaPhone(input.getId());
	    	if (output == 0) {
	    		return this.success(null);
	    	} else {
	    		return this.failed("phone already verified");
	    	}
        } else {
            return errorInput;
        }
	}
}
