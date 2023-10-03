package com.sig.todaysnews.controller;

import com.sig.todaysnews.dto.UserDto;
import com.sig.todaysnews.sevice.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserDto> getMyUserInfo() {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteMyUserInfo() {
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> updateMyUserInfo(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserInfo(
            @PathVariable String username
    ) {
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<Void> deleteUserInfo(
            @PathVariable String username
    ) {
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{username}")
    public ResponseEntity<Void> updateUserInfo(
            @PathVariable String username,
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok().build();
    }

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
}
