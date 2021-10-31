package com.egg.library.domain.service;

import com.egg.library.domain.PictureVO;
import com.egg.library.domain.repository.PictureVORepository;
import com.egg.library.exeptions.ConflictException;
import com.egg.library.exeptions.FieldInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.NoSuchElementException;

@Service
public class PictureService {

    @Autowired
    private PictureVORepository pictureVORepository;

    @Autowired
    private PictureVO pictureVO;

    /*
    public final String AUTHORS_UPLOADED_FOLDER ="images/authors/";
    public final String BOOKS_UPLOADED_FOLDER ="images/books/";
    public final String CUSTOMERS_UPLOADED_FOLDER ="images/customers/";*/
    public final Boolean DISCHARGE = Boolean.TRUE;

    @Transactional
    public PictureVO createPicture(String folderLocation, String id, String name, MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("Is not selecting the image to upload");
        }
        String nameFormated = name.replaceAll("\\s","");
        String finalPath = createPath(multipartFile,folderLocation,id,nameFormated);
        String relativPath =finalPath.substring(25);
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(finalPath);
            Files.write(path, bytes);

            pictureVO.setMime(multipartFile.getContentType());
            pictureVO.setName(multipartFile.getName());
            pictureVO.setPath(relativPath);
            pictureVO.setDischarge(DISCHARGE);
            pictureVORepository.create(pictureVO);
            return pictureVO;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    public String createPath(MultipartFile multipartFile,String folder, String id, String name){

        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        String dateName = dateFormat.format(date);
        String fileName = id+"-"+name+"-" + dateName + "." + multipartFile.getContentType().split("/")[1];
        String finalPath = folder + fileName;

        return finalPath;
    }

    @Transactional
    public PictureVO updatePicture(PictureVO pictureVO,String folderLocation, String id, String name, MultipartFile multipartFile){

        if (multipartFile.isEmpty()) {
            throw new FieldInvalidException("Is not selecting the image to upload");
        }

        String fileName = "src/main/resources/static"+pictureVO.getPath();

        Path path = Paths.get(fileName);
        File f = path.toFile();
        if (f.exists()) {
            f.delete();
        }

        String finalPath = createPath(multipartFile,folderLocation,id,name);
        String relativPath =finalPath.substring(25);

        try {
            byte[] bytes = multipartFile.getBytes();
            path = Paths.get(finalPath);
            Files.write(path, bytes);

            this.pictureVO= pictureVO;
            pictureVO.setMime(multipartFile.getContentType());
            pictureVO.setName(multipartFile.getName());
            pictureVO.setPath(relativPath);
            pictureVO.setDischarge(DISCHARGE);
            pictureVORepository.update(pictureVO);
            return pictureVO;
        }catch (Exception e) {
            e.printStackTrace();
            throw new ConflictException("Error during upload: " + multipartFile.getOriginalFilename());
        }
    }

    @Transactional(readOnly = true)
    public PictureVO getByPath(String path){
        return pictureVORepository.getByPath(path).orElseThrow(()-> new NoSuchElementException("Picture not found"));
    }
}
