package com.sig.todaysnews.sevice;

import com.sig.todaysnews.dto.UserDto;
import com.sig.todaysnews.persistence.entity.Authority;
import com.sig.todaysnews.persistence.entity.User;
import com.sig.todaysnews.persistence.repository.UserRepository;
import com.sig.todaysnews.security.util.AuthenticationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public UserDto signup(UserDto userDto) {
        if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        User user = User.builder()
                .username(userDto.getUsername())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .profileImgUrl(userDto.getProfileImgUrl())
                .nickname(userDto.getNickname())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        userRepository.save(user);

        userDto.setPassword("");
        return userDto;
    }

    public boolean dupCheck(String username) {
        return userRepository.findOneWithAuthoritiesByUsername(username).orElse(null) != null;
    }

    public User getMyUserWithAuthorities() {
         return userRepository.findOneWithAuthoritiesByUsername(AuthenticationUtil.getCurrentUsername().get()).get();
    }

    public UserDto getUserWithAuthorities(String username) {
        User user = userRepository.findOneWithAuthoritiesByUsername(username).get();
        UserDto userDto = null;

        if(user != null) {
            userDto = UserDto.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .profileImgUrl(user.getProfileImgUrl())
                    .nickname(user.getNickname())
                    .build();
        }

        return userDto;
    }

    public UserDto updateMyUserWithAuthorities() {
        return null;
    }
    public UserDto updateUserWithAuthorities(UserDto userDto) {
        return null;
    }

    public boolean deleteMyUserWithAuthorities() {
        return false;
    }
    public boolean deleteUserWithAuthorities(String username) {
        return false;
    }
}
