package bit5.team2.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit5.team2.account.lib.BaseController;
import bit5.team2.account.lib.ResultEntity;
import bit5.team2.account.service.VerificationService;

@RestController
@RequestMapping("/verification")
public class VerificationController extends BaseController{
	@Autowired
	VerificationService verificationService;
	
	@GetMapping(value = "/via-email")
	public @ResponseBody
	ResultEntity<Object> viaEmail(@RequestParam String id, BindingResult bindingResult){
	    ResultEntity<Object> errorInput = this.validateInput(bindingResult);
	    if (errorInput == null) {
	    	int output = verificationService.viaEmail(id);
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
