package bit5.team2.account.controller;

import bit5.team2.account.model.input.InChangePassword;
import bit5.team2.account.model.input.InChangeProfile;
import bit5.team2.account.service.ProfileService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.output.ResultEntity;
import bit5.team2.library.view.ProfileView;
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
public class ProfileController extends BaseController {
    @Autowired
    ProfileService profileService;

    @GetMapping(value = "/get-profile")
    public @ResponseBody
    ResultEntity<Object> getProfile(HttpServletRequest request,
                                    @RequestParam Optional<String> username) {
        ResultEntity<Object> err = this.unauthorizedUser(request);
        if (err != null) {
            return err;
        }

        ProfileView output = profileService.getProfile(username.orElse((String) this.dataUser.get("username")));

        if (output == null) {
            return this.failed();
        } else {
            return this.success(output);
        }
    }

    @PutMapping(value = "/change-profile", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> changeProfile(HttpServletRequest request,
                                       @RequestBody @Valid InChangeProfile input,
                                       BindingResult bindingResult) {
        ResultEntity<Object> err = this.unauthorizedUser(request);
        if (err != null) {
            return err;
        }

        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            if (profileService.changeProfile((String) this.dataUser.get("username"),input)) {
                return this.success(null);
            } else {
                return this.failed();
            }
        } else {
            return errorInput;
        }
    }

    @PutMapping(value = "/change-password", consumes = "application/json")
    public @ResponseBody
    ResultEntity<Object> changePassword(HttpServletRequest request,
                                       @RequestBody @Valid InChangePassword input,
                                       BindingResult bindingResult) {
        ResultEntity<Object> err = this.unauthorizedUser(request);
        if (err != null) {
            return err;
        }

        ResultEntity<Object> errorInput = this.validateInput(bindingResult);
        if (errorInput == null) {
            if (profileService.changePassword((String) this.dataUser.get("username"),input)) {
                return this.success(null);
            } else {
                return this.failed();
            }
        } else {
            return errorInput;
        }
    }
}
