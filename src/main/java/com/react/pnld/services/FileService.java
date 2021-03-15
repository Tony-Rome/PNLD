package com.react.pnld.services;

import com.react.pnld.model.ScheduleFileLoadDTO;
import com.react.pnld.model.ProcesaArchivoDTO;
import com.react.pnld.model.ScheduleFileLoadResponse;
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

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${copy.path.files}")
    private String FILE_PATH;

    @Autowired
    private FileUtilService fileUtilService;

    @Autowired
    private FileRepository fileRepository;

    public ScheduleFileLoadResponse scheduleLoad(ScheduleFileLoadDTO scheduleFileLoadDTO){

        ScheduleFileLoadResponse scheduleFileLoadResponse =
                new ScheduleFileLoadResponse("OK", "finish schedule file load");

        if(! this.isValidCsvHeader(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. headers invalid");
            scheduleFileLoadResponse.setResponse("NOK");
            scheduleFileLoadResponse.setDescription("headers invalid");
        }

        if(!this.copyAtFileSystem(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. copy at file system is not complete");
            scheduleFileLoadResponse.setResponse("NOK");
            scheduleFileLoadResponse.setDescription("copy at file system is not complete");
        }

        if(!this.queueLoad(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. queue load is not succesfull");
            scheduleFileLoadResponse.setResponse("NOK");
            scheduleFileLoadResponse.setDescription("queue load is not succesfull");
        }
        return scheduleFileLoadResponse;
    }

    public boolean isValidCsvHeader(ScheduleFileLoadDTO scheduleFileLoadDTO) {
        logger.info("isValidCsvHeader. csvFile={}", scheduleFileLoadDTO);
        String firstLine = "";
        try {
            InputStreamReader isr = new InputStreamReader(scheduleFileLoadDTO.getUploadFile().getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            firstLine = br.readLine();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }

        String cleanHeaders = fileUtilService.removeSymbols(firstLine);
        String[] headersFromFile = cleanHeaders.split(",");
        String[] selectedHeadersArray = fileUtilService.selectedHeadersArray(scheduleFileLoadDTO.getSelectedType());
        boolean isHeadersEquals = fileUtilService.isStringArraysEquals(headersFromFile, selectedHeadersArray);

        logger.info("isValidCsvHeader. isHeadersEquals={}", isHeadersEquals);
        return isHeadersEquals;
    }

    public boolean copyAtFileSystem(ScheduleFileLoadDTO scheduleFileLoadDTO){

        try {
            String originalFilename = scheduleFileLoadDTO.getUploadFile().getOriginalFilename();
            logger.info("copyAtFileSystem. originalFilename={}", originalFilename);
            String extensionFile = fileUtilService.getFileExtension(originalFilename);
            logger.info("copyAtFileSystem. extensionFile={}", extensionFile);
            logger.info("copyAtFileSystem. filename entered by user, csvFile.getName={}", scheduleFileLoadDTO.getName());
            String finalName = FILE_PATH + scheduleFileLoadDTO.getName() + extensionFile;

            File dest = new File(finalName);

            //Check if the directory exists
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }

            scheduleFileLoadDTO.getUploadFile().transferTo(dest);

            return true;
        } catch (IOException ioException){
            logger.error("copyAtFileSystem. ioException.getMessage()={}", ioException.getMessage());
            ioException.getStackTrace();
            return false;
        }
    }

    public boolean queueLoad(ScheduleFileLoadDTO scheduleFileLoadDTO){

        ProcesaArchivoDTO procesaArchivoDTO = new ProcesaArchivoDTO();
        procesaArchivoDTO.setNombreArchivo(scheduleFileLoadDTO.getName());
        procesaArchivoDTO.setTipoArchivo(scheduleFileLoadDTO.getSelectedType());
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