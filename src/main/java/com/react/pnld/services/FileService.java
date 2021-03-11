package com.react.pnld.services;

import com.react.pnld.controller.FileController;
import com.react.pnld.model.CSVHeadersProperties;
import com.react.pnld.model.CsvFile;
import com.react.pnld.model.ProcesaArchivoDTO;
import com.react.pnld.repo.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${copy.path.files}")
    private String FILE_PATH;

    @Autowired
    private CSVHeadersProperties csvHeadersProperties;

    @Autowired
    private FileRepository fileRepository;

    public boolean scheduleLoad(CsvFile csvFile){

        if(! this.isValidCsvHeader(csvFile)){
            logger.info("scheduleLoad. headers invalid");
            return false;
        }

        if(!this.copyAtFileSystem(csvFile)){
            logger.info("scheduleLoad. copy at file system is not complete");
            return false;
        }

        if(!this.queueLoad(csvFile)){
            logger.info("scheduleLoad. queue load not succesfull");
            return false;
        }

        logger.info("scheduleLoad. finished");
        return true;
    }

    public boolean isValidCsvHeader(CsvFile csvFile) {

        logger.info("isValidCsvHeader. csvFile={}", csvFile);

        List<String> csvFileHeaders = new ArrayList<>();
        String firstLine = "";
        try {
            InputStreamReader isr = new InputStreamReader(csvFile.getUploadFile().getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            firstLine = br.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }

        //clean headers
        String cleanHeaders = removeSymbols(firstLine);
        String[] headersFromFile = cleanHeaders.split(",");
        String[] selectedHeadersArray = this.selectedHeadersArray(csvFile.getSelectedType());
        boolean isHeadersEquals = isStringArraysEquals(headersFromFile, selectedHeadersArray);

        logger.info("isValidCsvHeader. isHeadersEquals={}", isHeadersEquals);
        return isHeadersEquals;
    }

    public String removeSymbols(String strToClean){
        String strCleaned = strToClean.replaceAll("[^a-zA-Z0-9,]", "");
        return strCleaned.toLowerCase();
    }

    public String[] selectedHeadersArray(String selectedType){

        switch (selectedType){

            case "teacher-roster":
                return csvHeadersProperties.getTeacherRoster();

            case "teacher-opt-in":
                return csvHeadersProperties.getTeacherOptIn();

            case "student-level":
                return csvHeadersProperties.getStudentLevel();

            case "signin-per-course":
                return csvHeadersProperties.getSigninPerCourse();

            case "sign-ins":
                return csvHeadersProperties.getSignIns();

            case "diag":

                return csvHeadersProperties.getDiagnostico();

            case "pre-cap":

                return csvHeadersProperties.getPreCapacita();

            case "post-cap":

                return csvHeadersProperties.getPostCapacita();

            case "pc-1":

                return csvHeadersProperties.getTestPc1();

            case "pc-2":

                return csvHeadersProperties.getTestPc2();

            case "pc-3":

                return csvHeadersProperties.getTestPc3();

            case "salida":

                return csvHeadersProperties.getSalida();

            case "satis":
                return csvHeadersProperties.getSatis();

            default:
                return null;
        }
    }

    public boolean isStringArraysEquals(String[] firstArray, String[] secondArray){
        logger.info("isStringArraysEquals. firstArray={}, secondArray={}", firstArray, secondArray);

        String[] firstArraySorted = firstArray;
        String[] secondArraySorted = secondArray;

        Arrays.sort(firstArraySorted);
        Arrays.sort(secondArraySorted);
        return Arrays.equals(firstArraySorted, secondArraySorted);
    }

    public boolean copyAtFileSystem(CsvFile csvFile){

        try {
            String fileName = csvFile.getUploadFile().getOriginalFilename();
            System.out.println("The name of the uploaded file is:" + fileName);
            String path = FILE_PATH + fileName;

            File dest = new File(path); //Check if the directory exists

            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }

            csvFile.getUploadFile().transferTo(dest);

            return true;
        } catch (IOException ioException){
            ioException.getStackTrace();
            return false;
        }
    }

    public boolean queueLoad(CsvFile csvFile){

        //Example for database connection
        //System.out.println("estado archivo:" + fileRepository.getFileState(2));

        ProcesaArchivoDTO procesaArchivoDTO = new ProcesaArchivoDTO();
        procesaArchivoDTO.setNombreArchivo(csvFile.getName());
        procesaArchivoDTO.setTipoArchivo(csvFile.getSelectedType());
        //TODO identify idPersona with csvFile.getLoadedBy()

        int responseInsert = fileRepository.insertProcessFile(procesaArchivoDTO);

        if(responseInsert == 0){
            return false;
        }
        return true;
    }

    @Scheduled(cron = "${cron.process-loadscheduled}", zone = "UTC")
    public void executeScheduledLoadFile(){
        //System.out.println("Time instant UTC: "+Instant.now());
        //TODO select scheduled files
        //TODO read file's content
        //TODO validate load records by file's type
            //TODO insert records if not exist
        //TODO resume load file
        //TODO update record load filex
    }

}