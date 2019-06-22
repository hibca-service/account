package bit5.team2.account.controller;

import bit5.team2.account.service.UserService;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.Profile;
import bit5.team2.library.output.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/account")
public class UserController extends BaseController {
    @Autowired
    UserService userService;

    @GetMapping("/get-user")
    public ResultEntity<Object> getUser(HttpServletRequest request,
                                        PagingProperties<Profile> pagingProperties) {
        ResultEntity<Object> errUser = this.unauthorizedUser(request);
        if (errUser != null) {    //	auth user failed
            ResultEntity<Object> errAdmin = this.unauthorizedAdmin(request);
            if (errAdmin != null) {	//	auth admin failed
                return this.unauthorized();
            }
        }

        PagingProperties<Profile> users = userService.getUser(pagingProperties);
        if (users == null) {
            return this.empty();
        } else {
            return this.success(users);
        }
    }
}
