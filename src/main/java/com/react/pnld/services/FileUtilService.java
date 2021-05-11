package com.react.pnld.services;

import com.react.pnld.dto.*;
import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.model.GenderProperties;
import com.react.pnld.model.LoadedFile;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Normalizer;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

@Service
public class FileUtilService {

    private static final Logger logger = LoggerFactory.getLogger(FileUtilService.class);
    public static final String DELIMITER_LAST_NAMES = " ";
    public static final int GENDER_ID_NOT_SPECIFIED = 4;
    public static final int RBD_ID_NOT_SPECIFIED = 0;
    public static final String NOT_SPECIFIED = "no especificado";
    public static final int REGION_ID_OTHER = 17;
    public static final int RUT_NOT_SPECIFY = 0;

    @Autowired
    private CSVHeadersProperties csvHeadersProperties;

    @Autowired
    private GenderProperties genderProperties;

    @Autowired
    private LoaderMoodleFile loaderMoodleFile;

    @Autowired
    private LoaderCodeFile loaderCodeFile;

    @Autowired
    private LoaderCPFile loaderCPFile;

    private CsvParserSettings csvParserSettings;

    public FileUtilService() {
        csvParserSettings = new CsvParserSettings();
        csvParserSettings.setLineSeparatorDetectionEnabled(true);
    }

    public static String removeSymbols(String strToClean) {
        String strCleaned = strToClean.replaceAll("[^a-zA-Z0-9,]", "");
        return strCleaned.toLowerCase();
    }

    public String[] selectedHeadersArray(String selectedType) {

        switch (FileTypes.valueOfLabel(selectedType)) {

            case TEACHER_ROSTER:
                return csvHeadersProperties.getTeacherRoster();

            case TEACHER_OPT_IN:
                return csvHeadersProperties.getTeacherOptIn();

            case STUDENT_LEVEL:
                return csvHeadersProperties.getStudentLevel();

            case SIGNIN_PER_COURSE:
                return csvHeadersProperties.getSignInPerCourse();

            case SIGN_INS:
                return csvHeadersProperties.getSignIns();

            case DIAGNOSIS:
                return csvHeadersProperties.getDiagnosis();

            case PRE_TRAINING:
                return csvHeadersProperties.getPreTraining();

            case POST_TRAINING:
                return csvHeadersProperties.getPostTraining();

            case TEST_CT_1:
                return csvHeadersProperties.getTestCT1();

            case TEST_CT_2:
                return csvHeadersProperties.getTestCT2();

            case TEST_CT_3:
                return csvHeadersProperties.getTestCT3();

            case SALIDA:
            case SATISFACTION:
                return csvHeadersProperties.getExitSatisfaction();

            default:
                return new String[1];
        }
    }

    public boolean isStringArraysEquals(String[] firstArray, String[] secondArray) {
        logger.info("isStringArraysEquals. firstArray={}", firstArray);
        logger.info("isStringArraysEquals. secondArray={}", secondArray);

        String[] firstArraySorted = firstArray;
        String[] secondArraySorted = secondArray;

        Arrays.sort(firstArraySorted);
        Arrays.sort(secondArraySorted);
        return Arrays.equals(firstArraySorted, secondArraySorted);
    }

    public String getExtension(String extension) {
        int indexExtensionStart = extension.indexOf(".");
        String fileExtensionName = extension.substring(indexExtensionStart, extension.length());
        return fileExtensionName;
    }

    public String getLoadedFileName(ScheduleFileLoadDTO scheduleFileLoadDTO) {

        String originalFilename = scheduleFileLoadDTO.getUploadFile().getOriginalFilename();
        logger.info("copyAtFileSystem. originalFilename={}", originalFilename);

        String extensionFile = getExtension(originalFilename);
        logger.info("copyAtFileSystem. extensionFile={}", extensionFile);

        String loadedDateFormattedString = scheduleFileLoadDTO.getLoadedOnDateTime().toString().replace(":", "-");
        String loadedDateFormatted = loadedDateFormattedString.replace(getExtension(loadedDateFormattedString), "");
        String finalFileName = loadedDateFormatted + "-" + scheduleFileLoadDTO.getName() + extensionFile;
        logger.info("copyAtFileSystem. finalFileName={}", finalFileName);

        return finalFileName;
    }

    public Reader getReader(String path) {
        try {
            return new InputStreamReader(new FileInputStream(path), "UTF-8");
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            logger.error(e.getMessage(), e);
            throw new IllegalStateException("Unable to read input", e);
        }
    }

    public <T> List<T> parseRowsToBeans(Reader reader, Class<T> clazz) {
        BeanListProcessor<T> rowProcessor = new BeanListProcessor<T>(clazz);
        csvParserSettings.setProcessor(rowProcessor);
        csvParserSettings.setHeaderExtractionEnabled(true);

        try {
            CsvParser parser = new CsvParser(csvParserSettings);
            parser.parse(reader);
            List<T> rowsLikeBeans = rowProcessor.getBeans();
            return rowsLikeBeans;
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return Collections.emptyList();
        }
    }

