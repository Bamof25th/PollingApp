package com.learn.polling.service;

import com.learn.polling.domain.User;
import com.learn.polling.domain.dtos.LoginRequest;
import com.learn.polling.domain.dtos.SignUpRequest;

public interface AuthService {

    String login(LoginRequest loginRequest);

    User signUp(SignUpRequest signUpRequest);

}
