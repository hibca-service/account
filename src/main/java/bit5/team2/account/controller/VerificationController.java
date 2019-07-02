package bit5.team2.account.controller;

import bit5.team2.account.model.input.InVerificationPhone;
import bit5.team2.account.model.output.OutLoginMobile;
import bit5.team2.account.model.output.OutLoginWeb;
import bit5.team2.account.service.AdminLoginService;
import bit5.team2.account.service.LoginService;
import bit5.team2.account.service.VerificationService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RefreshScope
@RequestMapping("/verification")
@CrossOrigin(origins = "*")
public class VerificationController extends BaseController {
	@Autowired
	VerificationService verificationService;

	@Autowired
	LoginService loginService;

	@Autowired
	AdminLoginService adminLoginService;

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

	@GetMapping(value = "/auth-user")
	public @ResponseBody ResultEntity<Object> reAuthUser(HttpServletRequest request) {
		ResultEntity<Object> err = this.unauthorizedUser(request);
		if (err == null) {    //	auth user success
			OutLoginMobile output = loginService.generateToken(this.dataUser.get("userId").toString(), request.getHeader("Authorization"));
			if (output != null) {
				return this.success(output);
			} else {
				return this.failed();
			}
		} else {
			return this.failed();
		}
	}

	@GetMapping(value = "/auth-admin")
	public @ResponseBody ResultEntity<Object> reAuthAdmin(HttpServletRequest request) {
		ResultEntity<Object> err = this.unauthorizedAdmin(request);
		if (err == null) {	//	auth admin success
			OutLoginWeb output = adminLoginService.reLogin(this.dataAdmin.get("adminId").toString(),request.getHeader("Authorization"));
			if (output != null) {
				return this.success(output);
			} else {
				return this.failed();
			}
		} else {
			return this.failed();
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
