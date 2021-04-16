package com.react.pnld.repo;

import com.react.pnld.dto.FileTableResumeDTO;
import com.react.pnld.mappers.LoadedFileMapper;
import com.react.pnld.model.LoadedFile;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class FileRepository {

    private static final Logger logger = LoggerFactory.getLogger(FileRepository.class);

    @Autowired
    private LoadedFileMapper loadedFileMapper;

   /* @Autowired
    @Qualifier("sqlSessionFactoryPNLD")
    private  SqlSessionFactory sqlSessionFactory;

    @Autowired
    private LoadedFileMapper getLoadedFileMapper(){
        SqlSession session = this.sqlSessionFactory.openSession();
        LoadedFileMapper loadedFileMapper = session.getMapper(LoadedFileMapper.class);
        return loadedFileMapper;
    }*/

    public int insertProcessFile (LoadedFile loadedFile) throws Exception {


/*
        LoadedFileMapper loadedFileMapper = this.getLoadedFileMapper();
*/
        return loadedFileMapper.insertProcessFile(loadedFile);
    }

    public List<LoadedFile> getLoadedFilesByStateAndTimestamps(int stateId, Timestamp initTime, Timestamp endTime){

/*
        LoadedFileMapper loadedFileMapper = this.getLoadedFileMapper();
*/
        return loadedFileMapper.getLoadedFilesByStateAndTimestamps(stateId, initTime, endTime);
    }

    public int updateFileLoaded(LoadedFile loadedFile){

/*
        LoadedFileMapper loadedFileMapper = this.getLoadedFileMapper();
*/
        return loadedFileMapper.updateLoadedFile(loadedFile);
    }

    public List<FileTableResumeDTO> getUploadedFiles() {

/*
        LoadedFileMapper loadedFileMapper = this.getLoadedFileMapper();
*/

        List<FileTableResumeDTO> filesUploaded = loadedFileMapper.getUploadedFiles();
        System.out.println("LOADEDFILEMAPPER: "+loadedFileMapper.getUploadedFiles().size());
        System.out.println("LOADEDFILEMAPPER: "+loadedFileMapper.getUploadedFiles().size());
        System.out.println("LOADEDFILEMAPPER: "+loadedFileMapper.getUploadedFiles().size());
        System.out.println("LOADEDFILEMAPPER: "+loadedFileMapper.getUploadedFiles().size());
        logger.info("getFilesUploaded. filesUploaded.size={}", filesUploaded.size());

        return filesUploaded;
    }
}
