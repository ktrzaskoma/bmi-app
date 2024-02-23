package io.github.ktrzaskoma.bmijava.service;

import io.github.ktrzaskoma.bmijava.dto.writemodel.StatWriteModel;
import io.github.ktrzaskoma.bmijava.model.Stats;

public interface StatCreateService {

    Stats createStatistic(Long userId, StatWriteModel statsToSave);
}
