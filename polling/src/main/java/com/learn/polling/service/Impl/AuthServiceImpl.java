
package com.learn.polling.service.Impl;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.learn.polling.domain.Role;
import com.learn.polling.domain.RoleName;
import com.learn.polling.domain.User;
import com.learn.polling.domain.dtos.LoginRequest;
import com.learn.polling.domain.dtos.SignUpRequest;
import com.learn.polling.repository.RoleRepository;
import com.learn.polling.repository.UserRepository;
import com.learn.polling.security.JwtTokenProvider;
import com.learn.polling.service.AuthService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public String login(LoginRequest loginRequest) {

        Authentication authenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticated);

        return tokenProvider.generateToken(authenticated);
    }

    @Override
    public User signUp(SignUpRequest signUpRequest) {

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new IllegalArgumentException("This email is Already Registered");
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new IllegalArgumentException("The UserName is Already Taken");
        }

        User newUser = new User();

        // Optionally set roles or other fields here
        newUser.setName(signUpRequest.getName());
        newUser.setUsername(signUpRequest.getUsername());
        newUser.setEmail(signUpRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        Role userRole = roleRepository.findByRole(RoleName.ROLE_USER)
                .orElseThrow(() -> new EntityNotFoundException("User role is Not set"));
        newUser.setRoles(Collections.singleton(userRole));

        User savedUser = userRepository.save(newUser);
        return savedUser;
    }

 
}
