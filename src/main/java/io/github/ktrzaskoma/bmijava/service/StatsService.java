package io.github.ktrzaskoma.bmijava.service;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import io.github.ktrzaskoma.bmijava.dto.StatsDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.StatsWriteModel;
import io.github.ktrzaskoma.bmijava.dto.mapper.StatsMapper;
import io.github.ktrzaskoma.bmijava.dto.writemodel.UserWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;
import io.github.ktrzaskoma.bmijava.model.User;
import io.github.ktrzaskoma.bmijava.repo.StatsRepository;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
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
        statsEntity.setBmi(String.valueOf(bmiCalculator(statsToSave)));
        statsEntity.setInfo(bmiAnalyzer(statsToSave));
        statsEntity.setUser(user);

        return statsRepository.save(statsEntity);
    }

    // logic to calculate BMI and receive feedback
    // part 1
    public Double bmiCalculator(StatsWriteModel stats) {
        float weightF = Float.parseFloat(stats.weight());
        float heightF = Float.parseFloat(stats.height());

        return Math.round((weightF/ (heightF * heightF)) * 100.0) / 100.0;
    }

    // part 2
    // todo: replace full information to code of that information
    public String bmiAnalyzer(StatsWriteModel stats) {
        double bmiF = bmiCalculator(stats);

        if (bmiF < 16.0) {
            return "starvation";
        } else if (bmiF < 17.0) {
            return "underweight";
        } else if (bmiF < 18.5) {
            return "low weight";
        } else if (bmiF < 25.0) {
            return "normal weight";
        } else if (bmiF < 30.0) {
            return "overweight";
        } else if (bmiF < 35.0) {
            return "obesity class I";
        } else if (bmiF < 40.0) {
            return "obesity class II";
        } else {
            return "obesity class III";
        }
    }

    // age calculator
    // todo: create this method and implementation
//    public String ageCalculator(LocalDate userDateOfBirth) {
//        LocalDate currentDate = LocalDate.now();
//        return String.valueOf(Period.between(userDateOfBirth ,currentDate).getYears());
//    }

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
