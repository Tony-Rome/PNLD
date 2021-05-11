package com.react.pnld.mappers;

import com.react.pnld.model.DiagnosticQuestionnaire;
import com.react.pnld.model.ExitSatisfactionQuestionnaire;

public interface QuestionnaireMapper {

    int getDiagnosticQuestionnaireCount(int teacherId);

    int getNextDiagnosticQuestionnaireId();

    void insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire);

    int getExitSatisfactionQuestionnaireCount(int teacherId);

    int getNextExitSatisfactionQuestionnaireId();

    void insertExitSatisfactionQuestionnaire(ExitSatisfactionQuestionnaire exitSatisfactionQuestionnaire);
}
