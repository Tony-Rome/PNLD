package com.react.pnld.mappers;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;

import java.util.List;

public interface TrainingInstitutionIndicatorMapper {

    List<TrainingInstitutionIndicatorDTO> trainingInstitutionData(int fromYear, int toYear);
}
