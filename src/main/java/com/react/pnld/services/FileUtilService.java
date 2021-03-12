package com.react.pnld.services;

import com.react.pnld.model.CSVHeadersProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class FileUtilService {

    private static final Logger logger = LoggerFactory.getLogger(FileUtilService.class);

    @Autowired
    private CSVHeadersProperties csvHeadersProperties;

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

            case "diagnostico":
                return csvHeadersProperties.getDiagnostico();

            case "pre-capacita":
                return csvHeadersProperties.getPreCapacita();

            case "post-capacita":
                return csvHeadersProperties.getPostCapacita();

            case "test-pc-1":
                return csvHeadersProperties.getTestPc1();

            case "test-pc-2":
                return csvHeadersProperties.getTestPc2();

            case "test-pc-3":
                return csvHeadersProperties.getTestPc3();

            case "salida":
                return csvHeadersProperties.getSalida();

            case "satis":
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

    public String getFileExtension(String filename){
        int indexExtensionStart = filename.indexOf(".");
        String fileExtensionName = filename.substring(indexExtensionStart,filename.length());
        return fileExtensionName;
    }


}
