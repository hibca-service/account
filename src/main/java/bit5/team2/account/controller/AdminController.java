package bit5.team2.account.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit5.team2.account.model.input.InCreateCoAdmin;
import bit5.team2.account.service.AdminService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.output.ResultEntity;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	AdminService adminService;
	
	@PostMapping(value = "/create-co-admin", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> createCoAdmin(@RequestBody @Valid InCreateCoAdmin input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        String createdBy = "shenron";
        if (errorInput == null) {
        	int output = adminService.createCoAdmin(input,createdBy);
        	if (output == 1) {
        		return this.failed();
			} else {
        		return this.success(null);
			}
        }
        else {
            return errorInput;
        }
    }
}
