package io.github.ktrzaskoma.bmijava.user;

import io.github.ktrzaskoma.bmijava.user.UserDto;
import io.github.ktrzaskoma.bmijava.user.UserWriteModel;
import io.github.ktrzaskoma.bmijava.user.User;
import io.github.ktrzaskoma.bmijava.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody UserWriteModel toCreate) {
        return ResponseEntity.ok().body(userService.createUser(toCreate));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getSelectedUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getSelectedUser(userId));
    }

}
