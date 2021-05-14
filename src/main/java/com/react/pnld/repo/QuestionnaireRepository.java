package com.react.pnld.repo;

import com.react.pnld.mappers.QuestionnaireMapper;
import com.react.pnld.model.DiagnosticQuestionnaire;
import com.react.pnld.model.SatisfactionQuestionnaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionnaireRepository {

    @Autowired
    QuestionnaireMapper questionnaireMapper;

    public int getDiagnosticQuestionnaireCount(int teacherId) {
        return questionnaireMapper.getDiagnosticQuestionnaireCount(teacherId);
    }

    public int getNextDiagnosticQuestionnaireId() {
        return questionnaireMapper.getNextDiagnosticQuestionnaireId();
    }

    public void insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire) {
        questionnaireMapper.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
    }

    public int getSatisfactionQuestionnaireCount(int teacherId) {
        return questionnaireMapper.getExitSatisfactionQuestionnaireCount(teacherId);
    }

    public int getNextSatisfactionQuestionnaireId() {
        return questionnaireMapper.getNextExitSatisfactionQuestionnaireId();
    }

    public void insertSatisfactionQuestionnaire(SatisfactionQuestionnaire exitSatisfactionQuestionnaire) {
        questionnaireMapper.insertExitSatisfactionQuestionnaire(exitSatisfactionQuestionnaire);
    }
}