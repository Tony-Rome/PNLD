package com.react.pnld.services;

import com.react.pnld.dto.TeacherPersonDTO;
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
public class EntitieUtilService {

    private static final Logger logger = LoggerFactory.getLogger(EntitieUtilService.class);

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
        return personRepository.getTeacherPerson(rut);
    }

    public int createPerson(Teacher teacher){
        return this.personRepository.insertPerson(teacher);

    }
    public int createTeacher(Teacher teacher){
        return this.personRepository.insertTeacher(teacher);
    }

    public Teacher buildTeacherFrom(TeacherPersonDTO teacherPersonDTO) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(FileAttributeUtilService.removeSymbols(teacherPersonDTO.getRut().trim()));
        teacher.setAge(0);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (teacherPersonDTO.getDepartment() == null || teacherPersonDTO.getDepartment().isEmpty()) ?
                NOT_SPECIFIED : teacherPersonDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

        String[] lastNames = FileAttributeUtilService.splitLastNames(teacherPersonDTO.getLastNames());
        teacher.setName(teacherPersonDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(teacherPersonDTO.getEmail());

        int genderId = genderRepository.getGenderIdByType(this.genderStandardization(teacherPersonDTO.getGender()));
        teacher.setGenderId(genderId);

        return teacher;
    }

    public void verifyTeacherPerson(Teacher teacher, TeacherPersonDTO teacherPersonDTO) {


        if(teacher.getAge() == 0 && teacherPersonDTO.getAge() != 0) teacher.setAge(teacherPersonDTO.getAge());

        if((teacher.getDepartment() == null || teacher.getDepartment().isEmpty()) && teacherPersonDTO.getDepartment() != null)
            teacher.setDepartment(teacherPersonDTO.getDepartment());

        if((teacher.getName() == null || teacher.getName().isEmpty()) && teacherPersonDTO.getName() != null)
            teacher.setName(teacherPersonDTO.getName());

        if(teacherPersonDTO.getLastNames() != null){

            String[] lastNames = FileAttributeUtilService.splitLastNames(teacherPersonDTO.getLastNames());

           if(teacher.getPaternalLastName() == null || teacher.getPaternalLastName().isEmpty())
               teacher.setPaternalLastName(lastNames[0]);
           if(teacher.getMaternalLastName() == null || teacher.getMaternalLastName().isEmpty())
               teacher.setMaternalLastName(lastNames[1]);
        }

        if((teacher.getEmail() == null || teacher.getEmail().isEmpty()) && teacherPersonDTO.getEmail() != null)
            teacher.setEmail(teacherPersonDTO.getEmail());

        //TODO Actualizar teacherPerson
    }

    public String validateTeacherByRut(String rut){
        return FileAttributeUtilService.rutValidator(rut);
    }

    public boolean validatePersonByEmail(String email){
        return personRepository.checkIfEmailExists(email);
    }

    public School getSchoolByName(String schoolName) {

        String schoolNameNormalized = FileAttributeUtilService.removeAccents(schoolName);

        if (schoolNameNormalized == null || schoolNameNormalized.isEmpty()) {
            return schoolRepository.getSchoolByName(NOT_SPECIFIED).get();
        }

        Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolNameNormalized);

        if (!schoolSelected.isPresent()) {
            School newSchool = createNewSchool(schoolNameNormalized, null,
                    REGION_ID_OTHER, FileAttributeUtilService.RBD_ID_NOT_SPECIFIED);
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

    public void verifySchool(School school, String city, int regionId, String rbdAsStr) {

        int rbd = FileAttributeUtilService.rbdToInt(rbdAsStr);

        if ((school.getCity() == null || school.getCity().isEmpty()) && (city != null)) {
            school.setCity(city);
        }
        if ((school.getRegionId() == REGION_ID_OTHER) && (regionId != REGION_ID_OTHER)) {
            school.setRegionId(regionId);
        }
        if ((school.getRbd() == FileAttributeUtilService.RBD_ID_NOT_SPECIFIED) && (rbd != FileAttributeUtilService.RBD_ID_NOT_SPECIFIED)) {
            school.setRbd(rbd);
        }
        //TODO Actualizar colegio
    }

    public int getRegionId(String name) {

        if (name == null || name.isEmpty()) {
            return REGION_ID_OTHER;
        }
        String cleanedName = FileAttributeUtilService.normalizeRegion(name);
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
