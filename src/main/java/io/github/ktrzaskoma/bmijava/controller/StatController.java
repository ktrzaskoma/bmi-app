package io.github.ktrzaskoma.bmijava.controller;

import io.github.ktrzaskoma.bmijava.dto.StatDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.StatWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;
import io.github.ktrzaskoma.bmijava.service.StatCreateService;
import io.github.ktrzaskoma.bmijava.service.StatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatController {

    private final StatService statService;
    private final StatCreateService statCreateService;

    @PostMapping("/{userId}")
    ResponseEntity<Stats> createStatistic(@PathVariable Long userId, @RequestBody StatWriteModel toCreate) {
        return ResponseEntity.ok()
                .body(statCreateService.createStatistic(userId, toCreate));
    }

    @GetMapping
    ResponseEntity<List<StatDto>> getStats() {
        return ResponseEntity.ok(statService.getAllStats());
    }

    @GetMapping("/{statsId}")
    ResponseEntity<StatDto> getSelectedStat(@PathVariable Long statsId) {
        return ResponseEntity.ok(statService.getSelectedStat(statsId));
    }

    @DeleteMapping("/{statsId}")
    ResponseEntity deleteStat(@PathVariable Long statsId) {
        return statService.deleteSelectedStat(statsId);
    }


}
