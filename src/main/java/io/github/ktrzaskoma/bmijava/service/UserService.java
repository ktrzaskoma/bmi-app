package io.github.ktrzaskoma.bmijava.service;

import io.github.ktrzaskoma.bmijava.dto.UserDto;
import io.github.ktrzaskoma.bmijava.dto.mapper.UserMapper;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }
}
