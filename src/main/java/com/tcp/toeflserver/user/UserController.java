package com.tcp.toeflserver.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final CustomUserDetailsService customUserDetailsService;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public HashMap<String, Object> signUp(@RequestBody CustomUser user){
        HashMap<String, Object> responseBody = new HashMap();
        int signUpResult = customUserDetailsService.signUpUser(user);

        responseBody.put("success", isSuccess(signUpResult));
        return responseBody;
    }

    private boolean isSuccess(int result) {
        return result > 0 ? true : false;
    }
}

