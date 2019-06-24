package bit5.team2.account.controller;

import bit5.team2.library.base.BaseController;
import bit5.team2.library.output.ResultEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck extends BaseController {
    @GetMapping("/account")
    public ResultEntity<Object> success1() {
        return this.success(null);
    }

    @GetMapping("/verification")
    public ResultEntity<Object> success2() {
        return this.success(null);
    }
}
