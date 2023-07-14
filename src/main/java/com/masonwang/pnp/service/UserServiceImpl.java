package com.masonwang.pnp.service;

import com.masonwang.pnp.repository.UserRepository;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
}
