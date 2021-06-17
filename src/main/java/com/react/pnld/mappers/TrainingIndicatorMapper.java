package com.react.pnld.mappers;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;
import com.react.pnld.dto.TrainingTeacherIndicatorDTO;

import java.util.List;

public interface TrainingIndicatorMapper {

    List<TrainingInstitutionIndicatorDTO> trainingInstitutionData(int fromYear, int toYear);

    List<TrainingTeacherIndicatorDTO> trainingTeacherData(int fromYear, int toYear);
}
