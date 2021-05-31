package com.react.pnld.services;

import com.react.pnld.dto.DiagnosticFileDTO;
import com.react.pnld.dto.SatisfactionFileDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.GenderProperties;
import com.react.pnld.model.School;
import com.react.pnld.model.Teacher;
import com.react.pnld.model.Training;
import com.react.pnld.repo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EntityUtilService {

    private static final Logger logger = LoggerFactory.getLogger(EntityUtilService.class);

    public static final int GENDER_ID_NOT_SPECIFIED = 4;
    public static final int REGION_ID_OTHER = 17;
    public static final int RBD_ID_NOT_SPECIFIED = 0;
    public static final String NOT_SPECIFIED = "no especificado";

    @Autowired
    private GenderProperties genderProperties;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private TrainingRepository trainingRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Autowired
    private GenderRepository genderRepository;

    @Autowired
    private RegionRepository regionRepository;

    public Optional<Teacher> getTeacherPersonByRut(String cleanRut) {
        return personRepository.getTeacherPerson(cleanRut);
    }

    public int createPerson(Teacher teacher) {
        return this.personRepository.insertPerson(teacher);

    }

    public int createTeacher(Teacher teacher) {
        return this.personRepository.insertTeacher(teacher);
    }

    public Teacher buildTeacherFrom(TrainingFileDTO trainingFileDTO, int schoolId) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(EntityAttributeUtilService.clearRut(trainingFileDTO.getRut()));
        teacher.setAge(0);
        teacher.setSchoolId(schoolId);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);

        String department = (trainingFileDTO.getDepartment() == null || trainingFileDTO.getDepartment().isEmpty()) ?
                NOT_SPECIFIED : trainingFileDTO.getDepartment();
        teacher.setDepartment(department);

        String[] lastNames = EntityAttributeUtilService.splitLastNames(trainingFileDTO.getLastNames());
        teacher.setName(trainingFileDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(trainingFileDTO.getEmail());
        teacher.setGenderId(GENDER_ID_NOT_SPECIFIED);

        return teacher;
    }

    public Teacher buildTeacherFromDiagnostic(DiagnosticFileDTO diagnosticFileDTO, int schoolId) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(EntityAttributeUtilService.clearRut(diagnosticFileDTO.getRut()));
        teacher.setAge(diagnosticFileDTO.getAge());
        teacher.setSchoolId(schoolId);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);

        teacher.setDepartment(NOT_SPECIFIED);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);

        String[] lastNames = EntityAttributeUtilService.splitLastNames(diagnosticFileDTO.getLastNames());
        teacher.setName(diagnosticFileDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(diagnosticFileDTO.getEmail());

        int genderId = genderRepository.getGenderIdByType(this.genderStandardization(diagnosticFileDTO.getGender()));
        teacher.setGenderId(genderId);

        return teacher;
    }

    public Teacher buildTeacherFromExitSatisfaction(SatisfactionFileDTO exitSatisfactionFileDTO, int schoolId) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(EntityAttributeUtilService.clearRut(exitSatisfactionFileDTO.getRut()));
        teacher.setSchoolId(schoolId);
        teacher.setAge(0);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);

        String department = (exitSatisfactionFileDTO.getDepartment() == null || exitSatisfactionFileDTO.getDepartment().isEmpty()) ?
                NOT_SPECIFIED : exitSatisfactionFileDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);

        String[] lastNames = EntityAttributeUtilService.splitLastNames(exitSatisfactionFileDTO.getLastNames());
        teacher.setName(exitSatisfactionFileDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(null);
        teacher.setGenderId(GENDER_ID_NOT_SPECIFIED);

        return teacher;
    }

    public boolean validatePersonByEmail(String email) {

        if (email == null || email.isEmpty() || !EntityAttributeUtilService.emailValidator(email)) return false;
        return !personRepository.checkIfEmailExists(email);
    }

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

    /*public School updateSchool(School school, String city, String commune,String regionName, String rbd){

        if(city != null && !city.isEmpty()) school.setCity(city);

        if(commune != null && !commune.isEmpty()) school.setCommune(commune);

        if(school.getRegionId() == REGION_ID_OTHER && !regionName.isEmpty() && regionName != null){
            int regionId = getRegionId(regionName);
            school.setRegionId(regionId);
        }

        if(school.getRbd() == RBD_ID_NOT_SPECIFIED && rbd != null && !rbd.isEmpty()) {
            Integer rbdAsInt = EntityAttributeUtilService.rbdToInt(rbd);
            if(rbdAsInt != null) school.setRbd(rbdAsInt.intValue());
        }

        int resultUpdateSchool = schoolRepository.updateSchool(school);
        logger.info("updateSchool. resultInsertSchool={}", resultUpdateSchool);

        return school;
    }*/

    public int getRegionId(String name) {

        if (name == null || name.isEmpty()) return REGION_ID_OTHER;

        String cleanedName = EntityAttributeUtilService.normalizeRegion(name);
        int regionIdSelected = regionRepository.getRegionIdByName(cleanedName);

        logger.info("getRegionId. regionIdSelected={}", regionIdSelected);
        return regionIdSelected;
    }

    public String genderStandardization(String gender) {

        if (genderProperties.getFemale().contains(gender)) {
            return genderProperties.GENDER_TYPE_FEMALE;
        }

        if (genderProperties.getMale().contains(gender)) {
            return genderProperties.GENDER_TYPE_MALE;
        }

        if (genderProperties.getOther().contains(gender)) {
            return genderProperties.GENDER_TYPE_OTHER;
        }

        return genderProperties.GENDER_TYPE_NOT_ESPECIFY;
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

    public int updateTeacher(Teacher teacher, int age, String department, boolean participatedInPNLD, String teachesInLevels,
                             boolean isApproved, int trainngYear){

        if(age != 0) teacher.setAge(age);
        if(department != null && !department.isEmpty()) teacher.setDepartment(department);
        if(participatedInPNLD) teacher.setParticipatedInPNLD(participatedInPNLD);
        if(teachesInLevels != null) teacher.setTeachesInLevels(teachesInLevels);
        if(isApproved) teacher.setTrainingApproved(isApproved);
        if(trainngYear > 0) teacher.setTrainingYear(trainngYear);

        return personRepository.updateTeacher(teacher);
    }
}
