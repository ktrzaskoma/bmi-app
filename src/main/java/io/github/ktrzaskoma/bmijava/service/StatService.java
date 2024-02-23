package io.github.ktrzaskoma.bmijava.service;

import io.github.ktrzaskoma.bmijava.dto.StatDto;
import io.github.ktrzaskoma.bmijava.dto.writemodel.StatWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface StatService {

    StatDto getSelectedStat(Long statsId);

    List<StatDto> getAllStats();

    ResponseEntity deleteSelectedStat(Long statsId);


}
