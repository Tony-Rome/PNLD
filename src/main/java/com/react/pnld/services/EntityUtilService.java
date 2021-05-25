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

import javax.swing.text.html.Option;
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
    PersonRepository personRepository;
    @Autowired
    TrainingRepository trainingRepository;
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    GenderRepository genderRepository;
    @Autowired
    RegionRepository regionRepository;

    public Optional<Teacher> getTeacherPersonByRut(String rut) {
        return personRepository.getTeacherPerson(EntityAttributeUtilService.clearRut(rut));
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
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (trainingFileDTO.getDepartment() == null || trainingFileDTO.getDepartment().isEmpty()) ?
                NOT_SPECIFIED : trainingFileDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

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
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        teacher.setDepartment(NOT_SPECIFIED);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

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
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (exitSatisfactionFileDTO.getDepartment() == null || exitSatisfactionFileDTO.getDepartment().isEmpty()) ?
                NOT_SPECIFIED : exitSatisfactionFileDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

        String[] lastNames = EntityAttributeUtilService.splitLastNames(exitSatisfactionFileDTO.getLastNames());
        teacher.setName(exitSatisfactionFileDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(null);
        teacher.setGenderId(GENDER_ID_NOT_SPECIFIED);

        return teacher;
    }

    public boolean validateTeacherByRut(String rut) {return EntityAttributeUtilService.rutValidator(rut);}

    public boolean validatePersonByEmail(String email) {

        if (email == null || email.isEmpty() || !EntityAttributeUtilService.emailValidator(email)) return false;
        return !personRepository.checkIfEmailExists(email);
    }

    public Optional<School> getSchoolByName(String schoolName) {

        if (schoolName == null || schoolName.isEmpty()) return Optional.empty();

        String schoolNameNormalized = EntityAttributeUtilService.removeAccents(schoolName);
        return schoolRepository.getSchoolByName(schoolNameNormalized);

    }

    public School createNewSchool(String name, String city, String commune,String regionName, String rbd) {

        if (name == null || name.isEmpty()) return schoolRepository.getSchoolByName(NOT_SPECIFIED).get();

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

    public School updateSchool(School school, String city, String commune,String regionName, String rbd){

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

        int resultUpdateSchool =schoolRepository.updateSchool(school);
        logger.info("updateSchool. resultInsertSchool={}", resultUpdateSchool);

        return school;
    }

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


}
