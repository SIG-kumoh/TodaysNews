package com.sig.todaysnews.controller;

import com.sig.todaysnews.dto.UserDto;
import com.sig.todaysnews.sevice.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;

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

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(
            @Valid @RequestBody UserDto userDto
    ) {
        Logger logger = LoggerFactory.getLogger(UserController.class);
        logger.debug(userDto.getNickname());
        return ResponseEntity.ok(userService.signup(userDto));
    }

    @PostMapping("/dup-check")
    public ResponseEntity<Void> dupCheck(
            @Valid @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok().build();
    }
}
