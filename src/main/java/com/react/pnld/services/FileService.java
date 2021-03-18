package com.react.pnld.services;

import com.react.pnld.model.LoadedFile;
import com.react.pnld.model.dto.ScheduleFileLoadDTO;
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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);
    private final int FILE_STATE_SCHEDULED = 1;
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

        LoadedFile loadedFile = new LoadedFile();
        loadedFile.setFileName(scheduleFileLoadDTO.getName());
        loadedFile.setFileType(scheduleFileLoadDTO.getSelectedType());
        loadedFile.setLoadedDate(scheduleFileLoadDTO.getLoadedOnDateTime());
        //TODO identify loadedByUserId with csvFile.getLoadedBy(), logged user
        loadedFile.setLoadedByUserId(1);
        loadedFile.setStateId(FILE_STATE_SCHEDULED);
        loadedFile.setProcessDate(null);
        loadedFile.setTotalRecords(0);
        loadedFile.setDuplicateRecords(0);
        loadedFile.setNewRecords(0);
        int responseInsert = fileRepository.insertProcessFile(loadedFile);

        if(responseInsert == 0){
            return false;
        }
        return true;
    }

    @Scheduled(cron = "${cron.process-loadscheduled}", zone = "UTC")
    public void executeFileLoadScheduled(){

        LocalDateTime endDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        logger.info("executeFileLoadScheduled. endDateTime={}", endDateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(1L);
        logger.info("executeFileLoadScheduled. startDateTime={}", startDateTime);

        //select scheduled files
        List<LoadedFile> filesLoadedScheduled = fileRepository.getLoadedFilesByState(FILE_STATE_SCHEDULED,
                Timestamp.valueOf(startDateTime), Timestamp.valueOf(endDateTime));
        logger.info("executeFileLoadScheduled. filesLoadedScheduled={}", filesLoadedScheduled);


        //TODO read file's content
        //TODO validate load records by file's type
            //TODO insert records if not exist
        //TODO resume load file
        //TODO update record load file
    }

}