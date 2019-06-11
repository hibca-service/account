package bit5.team2.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping(value = "/via-email", consumes = "application/json")
	public @ResponseBody
	ResultEntity<Object> viaEmail(@RequestParam String id, BindingResult bindingResult){
	    ResultEntity<Object> errorInput = this.validateInput(bindingResult);
	    if (errorInput == null) {
	    	verificationService.viaEmail(id);
	        return this.success(null);
	    }
	    else return errorInput;
	}
}
