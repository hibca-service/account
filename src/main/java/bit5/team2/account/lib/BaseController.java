package bit5.team2.account.lib;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
    @Value("${show_error_input}")
    Boolean showError;
    
    public Map<String, Object> data = null;

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResultEntity<Object> handleErrorParam(MissingServletRequestParameterException e){
    	return this.failed();
    }
    
    protected ResultEntity<Object> unauthorizedUser(HttpServletRequest request){
    	String authorization = request.getHeader("Authorization");
    	if (this.tokenValid(authorization)) {
    		return null;
    	} else {
    		return this.unauthorized();
    	}
    }
    
    protected boolean tokenValid(String authorization) {
        if (authorization == null || authorization.equals("")) {
            return false;
        } else {
            JWT jwt = new JWT();
            this.data = jwt.getdata(authorization);
            return ! (this.data == null);
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
    
    protected ResultEntity<Object> failed(Object o) {
        return new ResultEntity<>(o,ErrorCode.E03);
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
