package com.react.pnld.dto;

import com.react.pnld.model.Teacher;
import com.react.pnld.model.Training;
import com.react.pnld.services.FileUtilService;

 public interface TeacherPersonDTO {

     String getRut();

     int getAge();

     String getDepartment();

     boolean getParticipatedInPNLD();

     String getInLevels();

     String getSubjects();

     String getCsResources();

     String getRoboticsResources();

     String getName();

     String getLastNames();

     String getEmail();

     String getGender();


}
