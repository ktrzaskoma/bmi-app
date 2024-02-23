package io.github.ktrzaskoma.bmijava.dto;

public record StatDto(
        Long id,
        String bmi,
        String infoCode,
        UserDto user) {

}
