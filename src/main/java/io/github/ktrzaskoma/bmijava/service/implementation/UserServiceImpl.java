package io.github.ktrzaskoma.bmijava.service.implementation;

import io.github.ktrzaskoma.bmijava.dto.UserDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.UserWriteModel;
import io.github.ktrzaskoma.bmijava.dto.mapper.UserMapper;
import io.github.ktrzaskoma.bmijava.model.User;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import io.github.ktrzaskoma.bmijava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User createUser(UserWriteModel userToSave) {
        var userEntity = new User();
        userEntity.setName(userToSave.name());
        userEntity.setSurname(userToSave.surname());
        userEntity.setBirthDate(userToSave.birthDate());
        userEntity.setGender(userToSave.gender());

        return userRepository.save(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getSelectedUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
