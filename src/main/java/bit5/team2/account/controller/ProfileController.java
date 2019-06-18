package bit5.team2.account.controller;

import bit5.team2.account.service.ProfileService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.input.account.InChangeProfile;
import bit5.team2.library.output.ResultEntity;
import bit5.team2.library.output.account.OutGetProfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/account")
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

        OutGetProfile output = profileService.getProfile(username.orElse((String) this.data.get("username")));

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
            if (profileService.changeProfile((String) this.data.get("username"),input)) {
                return this.success(null);
            } else {
                return this.failed();
            }
        } else {
            return errorInput;
        }
    }
}
