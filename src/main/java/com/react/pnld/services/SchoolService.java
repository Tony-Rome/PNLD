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

    public Optional<School> getSchoolWhereRbd(int rbd){
        return schoolRepository.getSchoolWhereRbd(rbd);
    }

    public int createSchool(School school){
        return schoolRepository.insertSchool(school);
    }

    public int updateSchool(School school){
        return schoolRepository.updateSchool(school);
    }

    public int rbdToInt(String rbdStr) {
        if(rbdStr == null || rbdStr.isEmpty()) return null;
        String cleanedRbd = rbdStr.replaceAll("[^\\d]", "");
        if (cleanedRbd.isEmpty() || cleanedRbd.length() > LEN_MAX_RBD) return null;
        return Integer.parseInt(cleanedRbd);
    }
}
