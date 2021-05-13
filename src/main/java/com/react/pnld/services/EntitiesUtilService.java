package com.react.pnld.services;

import com.react.pnld.dto.TeacherPersonDTO;
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
public class EntitiesUtilService {

    private static final Logger logger = LoggerFactory.getLogger(EntitiesUtilService.class);

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
    @Autowired
    FileUtilService fileUtilService;

    public Teacher buildTeacherFrom(TeacherPersonDTO teacherPersonDTO) {

        Teacher teacher = new Teacher();
        teacher.setTeacherId(this.personRepository.getNextTeacherId());
        teacher.setPersonId(this.personRepository.getNextPersonId());
        teacher.setRut(FileUtilService.removeSymbols(teacherPersonDTO.getRut().trim()));
        teacher.setAge(0);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);
        teacher.setTeachesSubjects(null);
        teacher.setCsResources(null);
        teacher.setRoboticsResources(null);

        String department = (teacherPersonDTO.getDepartment() == null || teacherPersonDTO.getDepartment().isEmpty()) ?
                FileUtilService.NOT_SPECIFIED : teacherPersonDTO.getDepartment();
        teacher.setDepartment(department);

        Training training = this.trainingRepository.getTrainingByFacilitator(FileUtilService.NOT_SPECIFIED);
        teacher.setTrainingId(training.getId());

        String[] lastNames = FileUtilService.splitLastNames(teacherPersonDTO.getLastNames());
        teacher.setName(teacherPersonDTO.getName());
        teacher.setPaternalLastName(lastNames[0]);
        teacher.setMaternalLastName(lastNames[1]);
        teacher.setEmail(teacherPersonDTO.getEmail());

        int genderId = genderRepository.getGenderIdByType(fileUtilService.genderStandardization(teacherPersonDTO.getGender()));
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

            String[] lastNames = FileUtilService.splitLastNames(teacherPersonDTO.getLastNames());

           if(teacher.getPaternalLastName() == null || teacher.getPaternalLastName().isEmpty())
               teacher.setPaternalLastName(lastNames[0]);
           if(teacher.getMaternalLastName() == null || teacher.getMaternalLastName().isEmpty())
               teacher.setMaternalLastName(lastNames[1]);
        }

        if((teacher.getEmail() == null || teacher.getEmail().isEmpty()) && teacherPersonDTO.getEmail() != null)
            teacher.setEmail(teacherPersonDTO.getEmail());

        //TODO Actualizar teacherPerson
    }

    public School getSchoolByName(String schoolName) {

        if (schoolName == null || schoolName.isEmpty()) {
            return schoolRepository.getSchoolByName(FileUtilService.NOT_SPECIFIED).get();
        }

        Optional<School> schoolSelected = schoolRepository.getSchoolByName(schoolName);

        if (!schoolSelected.isPresent()) {
            School newSchool = createNewSchool(schoolName, null, FileUtilService.REGION_ID_OTHER, FileUtilService.RBD_ID_NOT_SPECIFIED);
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
            return FileUtilService.REGION_ID_OTHER;
        }
        String cleanedName = FileUtilService.normalizeStr(name);
        Optional<Integer> regionIdSelected = regionRepository.getRegionIdByName(cleanedName);

        logger.info("getRegionId. regionIdSelected={}", regionIdSelected.get());

        return (regionIdSelected.isPresent()) ? regionIdSelected.get().intValue() : FileUtilService.REGION_ID_OTHER;
    }

    public void verifySchool(School school, String city, int regionId, int rbd) {

        if ((school.getCity() == null || school.getCity().isEmpty()) && (city != null)) {
            school.setCity(city);
        }
        if ((school.getRegionId() == FileUtilService.REGION_ID_OTHER) && (regionId != FileUtilService.REGION_ID_OTHER)) {
            school.setRegionId(regionId);
        }
        if ((school.getRbd() == FileUtilService.RBD_ID_NOT_SPECIFIED) && (rbd != FileUtilService.RBD_ID_NOT_SPECIFIED)) {
            school.setRbd(rbd);
        }
        //TODO Actualizar colegio
    }
}
