package com.egg.library.web.controller;

import com.egg.library.domain.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
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

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[]  seeImage(@PathVariable("id") Integer id){
        return pictureService.obtenerFotoPorId(id);
    }
}
