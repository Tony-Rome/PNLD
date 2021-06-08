package com.react.pnld.mappers;

import com.react.pnld.model.DiagnosticQuestionnaire;
import com.react.pnld.model.SatisfactionQuestionnaire;

public interface QuestionnaireMapper {

    DiagnosticQuestionnaire getDiagnosticQuestionnaireWhereRut(String teacherRut);

    int getNextDiagnosticQuestionnaireId();

    int insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire);

    int getNextExitSatisfactionQuestionnaireId();

    SatisfactionQuestionnaire getSatisfactionQuestByRut(String teacherRut);

    int insertExitSatisfactionQuestionnaire(SatisfactionQuestionnaire exitSatisfactionQuestionnaire);
}
