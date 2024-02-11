package io.github.ktrzaskoma.bmijava.dto.mapper;

import io.github.ktrzaskoma.bmijava.dto.StatsDto;
import io.github.ktrzaskoma.bmijava.model.Stats;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatsMapper implements Function<Stats, StatsDto> {

    private final UserMapper userMapper;

    @Override
    public StatsDto apply(Stats stats) {
        return new StatsDto(
                stats.getId(),
                stats.getBmi(),
                userMapper.apply(stats.getUser())
        );
    }
}
