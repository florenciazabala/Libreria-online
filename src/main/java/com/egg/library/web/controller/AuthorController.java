package com.egg.library.web.controller;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.service.AuthorService;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.PictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private PictureService pictureService;

    @Autowired
    private BookService bookService;


    @GetMapping(value = "/all")
    public ModelAndView showAuthors(){
        ModelAndView mav = new ModelAndView("authors");
        mav.addObject("authors",  authorService.findAllAuthors());
        return mav;
    }

    @GetMapping (value = "/{id}")
    public ModelAndView shearchById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("authors");
        List<AuthorVO> authorVOS = new ArrayList<>();
        authorVOS.add(authorService.findById(id));
        mav.addObject("authors", authorVOS );
        return mav;
    }

    @GetMapping(value = "/create")
    public ModelAndView createAuthor(){
        ModelAndView mav = new ModelAndView("formAuthor");
        mav.addObject("author",new AuthorVO());
        mav.addObject("title","Crear autor");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping(value = "/update/{id}")
    public ModelAndView updateAuthor(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("formAuthor");
        mav.addObject("author",authorService.findById(id));
        mav.addObject("title","Modificar autor");
        mav.addObject("action", "saveModifications");
        return mav;
    }

    public final String AUTHORS_UPLOADED_FOLDER = "src/main/resources/static/images/authors/";
    @PostMapping(value = "/save")
    public RedirectView saveAuthor(@RequestParam String name, @RequestParam(required=false) MultipartFile picture){
        authorService.createAuthor(name);
        Integer id = authorService.findByName(name).getIdAuthor();
        PictureVO pictureVO = pictureService.createPicture(AUTHORS_UPLOADED_FOLDER,String.valueOf(id),name,picture);
        authorService.updatePicture(pictureVO.getPath(),id);
        return new RedirectView("/authors/all");
    }

    @PostMapping(value = "/saveModifications")
    public RedirectView saveChangesAuthor(@RequestParam("name") String name, @RequestParam("idAuthor") Integer idAuthor, @RequestParam(required=false) MultipartFile picture){
        PictureVO pictureVO = authorService.findById(idAuthor).getPicture();
        if(picture != null){
            if(pictureVO == null){
                pictureVO= pictureService.createPicture(AUTHORS_UPLOADED_FOLDER,String.valueOf(idAuthor),name,picture);
            }else{
                pictureVO= pictureService.updatePicture(pictureVO,AUTHORS_UPLOADED_FOLDER,String.valueOf(idAuthor),name,picture);
            }
        }
        authorService.updateAuthor(name,idAuthor);
        authorService.updatePicture(pictureVO.getPath(),idAuthor);
        return new RedirectView("/authors/all");
    }

    @GetMapping (value = "/delete/{id}")
    public RedirectView deleteAuthor(@PathVariable Integer id){
        authorService.delete(id);
        bookService.findByAuthor(id).forEach(book -> bookService.delete(book.getIsbn()));
        return new RedirectView("/authors/all");
    }

    @GetMapping (value = "/discharge/{id}")
    public RedirectView dischargeAuthor(@PathVariable Integer id){
        authorService.discharge(id);
        bookService.findByAuthor(id).forEach(book -> bookService.discharge(book.getIsbn()));
        return new RedirectView("/authors/all");
    }
}
