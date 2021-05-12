package com.react.pnld.services;

import com.react.pnld.controller.response.ScheduleFileLoadResponse;
import com.react.pnld.dto.FileResumeDTO;
import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.dto.FileTypes;
import com.react.pnld.dto.ScheduleFileLoadDTO;
import com.react.pnld.model.LoadedFile;
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
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class FileService {

    private static final Logger logger = LoggerFactory.getLogger(FileService.class);

    @Value("${copy.path.files}")
    private String FILE_PATH;

    @Autowired
    private FileUtilService fileUtilService;

    @Autowired
    private FileRepository fileRepository;

    public ScheduleFileLoadResponse scheduleLoad(ScheduleFileLoadDTO scheduleFileLoadDTO) {

        scheduleFileLoadDTO.setLoadedBy(scheduleFileLoadDTO.getLoadedBy());
        scheduleFileLoadDTO.setLoadedOnDateTime(LocalDateTime.now(ZoneId.of("UTC")));

        ScheduleFileLoadResponse scheduleFileLoadResponse =
                new ScheduleFileLoadResponse(HttpStatus.BAD_REQUEST);

        if (!this.isValidCsvHeader(scheduleFileLoadDTO)) {
            logger.info("scheduleLoad. headers invalid");
            scheduleFileLoadResponse.setDescription("Cabeceras inválidas");
            return scheduleFileLoadResponse;
        }

        if (!this.copyAtFileSystem(scheduleFileLoadDTO)) {
            logger.info("scheduleLoad. copy at file system is not complete");
            scheduleFileLoadResponse.setDescription("Copia de archivo al sistema no se pudo completar");
            return scheduleFileLoadResponse;
        }

        if (!this.queueLoad(scheduleFileLoadDTO)) {
            logger.info("scheduleLoad. queue load is not succesfull");
            scheduleFileLoadResponse.setDescription("Error en la cola de procesamiento");
            rollbackCopyAtFileSystem(scheduleFileLoadDTO);
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

    public boolean copyAtFileSystem(ScheduleFileLoadDTO scheduleFileLoadDTO) {

        try {
            String nameWithPath = FILE_PATH + fileUtilService.getLoadedFileName(scheduleFileLoadDTO);
            File fileLoaded = new File(nameWithPath);

            //Check if the directory exists
            if (!fileLoaded.getParentFile().exists()) {
                fileLoaded.getParentFile().mkdirs();
            }

            scheduleFileLoadDTO.getUploadFile().transferTo(fileLoaded);
            return true;

        } catch (IOException ioException) {
            logger.error(ioException.getMessage(), ioException);
            return false;
        }
    }

    public boolean rollbackCopyAtFileSystem(ScheduleFileLoadDTO scheduleFileLoadDTO) {

        try {
            String fileName = fileUtilService.getLoadedFileName(scheduleFileLoadDTO);
            Path path = FileSystems.getDefault().getPath(FILE_PATH, fileName);
            return Files.deleteIfExists(path);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return false;
        }
    }

    public boolean queueLoad(ScheduleFileLoadDTO scheduleFileLoadDTO) {

        LoadedFile loadedFile = new LoadedFile();
        loadedFile.setName(fileUtilService.getLoadedFileName(scheduleFileLoadDTO));
        loadedFile.setStoredIn(FILE_PATH);
        loadedFile.setType(scheduleFileLoadDTO.getSelectedType());
        loadedFile.setLoadedDate(scheduleFileLoadDTO.getLoadedOnDateTime());
        loadedFile.setLoadedByAdmin(scheduleFileLoadDTO.getLoadedBy());
        loadedFile.setStateId(FileTypes.STATE_SCHEDULED);
        loadedFile.setProcessDate(null);
        loadedFile.setTotalRecords(0);
        loadedFile.setDuplicateRecords(0);
        loadedFile.setNewRecords(0);

        try {
            int insertResult = fileRepository.insertProcessFile(loadedFile);
            logger.info("queueLoad. insertResult={}", insertResult);
            return true;
        } catch (Exception exception) {
            logger.error(exception.getMessage(), exception);
            return false;
        }
    }

    @Scheduled(cron = "${cron.process-loadscheduled}", zone = "UTC")
    public void executeFileLoadScheduled() {

        LocalDateTime endDateTime = LocalDateTime.now(ZoneId.of("UTC"));
        logger.info("executeFileLoadScheduled. endDateTime={}", endDateTime);
        LocalDateTime startDateTime = endDateTime.minusDays(1L);
        logger.info("executeFileLoadScheduled. startDateTime={}", startDateTime);

        List<LoadedFile> filesLoadedScheduled = fileRepository.getLoadedFilesNotProcessed();
        logger.info("executeFileLoadScheduled. filesLoadedScheduled={}", filesLoadedScheduled);

        for (LoadedFile loadedFile : filesLoadedScheduled) {
            loadedFile.setStateId(FileTypes.STATE_IN_PROCESS);
            this.fileRepository.updateFileLoaded(loadedFile);

            FileResumeDTO resume = fileUtilService.processLoadedFile(loadedFile);

            loadedFile.setProcessDate(LocalDateTime.now(ZoneId.of("UTC")));
            loadedFile.setStateId(FileTypes.FILE_STATE_PROCESSED);
            loadedFile.setTotalRecords(resume.getTotalRecords());
            loadedFile.setNewRecords(resume.getNewRecords());
            loadedFile.setDuplicateRecords(resume.getDuplicatedRecords());
            
            logger.info("executeFileLoadScheduled. updated loadedFile={}", loadedFile);
        }
    }

    public List<FileTableResumeDTO> getUploadedFiles() {
        return fileRepository.getUploadedFiles();
    }
}