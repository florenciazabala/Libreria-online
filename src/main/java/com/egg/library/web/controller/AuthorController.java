package com.egg.library.web.controller;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.PictureVO;
import com.egg.library.domain.service.AuthorService;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.PictureService;
import com.egg.library.util.pagination.RenderPages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ModelAndView showAuthors(@RequestParam(name = "page",defaultValue = "1")int page, HttpServletRequest request){

        Pageable pageable = PageRequest.of(page -1,8, Sort.by(Sort.Direction.ASC,"nombre"));
        Page<AuthorVO> authorPage = authorService.findAll(pageable);
        RenderPages<AuthorVO> renderPages = new RenderPages<AuthorVO>("/all",authorPage);

        ModelAndView mav = new ModelAndView("authors");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("success", flashMap.get("success-name"));
        }

        mav.addObject("page",renderPages);
        mav.addObject("authors", authorPage);

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
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView createAuthor(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formAuthor");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error-name"));
        }

        mav.addObject("author",new AuthorVO());
        mav.addObject("title","Crear autor");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateAuthor(@PathVariable Integer id, HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formAuthor");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error-name"));
        }

        mav.addObject("author",authorService.findById(id));
        mav.addObject("title","Modificar autor");
        mav.addObject("action", "saveModifications");
        return mav;
    }

    public final String AUTHORS_UPLOADED_FOLDER = "src/main/resources/static/images/authors/";
    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveAuthor(@RequestParam String name, @RequestParam(required = false)@Value("${picture.value:@null}") MultipartFile picture, RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/authors/all");
        try {
            AuthorVO authorVO = authorService.createAuthor(name);
            Integer id = authorVO.getIdAuthor();
            if(picture != null && !picture.isEmpty()) {
                PictureVO pictureVO = pictureService.createPicture(AUTHORS_UPLOADED_FOLDER, String.valueOf(id), name, picture);
                authorService.updatePicture(pictureVO, authorVO);
            }
            attributes.addFlashAttribute("success-name","El autor ha sido creado exitosamente");
        }catch (Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/authors/create");
        }
        return redirectView;
    }

    @PostMapping(value = "/saveModifications")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveChangesAuthor(@RequestParam("name") String name, @RequestParam("idAuthor") Integer idAuthor, @RequestParam(required=false) @Value("${picture.value:@null}") MultipartFile picture,
                                          RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/authors/all");
        try {

            AuthorVO authorVO = authorService.updateAuthor(name, idAuthor);

            PictureVO pictureVO = authorService.findById(idAuthor).getPicture();
            if (picture != null && !picture.isEmpty()) {
                if (pictureVO == null) {
                    pictureVO = pictureService.createPicture(AUTHORS_UPLOADED_FOLDER, String.valueOf(idAuthor), name, picture);
                } else {
                    pictureVO = pictureService.updatePicture(pictureVO, AUTHORS_UPLOADED_FOLDER, String.valueOf(idAuthor), name, picture);
                }
                authorService.updatePicture(pictureVO, authorVO);
            }

            attributes.addFlashAttribute("success-name","El autor ha sido actualizado exitosamente");
        }catch(Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/authors/update/"+idAuthor);
        }
        return redirectView;
    }

    @GetMapping (value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteAuthor(@PathVariable Integer id){
        authorService.delete(id);
        bookService.findByAuthor(id).forEach(book -> bookService.delete(book.getIsbn()));
        return new RedirectView("/authors/all");
    }

    @GetMapping (value = "/discharge/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView dischargeAuthor(@PathVariable Integer id){
        authorService.discharge(id);
        bookService.findByAuthor(id).forEach(book -> bookService.discharge(book.getIsbn()));
        return new RedirectView("/authors/all");
    }
}
