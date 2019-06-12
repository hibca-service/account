package bit5.team2.account.lib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;

import java.util.Map;

public class BaseController {
    @Value("${show_error_input}")
    Boolean showError;

    public Map<String, Object> data = null;

    protected ResultEntity<Object> checkToken(String authorization) {
        if (authorization == null || authorization.equals("")) {
            return this.unauthorized();
        } else {
            JWT jwt = new JWT();
            this.data = jwt.getdata(authorization);
            return null;
        }
    }

    protected ResultEntity<Object> validateInput(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object err = null;

            if (this.showError) {
                err = bindingResult.getFieldErrors();
            }

            return this.invalid(err);
        }
        return null;
    }

    protected ResultEntity<Object> success(Object o) {
        return new ResultEntity<>(o,ErrorCode.E00);
    }

    protected ResultEntity<Object> empty() {
        return new ResultEntity<>(null,ErrorCode.E01);
    }

    protected ResultEntity<Object> unauthorized() {
        return new ResultEntity<>(null,ErrorCode.E02);
    }

    protected ResultEntity<Object> failed() {
        return new ResultEntity<>(null,ErrorCode.E03);
    }

    protected ResultEntity<Object> invalid(Object o) {
        return new ResultEntity<>(o,ErrorCode.E04);
    }

    protected ResultEntity<Object> error() {
        return new ResultEntity<>(null,ErrorCode.E99);
    }

    protected ResultEntity<Object> error(String error) {
        return new ResultEntity<>(null,ErrorCode.E99,error);
    }
}
