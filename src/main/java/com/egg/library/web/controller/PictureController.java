package com.egg.library.web.controller;

import com.egg.library.domain.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@Controller
@RequestMapping("/pictures")
public class PictureController {
    @Autowired
    private PictureService pictureService;

    @PostMapping("/create")
    public void  uploadImage(@RequestParam("folder") String folder,
                             @RequestParam("id") String id,
                             @RequestParam("name") String name,
                             @RequestParam("file") MultipartFile multipartFile,
                             UriComponentsBuilder componentsBuilder){
        pictureService.createPicture(folder,id,name,multipartFile);

    }
}
