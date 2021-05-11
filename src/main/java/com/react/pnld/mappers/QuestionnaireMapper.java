package com.react.pnld.mappers;

import com.react.pnld.model.ExitSatisfactionQuestionnaire;

public interface QuestionnaireMapper {

    int getDiagnosticQuestionnaireCount(int teacherId);

    int getNextDiagnosticQuestionnaireId();

    void insertDiagnosticQuestionnaire();

    int getExitSatisfactionQuestionnaireCount();

    int getNextExitSatisfactionQuestionnaireId();

    void insertExitSatisfactionQuestionnaire(ExitSatisfactionQuestionnaire exitSatisfactionQuestionnaire);
}
