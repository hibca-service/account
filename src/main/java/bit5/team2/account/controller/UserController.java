package bit5.team2.account.controller;

import bit5.team2.account.service.impl.UserServiceImpl;
import bit5.team2.library.base.BaseController;
import bit5.team2.library.base.PagingProperties;
import bit5.team2.library.entity.User;
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
    UserServiceImpl userService;

    @GetMapping("/get-user")
    public ResultEntity<Object> getUser(HttpServletRequest request,
                                        PagingProperties<User> pagingProperties) {
        ResultEntity<Object> err = this.unauthorizedUser(request);
        if (err != null) {
            return err;
        }

        try {
            PagingProperties<User> users = userService.getUser(pagingProperties);
            if (users == null) {
                return this.empty();
            } else {
                return this.success(users);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return this.error(e.getMessage());
        }
    }
}
