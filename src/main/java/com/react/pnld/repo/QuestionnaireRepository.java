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

    public DiagnosticQuestionnaire getDiagnosticQuestionnaireWhereRut(String teacherRut) {
        return questionnaireMapper.getDiagnosticQuestionnaireWhereRut(teacherRut);
    }

    public int getNextDiagnosticQuestionnaireId() {
        return questionnaireMapper.getNextDiagnosticQuestionnaireId();
    }

    public int insertDiagnosticQuestionnaire(DiagnosticQuestionnaire newDiagnosticQuestionnaire) {
        return questionnaireMapper.insertDiagnosticQuestionnaire(newDiagnosticQuestionnaire);
    }

    public int getNextSatisfactionQuestionnaireId() {
        return questionnaireMapper.getNextExitSatisfactionQuestionnaireId();
    }

    public SatisfactionQuestionnaire getSatisfactionQuestionnaire(String teacherRut){
        return this.questionnaireMapper.getSatisfactionQuestByRut(teacherRut);
    }

    public int insertSatisfactionQuestionnaire(SatisfactionQuestionnaire exitSatisfactionQuestionnaire) {
        return questionnaireMapper.insertExitSatisfactionQuestionnaire(exitSatisfactionQuestionnaire);
    }
}
