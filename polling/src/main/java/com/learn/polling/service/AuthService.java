package com.learn.polling.service;

import com.learn.polling.domain.dtos.LoginRequest;
import com.learn.polling.domain.dtos.SignUpRequest;
import com.learn.polling.domain.dtos.UserDto;

public interface AuthService {

    String login(LoginRequest loginRequest);

    UserDto signUp(SignUpRequest signUpRequest);

}
