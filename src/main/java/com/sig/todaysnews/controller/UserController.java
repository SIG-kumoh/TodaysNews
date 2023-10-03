package com.sig.todaysnews.controller;

import com.sig.todaysnews.dto.UserDto;
import com.sig.todaysnews.sevice.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    public ResponseEntity<Void> dupCheck(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<UserDto> getMyUserInfo() {
        return ResponseEntity.ok(null);
    }

    public ResponseEntity<UserDto> getUserInfo(

    ) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteMyUserInfo() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> deleteUserInfo(

    ) {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> updateMyUserInfo() {
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> updateUserInfo(

    ) {
        return ResponseEntity.ok().build();
    }
}
