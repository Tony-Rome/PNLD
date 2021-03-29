package com.react.pnld.services;

import com.react.pnld.model.dto.ScheduleFileLoadDTO;
import com.react.pnld.model.dto.ProcesaArchivoDTO;
import com.react.pnld.model.ScheduleFileLoadResponse;
import com.react.pnld.repo.FileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.OffsetDateTime;
import java.time.ZoneId;

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

        scheduleFileLoadDTO.setLoadedBy("1");//TODO remove this line when user logged is identified
        scheduleFileLoadDTO.setLoadedOnDateTime(OffsetDateTime.now(ZoneId.of("UTC")));

        ScheduleFileLoadResponse scheduleFileLoadResponse =
                new ScheduleFileLoadResponse(HttpStatus.BAD_REQUEST);

        if(! this.isValidCsvHeader(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. headers invalid");
            scheduleFileLoadResponse.setDescription("Cabeceras inválidas");
            return scheduleFileLoadResponse;
        }

        if(!this.copyAtFileSystem(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. copy at file system is not complete");
            scheduleFileLoadResponse.setDescription("Copia de archivo al sistema no se pudo completar");
            return scheduleFileLoadResponse;
        }

        if(!this.queueLoad(scheduleFileLoadDTO)){
            logger.info("scheduleLoad. queue load is not succesfull");
            scheduleFileLoadResponse.setDescription("Error en la cola de procesamiento");
            return scheduleFileLoadResponse;
        }

        logger.info("scheduleLoad. file load successfully");
        scheduleFileLoadResponse.setResponse(HttpStatus.OK);
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
            String extensionFile = fileUtilService.getExtension(originalFilename);
            logger.info("copyAtFileSystem. extensionFile={}", extensionFile);

            String loadedDateTimeLikeString = scheduleFileLoadDTO.getLoadedOnDateTime().toString().replace(":","-");
            String loadedDateFormatted = loadedDateTimeLikeString.replace(fileUtilService.getExtension(loadedDateTimeLikeString),"");
            String finalFileName =   loadedDateFormatted + "-" + scheduleFileLoadDTO.getName() + extensionFile;
            logger.info("copyAtFileSystem. finalFileName={}", finalFileName);

            String finalPath = FILE_PATH + finalFileName;
            File dest = new File(finalPath);

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
        procesaArchivoDTO.setFechaCarga(scheduleFileLoadDTO.getLoadedOnDateTime());
        //TODO identify idPersona with csvFile.getLoadedBy()
        procesaArchivoDTO.setIdPersona(1); //TODO set by logged user

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