package com.react.pnld.services;

import com.react.pnld.model.School;
import com.react.pnld.repo.RegionRepository;
import com.react.pnld.repo.SchoolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.Optional;

@Service
public class SchoolService {

    private static final int LEN_MAX_RBD = 6;
    public static final int REGION_ID_OTHER = 17;
    public static final int RBD_ID_NOT_SPECIFIED = 0;
    public static final String NOT_SPECIFIED = "no especificado";

    private static final Logger logger = LoggerFactory.getLogger(SchoolService.class);

    @Autowired
    SchoolRepository schoolRepository;

    @Autowired
    RegionRepository regionRepository;


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

    public int getRegionId(String name) {

        if (name == null || name.isEmpty()) return REGION_ID_OTHER;

        String cleanedName = EntityAttributeUtilService.normalizeRegion(name);
        int regionIdSelected = regionRepository.getRegionIdByName(cleanedName);

        logger.info("getRegionId. regionIdSelected={}", regionIdSelected);
        return regionIdSelected;
    }
}
