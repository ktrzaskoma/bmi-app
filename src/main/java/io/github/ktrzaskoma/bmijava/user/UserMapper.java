package io.github.ktrzaskoma.bmijava.user;

import io.github.ktrzaskoma.bmijava.user.UserDto;
import io.github.ktrzaskoma.bmijava.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserMapper implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getSurname()
        );
    }
}
