package io.github.ktrzaskoma.bmijava.dto;

public record StatsDto(
        Long id,
        String bmi,
        String info,
        UserDto user) {

}
