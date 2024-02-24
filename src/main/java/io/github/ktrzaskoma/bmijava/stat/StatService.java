package io.github.ktrzaskoma.bmijava.stat;

import ch.qos.logback.classic.selector.servlet.LoggerContextFilter;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StatService {

    Logger log = LoggerFactory.getLogger(LoggerContextFilter.class);

    private final StatRepository statRepository;
    private final StatMapper statMapper;

    public StatDto getSelectedStat(Long statsId) {
        return statRepository.findById(statsId)
                .map(statMapper).orElseThrow(() -> new RuntimeException("Stats not found"));

    }

    public List<StatDto> getAllStats() {
        log.info("Get all stats!");
        return statRepository.findAll()
                .stream()
                .map(statMapper)
                .collect(Collectors.toList());
    }

    public ResponseEntity deleteSelectedStat(Long statsId) {
        if (statRepository.existsById(statsId)) {
            statRepository.deleteById(statsId);
            log.info("Selected stat was deleted");
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        else {
            log.info("There's nothing to delete");
            return ResponseEntity.notFound().build();
        }
    }


}