    public FileResumeDTO processLoadedFile(LoadedFile loadedFile) {

        String path = loadedFile.getStoredIn() + loadedFile.getName();
        Reader loadedFileReader = getReader(path);

        switch (FileTypes.valueOfLabel(loadedFile.getType())) {

            case TEACHER_ROSTER:
                return this.loaderCodeFile.processTeacherRosterFile(loadedFile);

            case TEACHER_OPT_IN:
                return this.loaderCodeFile.processTeacherOptInFile(loadedFile);

            case STUDENT_LEVEL:
                return this.loaderCodeFile.processStudentLevelFile(loadedFile);

            case SIGNIN_PER_COURSE:
                return this.loaderCodeFile.processSignInPerCourseFile(loadedFile);

            case SIGN_INS:
                return this.loaderCodeFile.processSignInsFile(loadedFile);

            case DIAGNOSIS:
                List<DiagnosticFileDTO> diagnosticRows = parseRowsToBeans(loadedFileReader, DiagnosticFileDTO.class);
                return this.loaderMoodleFile.diagnosticoFile(diagnosticRows, loadedFile.getId(), loadedFile.getType());

            case PRE_TRAINING:
            case POST_TRAINING:
                List<TrainingFileDTO> trainingRows = parseRowsToBeans(loadedFileReader, TrainingFileDTO.class);
                return loaderMoodleFile.processTrainingFileRows(trainingRows, loadedFile.getId(), loadedFile.getType());

            case SALIDA:
            case SATISFACTION:
                List<ExitSatisfactionFileDTO> exitSatisfactionRows = parseRowsToBeans(loadedFileReader, ExitSatisfactionFileDTO.class);
                return this.loaderMoodleFile.exitSatisfactionFile(exitSatisfactionRows, loadedFile.getId(), loadedFile.getType());

            case TEST_CT_1:
                return this.loaderCPFile.testPCOneFile(loadedFile);

            case TEST_CT_2:
                return this.loaderCPFile.testPCTwoFile(loadedFile);

            case TEST_CT_3:
                return this.loaderCPFile.testPCThreeFile(loadedFile);

            default:
                return new FileResumeDTO(0, 0, 0);
        }
    }

    public static String removeAccents(String toClean){
        return Normalizer.normalize(toClean, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }

    public static String cleanRegionName(String name){
        String normalizedName =Normalizer.normalize(name, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
        return normalizedName.replaceAll("region | Region ", "");
    }

    public static String removeSymbolsFromRut(String rutToClean){ //TODO: Mejorar hacia validación de RUT

        String cleanedRut = rutToClean.replaceAll("[^0-9k]","");
        String rutPattern = "[0-9]{6,8}(k|[0-9])";
        String newRut = Pattern.matches(rutPattern, cleanedRut) ? cleanedRut : null ;
        return newRut;
    }

    public String genderStandardization(String gender){

        String genderLowerCase = gender.toLowerCase();

        if(genderProperties.getFemale().contains(genderLowerCase)){
            return genderProperties.GENDER_TYPE_FEMALE;
        }

        if(genderProperties.getMale().contains(genderLowerCase)){
            return genderProperties.GENDER_TYPE_MALE;
        }

        if(genderProperties.getOther().contains(genderLowerCase)){
            return genderProperties.GENDER_TYPE_OTHER;
        }

        return genderProperties.GENDER_TYPE_NOT_ESPECIFY;
    }

    public static String[] splitLastNames(String lastNames){

        String[] newLastNamesArray = new String[2];

        String[] lastNamesArray = lastNames.split(" ",2);

        newLastNamesArray[0] = lastNamesArray[0];
        newLastNamesArray[1] = (lastNamesArray.length == 1) ? null: lastNamesArray[1];

        return newLastNamesArray;
    }

    int strToInt(String rbdStr){ //TODO: Mover a otro archivo
        String cleanedRbd = rbdStr.replaceAll("[^0-9]","");
        return Integer.parseInt(rbdStr);
    }

    public static LocalDateTime getLocalDateFrom(String stringDate){

        String stringFormatted = stringDate.replaceAll(" de ", "/").replaceAll("\\s+|\\t", " ");;
        String formatPattern = "d/MMMM/yyyy H:m";
        try {
            return LocalDateTime.parse(stringFormatted, DateTimeFormatter.ofPattern(formatPattern,
                    new Locale("es", "ES")));
        } catch (DateTimeException dateTimeException){
            logger.error("getLocalDateFrom.", dateTimeException.getMessage(), dateTimeException);
            return LocalDateTime.MIN;
        }
    }
}
