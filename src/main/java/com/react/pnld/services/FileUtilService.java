package com.react.pnld.services;

import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.dto.ParsedFileDTO;
import com.react.pnld.model.dto.ProcessedParsedFileResumeDTO;
import com.univocity.parsers.common.processor.RowListProcessor;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileUtilService {

    private static final Logger logger = LoggerFactory.getLogger(FileUtilService.class);

    @Autowired
    private CSVHeadersProperties csvHeadersProperties;

    private CsvParserSettings csvParserSettings;

    private RowListProcessor rowListProcessor;

    private CsvParser csvParser;

    public FileUtilService(){
        csvParserSettings = new CsvParserSettings();
        csvParserSettings.setLineSeparatorDetectionEnabled(true);
        rowListProcessor = new RowListProcessor();
        csvParserSettings.setProcessor(rowListProcessor);
        csvParserSettings.setHeaderExtractionEnabled(true);
        csvParser = new CsvParser(csvParserSettings);
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

    public ParsedFileDTO getParsedFile(String pathName){

        try (Reader inputReader = new InputStreamReader(new FileInputStream(pathName), "UTF-8")) {

            csvParser.parse(inputReader);
            String[] headers = rowListProcessor.getHeaders();
            List<String[]> rows = rowListProcessor.getRows();

            ParsedFileDTO parsedFile = new ParsedFileDTO();
            parsedFile.setHeaders(headers);
            parsedFile.setRows(rows);
            logger.info("getParsedFile. parsedFile={}", parsedFile);
            return  parsedFile;
        } catch (IOException ioe) {
            logger.error(ioe.getMessage(), ioe);
            logger.info("getParsedFile. returning empty parsed file");
            return new ParsedFileDTO();
        }
    }

    public ProcessedParsedFileResumeDTO processParsedFile(ParsedFileDTO parsedFileDTO){

        switch (FileTypes.valueOfLabel(parsedFileDTO.getFileType())){

            case TEACHER_ROSTER:
                return this.processTeacherRosterFile(parsedFileDTO);

            case TEACHER_OPT_IN:
                return this.processTeacherOptInFile(parsedFileDTO);

            case STUDENT_LEVEL:
                return this.processStudentLevelFile(parsedFileDTO);

            case SIGNIN_PER_COURSE:
                return this.processSignInPerCourseFile(parsedFileDTO);

            case SIGN_INS:
                return this.processSignInsFile(parsedFileDTO);

            case DIAGNOSTICO:
                return this.diagnosticoFile(parsedFileDTO);

            case PRE_CAPACITA:
                return this.preCapacitaFile(parsedFileDTO);

            case POST_CAPACITA:
                return this.postCapacitaFile(parsedFileDTO);

            case TEST_PC_1:
                return this.testPCOneFile(parsedFileDTO);

            case TEST_PC_2:
                return this.testPCTwoFile(parsedFileDTO);

            case TEST_PC_3:
                return this.testPCThreeFile(parsedFileDTO);

            case SALIDA:
                return this.salidaFile(parsedFileDTO);

            case SATIS:
                return this.satisFile(parsedFileDTO);

            default:
                return new ProcessedParsedFileResumeDTO(0,0,0);
        }
    }

    public ProcessedParsedFileResumeDTO processTeacherRosterFile(ParsedFileDTO teacherRosterParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        logger.info("processTeacherRosterFile init");
        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO processTeacherOptInFile(ParsedFileDTO teacherOptInParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO processStudentLevelFile(ParsedFileDTO studentLevelParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO processSignInPerCourseFile(ParsedFileDTO signInPerCourseParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO processSignInsFile(ParsedFileDTO signInsParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO diagnosticoFile(ParsedFileDTO diagnosticoParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO preCapacitaFile(ParsedFileDTO preCapacitaParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO postCapacitaFile(ParsedFileDTO postCapacitaParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO testPCOneFile(ParsedFileDTO testPCOneParsedFile){
        //TODO validate load records by file's type



        //TODO insert records if not exist

        logger.info("testPCOneFile init");
        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO testPCTwoFile(ParsedFileDTO testPCTwoParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO testPCThreeFile(ParsedFileDTO testPCThreeParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO salidaFile(ParsedFileDTO salidaParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }

    public ProcessedParsedFileResumeDTO satisFile(ParsedFileDTO satisParsedFile){
        //TODO validate load records by file's type
        //TODO insert records if not exist

        return new ProcessedParsedFileResumeDTO();
    }
}
