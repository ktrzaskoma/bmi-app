package io.github.ktrzaskoma.bmijava.stat;

import io.github.ktrzaskoma.bmijava.user.UserDto;

public record StatDto(
        Long id,
        String bmi,
        String infoCode,
        UserDto user) {
}
