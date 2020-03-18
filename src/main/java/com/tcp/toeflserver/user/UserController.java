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

        responseBody.put("success", customUserDetailsService.signUp(user));
        return responseBody;
    }
}

