package com.sparta.hanghaechabak.service;

import com.sparta.hanghaechabak.exception.ErrorDuplicateUserException;
import com.sparta.hanghaechabak.exception.ErrorNotFoundUserException;
import com.sparta.hanghaechabak.exception.ErrorUtils.ErrorCode;
import com.sparta.hanghaechabak.jwt.JwtTokenProvider;
import com.sparta.hanghaechabak.dto.LoginRequestDto;
import com.sparta.hanghaechabak.dto.SignupRequestDto;
import com.sparta.hanghaechabak.model.User;
import com.sparta.hanghaechabak.model.UserRoleEnum;
import com.sparta.hanghaechabak.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    public void registerUser(
            SignupRequestDto requestDto
    ) {

        // 회원 ID 중복 확인
        String username = requestDto.getNickname();
        String email = requestDto.getEmail();

        Optional<User> found = userRepository.findByNickname(username);
        if (found.isPresent()) {
            throw new ErrorDuplicateUserException(ErrorCode.ERROR_DUPLICATE_ID);
        }
        // email 중복확인 체크
        Optional<User> found_email = userRepository.findByEmail(email);
        if (found_email.isPresent()) {
            throw new ErrorDuplicateUserException(ErrorCode.ERROR_DUPLICATE_EMAIL);
        }

        // 패스워드 암호화
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!requestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        User user = new User(username, password, email, role);

        userRepository.save(user);
    }


    public Boolean nicknameDuplicate(
            String nickname
    ) {
        return userRepository.existsByNickname(nickname);
    }

    public List<Map<String, String>> login(
            LoginRequestDto requestDto
    ) {
        User member = userRepository.findByNickname(requestDto.getNickname())
                .orElseThrow(
                        () -> new ErrorNotFoundUserException(ErrorCode.ERROR_USER_ID)
                );

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new ErrorNotFoundUserException(ErrorCode.ERROR_USER_PASSWORD);
        }
        Map<String,String> username =new HashMap<>();
        Map<String,String> token = new HashMap<>();
        List<Map<String,String>> tu = new ArrayList<>(); // -> 리스트를 만드는데, Map형태(키:밸류 형태)의 변수들을 담을 것이다.

        token.put("token",jwtTokenProvider.createToken(member.getNickname(), Long.toString(member.getId())));
        username.put("username",member.getNickname()); // "token" : {token}
        tu.add(username); //List형태 ["username" : {username}]
        tu.add(token); //List형태 ["token" : {token}]
        return tu; // List형태 ["username" : {username}, "token" : {token}]
    }
}