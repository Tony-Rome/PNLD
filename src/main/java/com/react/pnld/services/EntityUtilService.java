package com.react.pnld.services;

import com.react.pnld.model.School;
import com.react.pnld.repo.RegionRepository;
import com.react.pnld.repo.SchoolRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntityUtilService {

    private static final Logger logger = LoggerFactory.getLogger(EntityUtilService.class);


    public static final int REGION_ID_OTHER = 17;
    public static final int RBD_ID_NOT_SPECIFIED = 0;
    public static final String NOT_SPECIFIED = "no especificado";

    @Autowired
    private RegionRepository regionRepository;

    //TODO replace for other
    public School createNewSchool(String name, String city, String commune,String regionName, String rbd) {

        if (name == null || name.isEmpty()) return schoolRepository.getSchoolWhereName(NOT_SPECIFIED).get();

        School newSchool = new School();
        newSchool.setId(schoolRepository.getNextSchoolId());
        newSchool.setName(EntityAttributeUtilService.removeAccents(name));
        newSchool.setCity(city);
        newSchool.setCommune(commune);

        int regionId = getRegionId(regionName);
        newSchool.setRegionId(regionId);

        Integer rbdAsInt = EntityAttributeUtilService.rbdToInt(rbd);
        newSchool.setRbd((rbdAsInt != null) ? rbdAsInt.intValue() : RBD_ID_NOT_SPECIFIED);

        int resultInsertSchool = this.schoolRepository.insertSchool(newSchool);
        logger.info("createNewSchool. resultInsertSchool={}", resultInsertSchool);
        return newSchool;
    }

    public School createSchool(String name, String commune, String city, int regionId, int rbd) {
        String schoolName = (name == null || name.isEmpty())? NOT_SPECIFIED : name;

        String schoolCity = (city == null || city.isEmpty())? NOT_SPECIFIED : city;

        String schoolCommune = (commune == null || commune.isEmpty())? NOT_SPECIFIED : commune;

        int schoolRegionId = (regionId == 0)? REGION_ID_OTHER : regionId;

        int schoolRbd = (rbd == 0) ? RBD_ID_NOT_SPECIFIED : rbd;

        School newSchool = new School(schoolRepository.getNextSchoolId(), schoolName, schoolCommune, schoolCity,
                schoolRegionId, schoolRbd);
        int resultInsertSchool = this.schoolRepository.insertSchool(newSchool);
        logger.info("createNewSchool. newSchool={}, resultInsertSchool={}", newSchool, resultInsertSchool);
        return newSchool;
    }

    public int getRegionId(String name) {

        if (name == null || name.isEmpty()) return REGION_ID_OTHER;

        String cleanedName = EntityAttributeUtilService.normalizeRegion(name);
        int regionIdSelected = regionRepository.getRegionIdByName(cleanedName);

        logger.info("getRegionId. regionIdSelected={}", regionIdSelected);
        return regionIdSelected;
    }

    public Optional<School> getSchoolWhereName(String schoolName) {

        Optional<School> selectedSchool = schoolRepository.getSchoolWhereName(schoolName);

        if(!selectedSchool.isPresent()) return schoolRepository.getSchoolWhereRbd(0);

        return selectedSchool;
    }

    public Optional<School> getSchoolWhereRbd(int rbd){
        return schoolRepository.getSchoolWhereRbd(rbd);
    }

    public int updateSchool(School school, String name, int rbd, int regionId, String city, String commune){
        int regionIdUpdated = (regionId > 0 && regionId < 17)? regionId : school.getRegionId();
        school.setRegionId(regionIdUpdated);

        String nameSchoolUpdated = (name != null && !name.isEmpty())? name : school.getName();
        school.setName(nameSchoolUpdated);

        String cityUpdated= (city != null && !city.isEmpty())? city : school.getCity();
        school.setCity(cityUpdated);

        int rbdUpdated = (rbd != 0)? rbd : school.getRbd();
        school.setRbd(rbdUpdated);

        String communeUpdated = (commune != null && !commune.isEmpty())? commune : school.getCommune();
        school.setCommune(communeUpdated);

        int resultUpdate = schoolRepository.updateSchool(school);
        logger.info("updateSchool. school={}, resultUpdate={}", school, resultUpdate);
        return resultUpdate;
    }

}
