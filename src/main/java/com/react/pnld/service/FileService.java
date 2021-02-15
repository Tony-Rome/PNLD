package com.react.pnld.service;

import com.react.pnld.model.CsvFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileService {

    @Value( "${copy.path.files}" )
    private String FILE_PATH;

    @Value( "${csv.headers.teacher-roster}" )
    private String[] teacherRosterHeaders;

    public boolean scheduleLoad(CsvFile csvFile){

        if(! this.isValidCsvHeader(csvFile)){
            return false;
        }

        if(!this.copyAtFileSystem(csvFile)){
            return false;
        }

        if(!this.queueLoad(csvFile)){
            return false;
        }

        return true;
    }

    public boolean isValidCsvHeader(CsvFile csvFile) {

        //System.out.println(teacherRosterHeaders[0]);

        List<String> csvFileHeaders = new ArrayList<>();
        //TODO get headers;
        InputStreamReader isr = null;
        try {
            isr = new InputStreamReader(csvFile.getUploadFile().getInputStream(), StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(isr);
            System.out.println(br.readLine());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //TODO clean headers


        //TODO finish validation
        return true;
    }

    public boolean copyAtFileSystem(CsvFile csvFile){

        //TODO finish copy
        String fileName = csvFile.getUploadFile().getOriginalFilename();
        System.out.println("The name of the uploaded file is:" + fileName);
        String path = FILE_PATH + fileName;
        try {
            File dest = new File(path); //Check if the directory exists
            if(!dest.getParentFile().exists()){
                dest.getParentFile().mkdirs();
            }
            csvFile.getUploadFile().transferTo(dest);
        } catch (IOException ioException){
            ioException.getStackTrace();
        }

        return true;
    }

    public boolean queueLoad(CsvFile csvFile){

        //TODO finish queue load csv file

        return true;
    }
}
