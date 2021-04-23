package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.FileTypes;
import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.dto.TrainingFileDTO;
import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.model.LoadedFile;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.postgresql.util.PGInterval;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.text.Normalizer;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FileUtilService {

    private static final Logger logger = LoggerFactory.getLogger(FileUtilService.class);

    @Autowired
    private CSVHeadersProperties csvHeadersProperties;

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

    public String removeSymbols(String strToClean) {
        String strCleaned = strToClean.replaceAll("[^a-zA-Z0-9,;]", "");
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
                return csvHeadersProperties.getSalida();

            case SATISFACTION:
                return csvHeadersProperties.getSatisfaction();

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
                return this.loaderMoodleFile.diagnosticoFile(loadedFile);

            case PRE_TRAINING:
            case POST_TRAINING:
                List<TrainingFileDTO> trainingRows = parseRowsToBeans(loadedFileReader, TrainingFileDTO.class);
                return loaderMoodleFile.processTrainingFileRows(trainingRows, loadedFile.getId(), loadedFile.getType());

            case SALIDA:
                return this.loaderMoodleFile.salidaFile(loadedFile);

            case SATISFACTION:
                return this.loaderMoodleFile.satisFile(loadedFile);

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

    public static PGInterval getRequiredTrainingInterval(String requiredInterval){
        String DAY_LABEL = "dia";
        String HOUR_LABEL = "hora";
        String MINUTE_LABEL = "minuto";
        String SECOND_LABEL = "segundo";

        int yearZero = 0;
        int monthZero = 0;
        int days = 0;
        int hour = 0;
        int mins = 0;
        int secs = 0;

        String durationWithoutAccents = removeAccents(requiredInterval);
        String onlyNumsString = durationWithoutAccents.trim().replaceAll("([ \\t\\n\\x0B\\f\\r][a-z]+)","");
        String[] time = onlyNumsString.split("[ \\t\\n\\x0B\\f\\r]");

        if(durationWithoutAccents.contains(DAY_LABEL) && durationWithoutAccents.contains(HOUR_LABEL)){
            days = Integer.parseInt(time[0].trim());
            hour = Integer.parseInt(time[1].trim());
        }

        if(durationWithoutAccents.contains(HOUR_LABEL) && durationWithoutAccents.contains(MINUTE_LABEL)){
            hour = Integer.parseInt(time[0].trim());
            mins = Integer.parseInt(time[1].trim());
        }

        if(durationWithoutAccents.contains(MINUTE_LABEL) && durationWithoutAccents.contains(SECOND_LABEL)) {
            mins = Integer.parseInt(time[0].trim());
            secs = Integer.parseInt(time[1].trim());
        }

        return new PGInterval(yearZero, monthZero, days, hour, mins, secs);
    }

    public static String removeAccents(String toClean){
        return Normalizer.normalize(toClean, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
