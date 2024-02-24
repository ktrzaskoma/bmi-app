package io.github.ktrzaskoma.bmijava.stat;

import io.github.ktrzaskoma.bmijava.stat.StatDto;
import io.github.ktrzaskoma.bmijava.stat.Stat;
import io.github.ktrzaskoma.bmijava.user.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class StatMapper implements Function<Stat, StatDto> {

    private final UserMapper userMapper;

    @Override
    public StatDto apply(Stat stat) {
        return new StatDto(
                stat.getId(),
                stat.getBmi(),
                stat.getInfoCode(),
                userMapper.apply(stat.getUser())
        );
    }
}
