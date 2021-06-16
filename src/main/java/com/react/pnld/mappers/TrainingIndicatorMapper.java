package com.react.pnld.mappers;

import com.react.pnld.dto.TrainingInstitutionIndicatorDTO;

import java.util.List;

public interface TrainingIndicatorMapper {

    List<TrainingInstitutionIndicatorDTO> participantInstitutionNumberPNLD(int fromYear, int toYear);
}
