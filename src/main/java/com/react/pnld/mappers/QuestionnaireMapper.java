package com.react.pnld.mappers;

import com.react.pnld.model.DiagnosticQuestionnaire;
import com.react.pnld.model.SatisfactionQuestionnaire;

public interface QuestionnaireMapper {

    int getDiagnosticQuestionnaireCount(int teacherId);

    int getNextDiagnosticQuestionnaireId();

    void insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire);

    int getExitSatisfactionQuestionnaireCount(int teacherId);

    int getNextExitSatisfactionQuestionnaireId();

    SatisfactionQuestionnaire getSatisfactionQuestByRut(String teacherRut);

    int insertExitSatisfactionQuestionnaire(SatisfactionQuestionnaire exitSatisfactionQuestionnaire);
}
