package com.github.woodylic.restut.controller;

import com.github.woodylic.restut.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController
{
    @RequestMapping(value="/greeting", method= RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public BaseResponse<String> greeting() {
        BaseResponse<String> resp = new BaseResponse<String>();
        resp.setSuccess(true);
        resp.setResult("Hello World!");
        return resp;
    }

    @RequestMapping(value="/greeting_post", method= RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public BaseResponse<String> post(@RequestBody BaseResponse baseResponse) {
        System.out.println(baseResponse.getErrorMessage());
        return baseResponse;
    }
}
