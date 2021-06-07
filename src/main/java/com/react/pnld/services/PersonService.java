package com.react.pnld.services;

import com.react.pnld.dto.DiagnosticFileDTO;
import com.react.pnld.model.GenderProperties;
import com.react.pnld.model.Teacher;
import com.react.pnld.repo.GenderRepository;
import com.react.pnld.repo.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Pattern;

@Service
public class PersonService {

    public static final String NOT_SPECIFIED = "no especificado";
    private static final Logger logger = LoggerFactory.getLogger(PersonService.class);

    @Autowired
    private GenderProperties genderProperties;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private GenderRepository genderRepository;

    public int getNextTeacherId() {
        return this.personRepository.getNextTeacherId();
    }

    public Optional<Teacher> getTeacherByRut(String rut) {
        String rutCleaned = this.clearRut(rut);
        return personRepository.getTeacherPerson(rutCleaned);
    }

    public int createTeacher(Teacher teacher) {
        return this.personRepository.insertTeacher(teacher);
    }

    public Teacher buildTeacherFromDiagnostic(DiagnosticFileDTO diagnosticFileDTO, int schoolId) {

        Teacher teacher = new Teacher();
        teacher.setRut(this.clearRut(diagnosticFileDTO.getRut()));
        teacher.setAge(diagnosticFileDTO.getAge());
        teacher.setSchoolRbd(schoolId);
        teacher.setParticipatedInPNLD(false);
        teacher.setTeachesInLevels(null);

        teacher.setDepartment(NOT_SPECIFIED);

        //Training training = this.trainingRepository.getTrainingByFacilitator(NOT_SPECIFIED);

        String[] lastNames = EntityAttributeUtilService.splitLastNames(diagnosticFileDTO.getLastNames());
        teacher.setName(diagnosticFileDTO.getName());
        teacher.setEmail(diagnosticFileDTO.getEmail());

        int genderId = genderRepository.getGenderIdByType(this.genderStandardization(diagnosticFileDTO.getGender()));
        teacher.setGenderId(genderId);

        return teacher;
    }

    public boolean validatePersonByEmail(String email) {

        if (email == null || email.isEmpty() || !EntityAttributeUtilService.emailValidator(email)) return false;
        return !personRepository.checkIfEmailExists(email);
    }

    public int updateTeacher(Teacher teacher){
        return personRepository.updateTeacher(teacher);
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

    public boolean rutValidator(String rut) {
        String cleanRut = this.clearRut(rut);

        if(!Pattern.matches("[0-9]{6,8}(k|[0-9])", cleanRut)){
            return false;
        }

        String rutWithoutCheckDigit = cleanRut.substring(0, cleanRut.length() - 1);

        Integer numericalSeries = 0;
        Integer sum = 1;
        Integer rutAsInt = Integer.parseInt(rutWithoutCheckDigit);

        for (; rutAsInt != 0; rutAsInt = (int) Math.floor(rutAsInt /= 10))
            sum = (sum + rutAsInt % 10 * (9 - numericalSeries++ % 6)) % 11;

        String checkDigitVerifier = (sum > 0) ? String.valueOf(sum - 1) : "k";

        String generatedRut = rutWithoutCheckDigit + checkDigitVerifier;

        return (cleanRut.equals(generatedRut)) ? true : false;

    }

    public String clearRut(String rut) {
        return rut.toLowerCase().replaceAll("[^0-9k]", "");
    }

}

