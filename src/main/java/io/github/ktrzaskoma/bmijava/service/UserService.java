package io.github.ktrzaskoma.bmijava.service;

import io.github.ktrzaskoma.bmijava.dto.UserDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.UserWriteModel;
import io.github.ktrzaskoma.bmijava.model.User;

import java.util.List;

public interface UserService {

    User createUser(UserWriteModel userToSave);

    List<UserDto> getAllUsers();

    UserDto getSelectedUser(Long userId);
}
