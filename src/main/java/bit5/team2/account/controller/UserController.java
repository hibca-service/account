package bit5.team2.account.controller;

import bit5.team2.account.model.input.InChangeAccountStatus;
import bit5.team2.account.model.input.InChangeOAStatus;
import bit5.team2.account.model.output.Profiles;
import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/account")
@CrossOrigin(origins = "*")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @PostMapping("/get-user")
    public ResultEntity<Object> getUser(HttpServletRequest request,
                                        PagingProperties<Profiles> pagingProperties) {
        ResultEntity<Object> err = this.unauthorizedUser(request);
        if (err != null) {
            return err;
        }
        return this.success(userService.getUser(pagingProperties,this.dataUser.get("userId").toString()));
    }

    @GetMapping("/get-user")
    public ResultEntity<Object> getUsers(HttpServletRequest request,
                                         PagingProperties<User> pagingProperties,
                                         @RequestParam Optional<Boolean> isOa,
                                         @RequestParam Optional<Boolean> isOaApproved,
                                         @RequestParam Optional<Boolean> isActive) {
        ResultEntity<Object> errAdmin = this.unauthorizedAdmin(request);
        if (errAdmin != null) {
            return this.unauthorized();
        }

        return this.success(userService.getUser(pagingProperties,isOa,isOaApproved,isActive));
    }

    @PutMapping(value = "/change-account-status", consumes = "application/json")
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

    @PutMapping(value = "/change-oa-status", consumes = "application/json")
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
}
