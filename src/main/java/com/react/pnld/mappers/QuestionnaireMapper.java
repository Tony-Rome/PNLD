package com.react.pnld.mappers;

import com.react.pnld.model.DiagnosticQuestionnaire;
import com.react.pnld.model.SatisfactionQuestionnaire;

public interface QuestionnaireMapper {

    DiagnosticQuestionnaire getDiagnosticQuestionnaireWhereRut(String teacherRut);

    int getNextDiagnosticQuestionnaireId();

    int insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire);

    int getNextSatisfactionQuestionnaireId();

    SatisfactionQuestionnaire getSatisfactionQuestByRut(String teacherRut);

    int insertSatisfactionQuestionnaire(SatisfactionQuestionnaire exitSatisfactionQuestionnaire);
}
