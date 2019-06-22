package bit5.team2.account.controller;

import bit5.team2.account.model.input.InChangeOAStatus;
import bit5.team2.account.model.input.InChangeAccountStatus;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.Profile;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @GetMapping("/get-user")
    public ResultEntity<Object> getUser(HttpServletRequest request,
                                        PagingProperties<Profile> pagingProperties,
                                        Optional<List<String>> usedIds) {
        ResultEntity<Object> errUser = this.unauthorizedUser(request);
        if (errUser != null) {    //	auth user failed
            ResultEntity<Object> errAdmin = this.unauthorizedAdmin(request);
            if (errAdmin != null) {	//	auth admin failed
                return this.unauthorized();
            }
        }

        PagingProperties<Profile> users;
        if (usedIds.isPresent()) {
            users = userService.getUser(pagingProperties,usedIds.get());
        } else {
            users = userService.getUser(pagingProperties);
        }
        if (users == null) {
            return this.empty();
        } else {
            return this.success(users);
        }
    }

    @PostMapping(value = "/change-account-status", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> changeAccountStatus(HttpServletRequest request,
                                             @RequestBody @Valid InChangeAccountStatus input,
                                             BindingResult bindingResult) {
        ResultEntity<Object> err = this.unauthorizedAdmin(request);
        if (err != null) {
            return err;
        }

        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            if (userService.changeAccountStatus(input.getUserId(),input.isActive())) {
                return this.success(null);
            } else {
                return this.failed();
            }
        }
        else {
            return errorInput;
        }
    }

    @PostMapping(value = "/change-oa-status", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> changeOAStatus(HttpServletRequest request,
                                        @RequestBody @Valid InChangeOAStatus input,
                                        BindingResult bindingResult) {
        ResultEntity<Object> err = this.unauthorizedAdmin(request);
        if (err != null) {
            return err;
        }

        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            if (userService.changeOAStatus(input.getUserId(),input.isApprove())) {
                return this.success(null);
            } else {
                return this.failed();
            }
        }
        else {
            return errorInput;
        }
    }

    //approve OA with authorization
//	@PostMapping(value = "/approve-oa", consumes = "application/json")
//    public @ResponseBody
//	ResultEntity<Object> changeOAStatus(@RequestBody @Valid InChangeOAStatus input,HttpServletRequest request,
//                                  BindingResult bindingResult) {
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//		if (err != null) {
//		    return err;
//		}
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        if (errorInput == null) {
//        	boolean output = adminService.changeOAStatus(input);
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
//	ResultEntity<Object> changeAccountStatus(@RequestBody @Valid InChangeAccountStatus input, HttpServletRequest request,
//                                  BindingResult bindingResult) {
//
//		ResultEntity<Object> err = this.unauthorizedUser(request);
//		if (err != null) {
//		    return err;
//		}
//
//        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
//        if (errorInput == null) {
//        	boolean output = adminService.changeAccountStatus(input);
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
}
