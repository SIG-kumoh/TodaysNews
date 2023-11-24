package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.UserDto;
import com.sig.todaysnews.persistence.entity.User;
import com.sig.todaysnews.persistence.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

public interface UserService {
    default UserDto entityToDto(User user) {
        UserDto userDto = UserDto.builder()
                .username(user.getUsername())
                .password("")
                .profileImgUrl(user.getProfileImgUrl())
                .nickname(user.getNickname())
                .build();

        return userDto;
    }
}