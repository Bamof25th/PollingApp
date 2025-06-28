package com.learn.polling.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn.polling.domain.dtos.CurrentUserDto;
import com.learn.polling.domain.dtos.UserDto;
import com.learn.polling.domain.dtos.UserIdentityAvailabilityDto;
import com.learn.polling.repository.UserRepository;
import com.learn.polling.security.CurrentUser;
import com.learn.polling.security.UserPrincipal;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers() {

        List<UserDto> users = userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserDto.class))
                .collect(Collectors.toList());

        return new ResponseEntity<>(users, HttpStatus.FOUND);
    }

    @GetMapping("/user/me")
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public ResponseEntity<CurrentUserDto> getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        try {
            if (currentUser == null) {
                throw new IllegalStateException("Current user is null");
            }
            CurrentUserDto me = modelMapper.map(currentUser, CurrentUserDto.class);
            return ResponseEntity.ok(me);
        } catch (Exception e) {
            // This will help us debug the issue
            throw new RuntimeException("Error mapping current user: " + e.getMessage(), e);
        }
    }

    @GetMapping("/user/checkUsernameAvailability")
    public ResponseEntity<UserIdentityAvailabilityDto> checkUsernameAvailability(
            @RequestParam("username") String username) {
        boolean check = userRepository.existsByUsername(username);
        return ResponseEntity.ok(new UserIdentityAvailabilityDto(check));
    }

    @GetMapping("/user/checkEmailAvailability")
    public ResponseEntity<UserIdentityAvailabilityDto> checkEmailAvailability(@RequestParam("email") String email) {
        boolean check = userRepository.existsByEmail(email);
        return ResponseEntity.ok(new UserIdentityAvailabilityDto(check));
    }

    @GetMapping("/user/debug")
    public ResponseEntity<String> debugCurrentUser(@CurrentUser UserPrincipal currentUser) {
        try {
            if (currentUser == null) {
                return ResponseEntity.ok("No authenticated user - JWT might be invalid or expired");
            }

            String authorities = currentUser.getAuthorities().stream()
                    .map(auth -> auth.getAuthority())
                    .collect(Collectors.joining(", "));

            return ResponseEntity.ok("User: " + currentUser.getUsername() +
                    ", Authorities: " + authorities +
                    ", ID: " + currentUser.getId() +
                    ", Name: " + currentUser.getName());
        } catch (Exception e) {
            return ResponseEntity.ok("Error in debug: " + e.getMessage());
        }
    }
}
