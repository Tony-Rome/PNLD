package com.react.pnld.mappers;

import com.react.pnld.dto.TrainingTeacherIndicatorDTO;

import java.util.List;

public interface TrainingTeacherIndicatorMapper {

    List<TrainingTeacherIndicatorDTO> trainingTeacherData(int fromYear, int toYear);
}
