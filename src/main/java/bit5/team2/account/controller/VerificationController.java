package bit5.team2.account.controller;

import bit5.team2.account.model.input.InVerificationPhone;
import bit5.team2.account.service.VerificationService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verification")
public class VerificationController extends BaseController {
	@Autowired
	VerificationService verificationService;

	@PostMapping(value = "/via-phone")
	public @ResponseBody ResultEntity<Object> viaPhone(@RequestBody InVerificationPhone input,
													   BindingResult bindingResult) {
		ResultEntity<Object> errorInput = this.validateInput(bindingResult);
		if (errorInput == null) {
			if (verificationService.viaPhone(input)) {
				return this.success(null);
			} else {
				return this.failed(null);
			}
		} else {
			return errorInput;
		}
	}
	
//	@GetMapping(value = "/via-email")
//	public @ResponseBody
//	ResultEntity<Object> viaEmail(@RequestParam String id) {
//		System.out.println("="+id+"=");
//		int output = verificationService.viaEmail(id);
//    	if (output == 0) {
//    		return this.success(null);
//    	} else {
//    		return this.failed("email already verified");
//    	}
//	}
}
