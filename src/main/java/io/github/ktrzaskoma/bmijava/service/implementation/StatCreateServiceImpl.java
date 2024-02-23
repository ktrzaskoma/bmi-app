package io.github.ktrzaskoma.bmijava.service.implementation;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import io.github.ktrzaskoma.bmijava.dto.writemodel.StatWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;
import io.github.ktrzaskoma.bmijava.model.User;
import io.github.ktrzaskoma.bmijava.repo.StatRepository;
import io.github.ktrzaskoma.bmijava.repo.UserRepository;
import io.github.ktrzaskoma.bmijava.service.StatCreateService;
import io.github.ktrzaskoma.bmijava.service.StatService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatCreateServiceImpl implements StatCreateService {

    Logger log = LoggerFactory.getLogger(LoggerContextFilter.class);

    private final StatRepository statRepository;
    private final UserRepository userRepository;

    @Override
    public Stats createStatistic(Long userId, StatWriteModel statsToSave) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        var statsEntity = new Stats();

        statsEntity.setHeight(statsToSave.height());
        statsEntity.setWeight(statsToSave.weight());
        statsEntity.setBmi(String.valueOf(calculateBMI(statsToSave)));
        statsEntity.setInfoCode(lineUpBMI(statsToSave, user));
        statsEntity.setUser(user);

        log.info("Stat was created");

        return statRepository.save(statsEntity);
    }


    public Double calculateBMI(StatWriteModel stats) {
        float weightF = Float.parseFloat(stats.weight());
        float heightF = Float.parseFloat(stats.height());

        return Math.round((weightF/ (heightF * heightF)) * 100.0) / 100.0;
    }

    public String lineUpBMI(StatWriteModel stats, User user) {

        int age = (Period.between(user.getBirthDate(), LocalDate.now())).getYears();
        double bmiF = calculateBMI(stats);

        Map<String, double[]> ageGroups = new HashMap<>();
        ageGroups.put("0-17", new double[]{0, 0, 0});
        ageGroups.put("18-24", new double[]{18.5, 25, 30});
        ageGroups.put("25-34", new double[]{19, 24, 29});
        ageGroups.put("35-44", new double[]{20, 25, 30});
        ageGroups.put("45-54", new double[]{21, 26, 31});
        ageGroups.put("55-64", new double[]{22, 27, 32});
        ageGroups.put("65-74", new double[]{23, 28, 33});
        ageGroups.put("75-84", new double[]{24, 29, 34});
        ageGroups.put("85+", new double[]{25, 30, 35});

        for (Map.Entry<String, double[]> entry : ageGroups.entrySet()) {
            String ageGroup = entry.getKey();
            double[] bmiRanges = entry.getValue();

            String[] ageRange = ageGroup.split("-");
            int lowerAge = Integer.parseInt(ageRange[0]);
            int upperAge = ageRange[1].equals("+") ? Integer.MAX_VALUE : Integer.parseInt(ageRange[1]);

            if (age >= lowerAge && age <= upperAge) {
                if (lowerAge == 0) {
                    return "0";
                } else if (bmiF < bmiRanges[0]) {           // underweight
                    return "1";
                } else if (bmiF < bmiRanges[1]) {           // normal
                    return "2";
                } else if (bmiF < bmiRanges[2]) {           // overweight
                    return "3";
                } else {
                    return "4";                             // obsity
                }
            }
        }

        return "5"; // consult with specialist
    }
}
