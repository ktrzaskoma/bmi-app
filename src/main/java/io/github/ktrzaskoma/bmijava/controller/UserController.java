package io.github.ktrzaskoma.bmijava.controller;

import io.github.ktrzaskoma.bmijava.dto.UserDto;
import io.github.ktrzaskoma.bmijava.model.User;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import io.github.ktrzaskoma.bmijava.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URI;
import java.util.List;


@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @PostMapping
    ResponseEntity<User> createUser(@RequestBody User toCreate) {
        User result = userRepository.save(toCreate);
        return ResponseEntity.created(URI.create("/" + result.getId())).body(result);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
