package com.react.pnld.mappers;

import com.react.pnld.dto.TrainingIndicatorDTO;

import java.util.List;

public interface TrainingIndicatorMapper {

    List<TrainingIndicatorDTO> trainingInstitutionData(int fromYear, int toYear);

    List<TrainingIndicatorDTO> trainingTeacherData(int fromYear, int toYear);
}
