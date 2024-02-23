package io.github.ktrzaskoma.bmijava.dto.mapper;

import io.github.ktrzaskoma.bmijava.dto.StatDto;
import io.github.ktrzaskoma.bmijava.model.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatMapper implements Function<Stats, StatDto> {

    private final UserMapper userMapper;

    @Override
    public StatDto apply(Stats stats) {
        return new StatDto(
                stats.getId(),
                stats.getBmi(),
                stats.getInfoCode(),
                userMapper.apply(stats.getUser())
        );
    }
}
