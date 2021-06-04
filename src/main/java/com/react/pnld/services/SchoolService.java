package com.react.pnld.services;

import com.react.pnld.model.School;
import com.react.pnld.repo.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
public class SchoolService {

    @Autowired
    SchoolRepository schoolRepository;

    public Optional<School> getSchoolByName(String schoolName){

        if(schoolName != null && !schoolName.isEmpty()){
            String cleanSchoolName = Normalizer.normalize(schoolName, Normalizer.Form.NFD).
                    replaceAll("[^\\p{ASCII}]", "");

            Optional<School> schoolWithName = schoolRepository.getSchoolWhereName(cleanSchoolName);

            if(schoolWithName.isPresent()){
                return schoolWithName;
            }
        }

        return schoolRepository.getSchoolNotSpecified();
    }
}
