package cn.wangsr.authserver.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WJL
 */
@RestController
@RequestMapping("/user")
public class TestController {
    @GetMapping("/test")
    public Object getCurrentUser(Authentication authentication) {
        return authentication.getPrincipal();
    }

}
