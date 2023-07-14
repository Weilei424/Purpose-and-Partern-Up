package com.masonwang.pnp.web;

import com.masonwang.pnp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;
}
