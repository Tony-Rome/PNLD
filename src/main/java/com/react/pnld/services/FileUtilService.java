package com.react.pnld.services;

import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.FileTypes;
import com.react.pnld.dto.PostTrainingDTO;
import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.model.LoadedFile;
import com.univocity.parsers.common.processor.BeanListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
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

    public FileUtilService(){
        csvParserSettings = new CsvParserSettings();
        csvParserSettings.setLineSeparatorDetectionEnabled(true);
    }

    public String removeSymbols(String strToClean){
        String strCleaned = strToClean.replaceAll("[^a-zA-Z0-9,]", "");
        return strCleaned.toLowerCase();
    }

    public String[] selectedHeadersArray(String selectedType){

        switch (FileTypes.valueOfLabel(selectedType)){

            case TEACHER_ROSTER:
                return csvHeadersProperties.getTeacherRoster();

            case TEACHER_OPT_IN:
                return csvHeadersProperties.getTeacherOptIn();

            case STUDENT_LEVEL:
                return csvHeadersProperties.getStudentLevel();

            case SIGNIN_PER_COURSE:
                return csvHeadersProperties.getSigninPerCourse();

            case SIGN_INS:
                return csvHeadersProperties.getSignIns();

            case DIAGNOSTICO:
                return csvHeadersProperties.getDiagnostico();

            case PRE_CAPACITA:
                return csvHeadersProperties.getPreCapacita();

            case POST_CAPACITA:
                return csvHeadersProperties.getPostCapacita();

            case TEST_PC_1:
                return csvHeadersProperties.getTestPc1();

            case TEST_PC_2:
                return csvHeadersProperties.getTestPc2();

            case TEST_PC_3:
                return csvHeadersProperties.getTestPc3();

            case SALIDA:
                return csvHeadersProperties.getSalida();

            case SATIS:
                return csvHeadersProperties.getSatis();

            default:
                return new String[1];
        }
    }

    public boolean isStringArraysEquals(String[] firstArray, String[] secondArray){
        logger.info("isStringArraysEquals. firstArray={}", firstArray);
        logger.info("isStringArraysEquals. secondArray={}", secondArray);

        String[] firstArraySorted = firstArray;
        String[] secondArraySorted = secondArray;

        Arrays.sort(firstArraySorted);
        Arrays.sort(secondArraySorted);
        return Arrays.equals(firstArraySorted, secondArraySorted);
    }

    public String getExtension(String extension){
        int indexExtensionStart = extension.indexOf(".");
        String fileExtensionName = extension.substring(indexExtensionStart,extension.length());
        return fileExtensionName;
    }

    public String getLoadedFileName(ScheduleFileLoadDTO scheduleFileLoadDTO){

        String originalFilename = scheduleFileLoadDTO.getUploadFile().getOriginalFilename();
        logger.info("copyAtFileSystem. originalFilename={}", originalFilename);

        String extensionFile = getExtension(originalFilename);
        logger.info("copyAtFileSystem. extensionFile={}", extensionFile);

        String loadedDateFormattedString = scheduleFileLoadDTO.getLoadedOnDateTime().toString().replace(":","-");
        String loadedDateFormatted = loadedDateFormattedString.replace(getExtension(loadedDateFormattedString),"");
        String finalFileName =   loadedDateFormatted + "-" + scheduleFileLoadDTO.getName() + extensionFile;
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

    public <T> List<T> parseRowsToBeans(String path, Class<T> clazz){
        BeanListProcessor<T> rowProcessor = new BeanListProcessor<T>(clazz);
        csvParserSettings.setProcessor(rowProcessor);
        csvParserSettings.setHeaderExtractionEnabled(true);

        try {
            CsvParser parser = new CsvParser(csvParserSettings);
            parser.parse(getReader(path));
            List<T> rowsLikeBeans = rowProcessor.getBeans();
            return rowsLikeBeans;
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return Collections.emptyList();
        }
    }

    public FileResumeDTO processLoadedFile(LoadedFile loadedFile){

        String path = loadedFile.getStoredIn() + loadedFile.getName();

        switch (FileTypes.valueOfLabel(loadedFile.getType())){

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

            case DIAGNOSTICO:
                return this.loaderMoodleFile.diagnosticoFile(loadedFile);

            case PRE_CAPACITA:
                return this.loaderMoodleFile.preCapacitaFile(loadedFile);
            case POST_CAPACITA:

                List<PostTrainingDTO> postTrainingRows = parseRowsToBeans(path, PostTrainingDTO.class);
                return loaderMoodleFile.processPostTrainingRows(postTrainingRows, loadedFile.getId());


            case SALIDA:
                return this.loaderMoodleFile.salidaFile(loadedFile);

            case SATIS:
                return this.loaderMoodleFile.satisFile(loadedFile);

            case TEST_PC_1:
                return this.loaderCPFile.testPCOneFile(loadedFile);

            case TEST_PC_2:
                return this.loaderCPFile.testPCTwoFile(loadedFile);

            case TEST_PC_3:
                return this.loaderCPFile.testPCThreeFile(loadedFile);

            default:
                return new FileResumeDTO(0,0,0);
        }
    }
}
