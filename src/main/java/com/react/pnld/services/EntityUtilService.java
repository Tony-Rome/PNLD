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


    public void completeTeacherPersonFromDiagnostic(Teacher teacher, DiagnosticFileDTO diagnosticFileDTO) {

        if (teacher.getAge() == 0 && diagnosticFileDTO.getAge() != 0) teacher.setAge(diagnosticFileDTO.getAge());

        if ((teacher.getName() == null || teacher.getName().isEmpty()) && diagnosticFileDTO.getName() != null)
            teacher.setName(diagnosticFileDTO.getName());

        if (diagnosticFileDTO.getLastNames() != null) {

            String[] lastNames = EntityAttributeUtilService.splitLastNames(diagnosticFileDTO.getLastNames());

            if (teacher.getPaternalLastName() == null || teacher.getPaternalLastName().isEmpty())
                teacher.setPaternalLastName(lastNames[0]);
            if (teacher.getMaternalLastName() == null || teacher.getMaternalLastName().isEmpty())
                teacher.setMaternalLastName(lastNames[1]);
        }

        if ((teacher.getEmail() == null || teacher.getEmail().isEmpty()) && diagnosticFileDTO.getEmail() != null)
            teacher.setEmail(diagnosticFileDTO.getEmail());

        //TODO Actualizar teacherPerson
    }

    public boolean validateTeacherByRut(String rut) {return EntityAttributeUtilService.rutValidator(rut);}

    public boolean validatePersonByEmail(String email) {

        if (!EntityAttributeUtilService.emailValidator(email)) return false;

        return (!personRepository.checkIfEmailExists(email));
    }

    public School getSchoolByName(String schoolName) {

        if (schoolName == null || schoolName.isEmpty()) {
            return schoolRepository.getSchoolByName(NOT_SPECIFIED).get();
        }

        String schoolNameNormalized = EntityAttributeUtilService.removeAccents(schoolName);

        Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolNameNormalized);

        if (!schoolSelected.isPresent()) {
            School newSchool = createNewSchool(schoolNameNormalized, null,
                    REGION_ID_OTHER, EntityAttributeUtilService.RBD_ID_NOT_SPECIFIED);
            schoolSelected = Optional.of(newSchool);
        }
        return schoolSelected.get();

    }

    School createNewSchool(String name, String city, int regionId, int rbd) {
        School newSchool = new School();
        newSchool.setId(schoolRepository.getNextSchoolId());
        newSchool.setName(name);
        newSchool.setCity(city);
        newSchool.setRegionId(regionId);
        newSchool.setRbd(rbd);

        int resultInsertSchool = this.schoolRepository.insertSchool(newSchool);
        logger.info("createNewSchool. resultInsertSchool={}", resultInsertSchool);
        return newSchool;
    }

    public int getRegionId(String name) {

        if (name == null || name.isEmpty()) {
            return REGION_ID_OTHER;
        }
        String cleanedName = EntityAttributeUtilService.normalizeRegion(name);
        Optional<Integer> regionIdSelected = regionRepository.getRegionIdByName(cleanedName);

        logger.info("getRegionId. regionIdSelected={}", regionIdSelected.get());

        return (regionIdSelected.isPresent()) ? regionIdSelected.get().intValue() : REGION_ID_OTHER;
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
