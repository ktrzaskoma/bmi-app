package io.github.ktrzaskoma.bmijava.service;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import io.github.ktrzaskoma.bmijava.dto.StatsDto;
import io.github.ktrzaskoma.bmijava.dto.StatsWriteModel;
import io.github.ktrzaskoma.bmijava.dto.mapper.StatsMapper;
import io.github.ktrzaskoma.bmijava.model.Stats;
import io.github.ktrzaskoma.bmijava.model.User;
import io.github.ktrzaskoma.bmijava.repo.StatsRepository;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatsService {

    Logger log = LoggerFactory.getLogger(LoggerContextFilter.class);

    private final StatsRepository statsRepository;
    private final UserRepository userRepository;
    private final StatsMapper statsMapper;

    // creating and saving Statistic
    public Stats createStatistic(Long userId, StatsWriteModel statsToSave) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        var statsEntity = new Stats();
        statsEntity.setHeight(statsToSave.height());
        statsEntity.setWeight(statsToSave.weight());
        statsEntity.setUser(user);
        statsEntity.setBmi(calculateBMI(statsToSave));

        return statsRepository.save(statsEntity);
    }

    // logic to calculate BMI
    public String calculateBMI(StatsWriteModel stats) {


        float weightF = Float.parseFloat(stats.weight());
        float heightF = Float.parseFloat(stats.height());
        float bmiF = Math.round(weightF/ (heightF * heightF));

        return String.valueOf(bmiF);
    }


    public List<StatsDto> getAllStats() {
        log.info("Get all stats!");
        return statsRepository.findAll()
                .stream()
                .map(statsMapper)
                .collect(Collectors.toList());

    }

    public StatsDto getSelectedStat(Long statsId) {
        return statsRepository.findById(statsId)
                .map(statsMapper).orElseThrow(() -> new RuntimeException("Stats not found"));
    }

    public ResponseEntity deleteSelectedStat(Long statsId) {
        if (statsRepository.existsById(statsId)) {
            statsRepository.deleteById(statsId);
            log.info("Selected stat was deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            log.info("There's nothing to delete");
            return ResponseEntity.notFound().build();
        }
    }


}
