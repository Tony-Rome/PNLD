package com.react.pnld.services;

import com.react.pnld.dto.*;
import com.react.pnld.interceptor.PNLDInterceptor;
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
import java.text.Normalizer;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

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
    private LoaderCTFiles loaderCTFiles;

    private CsvParserSettings csvParserSettings;

    public FileUtilService() {
        csvParserSettings = new CsvParserSettings();
        csvParserSettings.setProcessorErrorHandler(new PNLDInterceptor());
        csvParserSettings.setLineSeparatorDetectionEnabled(true);
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

            case CT_STUDENTS_ONE:
                return csvHeadersProperties.getTestCT1();

            case CT_STUDENTS_TWO:
                return csvHeadersProperties.getTestCT2();

            case CT_TEACHERS:
                return csvHeadersProperties.getTestCT3();

            case SATISFACTION:
                return csvHeadersProperties.getSatisfaction();

            case GENERAL_RESUME:
                return csvHeadersProperties.getGeneralResumeArray();

            default:
                return new String[1];
        }
    }

    public boolean isStringArraysEquals(String[] firstArray, String[] secondArray) {
        logger.info("isStringArraysEquals. firstArray={}", Arrays.toString(firstArray));
        logger.info("isStringArraysEquals. secondArray={}", Arrays.toString(secondArray));

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

    private void closeReader(Reader loadedFileReader){
        try {
            loadedFileReader.close();
        } catch (IOException e) {
            logger.error("closeReader. message={}", e.getMessage(), e);
        }
    }

    public <T> List<T> parseRowsToBeans(Reader reader, Class<T> clazz) {
        BeanListProcessor<T> rowProcessor = new BeanListProcessor<T>(clazz);
        csvParserSettings.setProcessor(rowProcessor);
        csvParserSettings.setHeaderExtractionEnabled(true);
        csvParserSettings.setDelimiterDetectionEnabled(true, csvHeadersProperties.getDelimiters().toCharArray());

        CsvParser parser = new CsvParser(csvParserSettings);
        parser.parse(reader);
        List<T> rowsLikeBeans = rowProcessor.getBeans();
        return rowsLikeBeans;
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
                closeReader(loadedFileReader);
                return this.loaderMoodleFile.processDiagnosticFileRows(diagnosticRows, loadedFile.getId());

            case PRE_TRAINING:
            case POST_TRAINING:
                List<TrainingFileDTO> trainingRows = parseRowsToBeans(loadedFileReader, TrainingFileDTO.class);
                closeReader(loadedFileReader);
                return loaderMoodleFile.processTrainingFileRows(trainingRows, loadedFile.getId(), loadedFile.getType());

            case SATISFACTION:
                List<SatisfactionFileDTO> satisfactionRows = parseRowsToBeans(loadedFileReader, SatisfactionFileDTO.class);
                closeReader(loadedFileReader);
                return this.loaderMoodleFile.processSatisfactionFileRows(satisfactionRows, loadedFile.getId());

            case CT_STUDENTS_ONE:
                List<CTRowGroupOneStudentsDTO> ctFirstGroupStudentsRows = parseRowsToBeans(loadedFileReader, CTRowGroupOneStudentsDTO.class);
                closeReader(loadedFileReader);
                return this.loaderCTFiles.processStudentsGroupOneRows(ctFirstGroupStudentsRows);

            case CT_STUDENTS_TWO:
                return this.loaderCTFiles.processStudentsGroupTwoRows(null);

            case CT_TEACHERS:
                List<CTRowTeacherDTO> ctRowsTeacher = parseRowsToBeans(loadedFileReader, CTRowTeacherDTO.class);
                closeReader(loadedFileReader);
                return this.loaderCTFiles.processTeacherRows(ctRowsTeacher);

            case GENERAL_RESUME:
                List<GeneralResumeTrainingDTO> generalResumeTrainingRows = parseRowsToBeans(loadedFileReader,
                        GeneralResumeTrainingDTO.class);
                closeReader(loadedFileReader);
                return this.loaderMoodleFile.processGeneralResumeRows(generalResumeTrainingRows, loadedFile.getId());

            default:
                return new FileResumeDTO(0, 0, 0, 0);
        }
    }

    public static LocalDateTime getLocalDateFrom(String stringDate) {

        String stringFormatted = stringDate.replaceAll(" de ", "/").replaceAll("\\s+|\\t", " ");
        ;
        String formatPattern = "d/MMMM/yyyy H:m";
        try {
            return LocalDateTime.parse(stringFormatted, DateTimeFormatter.ofPattern(formatPattern,
                    new Locale("es", "ES")));
        } catch (DateTimeException dateTimeException) {
            logger.error("getLocalDateFrom.", dateTimeException.getMessage(), dateTimeException);
            return LocalDateTime.MIN;
        }
    }

    public static String removeAccents(String toClean) {
        if(toClean == null) return new String();
        return Normalizer.normalize(toClean, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", "");
    }
}
