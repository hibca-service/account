package com.bit5team2.dummy.entity;

public class BaseController {
    protected ResultEntity<Object> checkToken(String authorization) {
        if (authorization == null || authorization.equals("")) {
            return new ResultEntity<>(null,ErrorCode.E02);
        }
        return null;
    }
}
