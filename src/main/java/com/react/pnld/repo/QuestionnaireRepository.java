package com.react.pnld.repo;

import com.react.pnld.mappers.QuestionnaireMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionnaireRepository {

    @Autowired
    QuestionnaireMapper questionnaireMapper;

    public int getDiagnosticQuestionnaireCount(int teacherId) {
        return questionnaireMapper.getDiagnosticQuestionnaireCount(teacherId);
    }
}
