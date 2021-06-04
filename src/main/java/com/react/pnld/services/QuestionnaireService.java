package com.react.pnld.services;

import com.react.pnld.model.SatisfactionQuestionnaire;
import com.react.pnld.repo.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuestionnaireService {

    @Autowired
    QuestionnaireRepository questionnaireRepository;

    public Optional<SatisfactionQuestionnaire> getSatisfactionQuestByRut(String teacherRut){
        return Optional.ofNullable(questionnaireRepository.getSatisfactionQuestionnaire(teacherRut));
    }

    public int saveSatisfactionQuestionnaire(SatisfactionQuestionnaire satisfactionQuestionnaire){
        return questionnaireRepository.insertSatisfactionQuestionnaire(satisfactionQuestionnaire);
    }

    public int getNextSatisfactionQuestionnaireId(){
        return questionnaireRepository.getNextSatisfactionQuestionnaireId();
    }
}
