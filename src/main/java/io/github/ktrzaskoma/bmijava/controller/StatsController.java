package io.github.ktrzaskoma.bmijava.controller;

import io.github.ktrzaskoma.bmijava.dto.StatsDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.StatsWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;
import io.github.ktrzaskoma.bmijava.service.StatsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatsService statsService;

    @PostMapping("/{userId}")
    ResponseEntity<Stats> createStatistic(@PathVariable Long userId, @RequestBody StatsWriteModel toCreate) {
        return ResponseEntity.ok()
                .body(statsService.createStatistic(userId, toCreate));
    }

    @GetMapping
    ResponseEntity<List<StatsDto>> getStats() {
        return ResponseEntity.ok(statsService.getAllStats());
    }

    @GetMapping("/{statsId}")
    ResponseEntity<StatsDto> getSelectedStat(@PathVariable Long statsId) {
        return ResponseEntity.ok(statsService.getSelectedStat(statsId));
    }

    @DeleteMapping("/{statsId}")
    ResponseEntity deleteStat(@PathVariable Long statsId) {
        return statsService.deleteSelectedStat(statsId);
    }








}
