package com.react.pnld.mappers;

import com.react.pnld.dto.indicator.TrainingIndicatorDTO;

import java.util.List;

public interface TrainingIndicatorMapper {

    TrainingIndicatorDTO trainingInstitutionData(int fromYear, int toYear, int regionId);

    TrainingIndicatorDTO trainingTeacherData(int fromYear, int toYear, int regionId);
}
