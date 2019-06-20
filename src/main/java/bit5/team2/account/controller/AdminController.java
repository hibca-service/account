package bit5.team2.account.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bit5.team2.account.model.input.InApproveOA;
import bit5.team2.account.model.input.InCreateCoAdmin;
import bit5.team2.account.model.input.InDeleteCoAdmin;
import bit5.team2.account.model.input.InSuspendAccount;
import bit5.team2.account.model.input.InUpdateCoAdmin;
import bit5.team2.account.model.output.OutReadCoAdmin;
import bit5.team2.account.service.AdminService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import bit5.team2.library.output.ResultEntity;

@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController {
	@Autowired
	AdminService adminService;
	
	//create co admin without authorization
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
	
	@GetMapping("/get-co-admin")
    public ResultEntity<Object> getCoAdmin() {
        if (adminService.readCoAdmin()==null) {
        	return this.empty();
        } else {
        	return this.success(adminService.readCoAdmin());
        }
    }
	
	//update co admin without authorization
	@PostMapping(value = "/update-co-admin", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> updateCoAdmin(@RequestBody @Valid InUpdateCoAdmin input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        String adminId = "61aa2ec5-87cf-4032-a06d-e378649e5864";
        if (errorInput == null) {
        	boolean output = adminService.updateCoAdmin(input,adminId);
        	if (output == false) {
        		return this.failed();
			} else {
        		return this.success(null);
			}
        }
        else {
            return errorInput;
        }
    }
	
	//suspend coadmin without authorization
	@PostMapping(value = "/suspend-co-admin", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> deleteCoAdmin(@RequestBody @Valid InDeleteCoAdmin input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	boolean output = adminService.suspendCoAdmin(input);
        	if (output == false) {
        		return this.failed();
			} else {
        		return this.success(null);
			}
        }
        else {
            return errorInput;
        }
    }
	
	//suspend account without authorization
	@PostMapping(value = "/suspend-account", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> suspendAccount(@RequestBody @Valid InSuspendAccount input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	boolean output = adminService.suspendAccount(input);
        	if (output == false) {
        		return this.failed();
			} else {
        		return this.success(null);
			}
        }
        else {
            return errorInput;
        }
    }
	
	//approve OA without authorization
	@PostMapping(value = "/approve-oa", consumes = "application/json")
    public @ResponseBody
	ResultEntity<Object> approveOA(@RequestBody @Valid InApproveOA input,
                                  BindingResult bindingResult) {
        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
        	boolean output = adminService.approveOA(input);
        	if (output == false) {
        		return this.failed();
			} else {
        		return this.success(null);
			}
        }
        else {
            return errorInput;
        }
    }
	
	//approve OA with authorization
//	@PostMapping(value = "/approve-oa", consumes = "application/json")
//    public @ResponseBody
//	ResultEntity<Object> approveOA(@RequestBody @Valid InApproveOA input,HttpServletRequest request,
//                                  BindingResult bindingResult) {
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//		if (err != null) {
//		    return err;
//		}	
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        if (errorInput == null) {
//        	boolean output = adminService.approveOA(input);
//        	if (output == false) {
//        		return this.failed();
//			} else {
//        		return this.success(null);
//			}
//        }
//        else {
//            return errorInput;
//        }
//    }
	
	//suspend account with authorization
//	@PostMapping(value = "/suspend-account", consumes = "application/json")
//    public @ResponseBody
//	ResultEntity<Object> suspendAccount(@RequestBody @Valid InSuspendAccount input, HttpServletRequest request,
//                                  BindingResult bindingResult) {
//		
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//		if (err != null) {
//		    return err;
//		}
//		
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        if (errorInput == null) {
//        	boolean output = adminService.suspendAccount(input);
//        	if (output == false) {
//        		return this.failed();
//			} else {
//        		return this.success(null);
//			}
//        }
//        else {
//            return errorInput;
//        }
//    }
	
	//suspend coadmin without authorization
//	@PostMapping(value = "/suspend-co-admin", consumes = "application/json")
//    public @ResponseBody
//	ResultEntity<Object> deleteCoAdmin(@RequestBody @Valid InDeleteCoAdmin input, HttpServletRequest request,
//                                  BindingResult bindingResult) {
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//		if (err != null) {
//		    return err;
//		}
//		
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        if (errorInput == null) {
//        	boolean output = adminService.suspendCoAdmin(input);
//        	if (output == false) {
//        		return this.failed();
//			} else {
//        		return this.success(null);
//			}
//        }
//        else {
//            return errorInput;
//        }
//    }
	
	//create co admin with authorization
//	@PostMapping(value = "/create-co-admin", consumes = "application/json")
//    public @ResponseBody
//	ResultEntity<Object> createCoAdmin(@RequestBody @Valid InCreateCoAdmin input, HttpServletRequest request,
//                                  BindingResult bindingResult) {
//		
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//        if (err != null) {
//            return err;
//        }
//        
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        String createdBy = "shenron";
//        if (errorInput == null) {
//        	int output = adminService.createCoAdmin(input,createdBy);
//        	if (output == 1) {
//        		return this.failed();
//			} else {
//        		return this.success(null);
//			}
//        }
//        else {
//            return errorInput;
//        }
//    }
	
	//get co admin with authorization
//	@GetMapping("/get-co-admin")
//    public ResultEntity<Object> getCoAdmin(HttpServletRequest request) {
//        ResultEntity<Object> err = this.unauthorizedUser(request);
//        if (err != null) {
//            return err;
//        }
//        
//        if (adminService.readCoAdmin()==null) {
//			return this.empty();
//		} else {
//			return this.success(adminService.readCoAdmin());
//		}
//    }
		
		//update co admin with authorization
//		@PostMapping(value = "/update-co-admin", consumes = "application/json")
//	    public @ResponseBody
//		ResultEntity<Object> createCoAdmin(@RequestBody @Valid InUpdateCoAdmin input, HttpServletRequest request,
//	                                  BindingResult bindingResult) {
//			ResultEntity<Object> err = this.unauthorizedUser(request);
//	        if (err != null) {
//	            return err;
//	        }
//			
//	        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//	        String adminId = "61aa2ec5-87cf-4032-a06d-e378649e5864";
//	        if (errorInput == null) {
//	        	boolean output = adminService.updateCoAdmin(input,adminId);
//	        	if (output == false) {
//	        		return this.failed();
//				} else {
//	        		return this.success(null);
//				}
//	        }
//	        else {
//	            return errorInput;
//	        }
//	    }
}
