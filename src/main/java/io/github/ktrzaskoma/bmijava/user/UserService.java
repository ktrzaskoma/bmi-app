package io.github.ktrzaskoma.bmijava.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public User createUser(UserWriteModel userToSave) {
        var userEntity = new User();
        userEntity.setName(userToSave.name());
        userEntity.setSurname(userToSave.surname());
        userEntity.setBirthDate(userToSave.birthDate());
        userEntity.setGender(userToSave.gender());

        return userRepository.save(userEntity);
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper)
                .collect(Collectors.toList());
    }

    public UserDto getSelectedUser(Long userId) {
        return userRepository.findById(userId)
                .map(userMapper).orElseThrow(() -> new RuntimeException("User not found"));
    }


}
