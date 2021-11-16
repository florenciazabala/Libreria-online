package com.egg.library.web.controller;


import com.egg.library.domain.EditorialVO;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.EditorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/editorials")
public class EditorialController {

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private BookService bookService;


    @GetMapping(value = "/all")
    public ModelAndView showeditoriales(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("editorials");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("success", flashMap.get("success-name"));
            mav.addObject("error", flashMap.get("error-name"));
        }

        mav.addObject("editorials",  editorialService.findAllEditorials());
        return mav;
    }

    @GetMapping (value = "/{id}")
    public ModelAndView shearchById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("editorials");
        try {
            List<EditorialVO> editorial = new ArrayList<>();
            editorial.add(editorialService.findById(id));
            mav.addObject("editorials", editorial);
        }catch(NoSuchElementException e){
            return new ModelAndView( "redirect:/editorials/all");
        }
        return mav;
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView createAuthor(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formEditorial");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error-name"));
        }

        mav.addObject("editorial",new EditorialVO());
        mav.addObject("title","Crear editorial");
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping(value = "/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView updateAuthor(@PathVariable Integer id,HttpServletRequest request){
        ModelAndView mav = null;
        try{
            mav = new ModelAndView("formEditorial");

            Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
            if (flashMap != null) {
                mav.addObject("error", flashMap.get("error-name"));
            }

            mav.addObject("editorial",editorialService.findById(id));
            mav.addObject("title","Modificar editorial");
            mav.addObject("action", "saveModifications");
        }catch(NoSuchElementException e){
            return new ModelAndView( "redirect:/editorials/all");
        }
        return mav;
    }

    @PostMapping(value = "/save")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveAuthor(@RequestParam String name,RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/editorials/all");
        try {
            editorialService.createEditorial(name);
            attributes.addFlashAttribute("success-name","La editorial ha sido creado exitosamente");
        }catch (Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/editorials/create");
        }
        return redirectView;
    }

    @PostMapping(value = "/saveModifications")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveChangesAuthor(@RequestParam String name, @RequestParam Integer id, RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/editorials/all");
        try {
            editorialService.updateEditorial(name, id);
            attributes.addFlashAttribute("success-name","La editorial ha sido actualizado exitosamente");
        }catch (Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/editorials/update/"+id);
        }
        return redirectView;
    }

    @GetMapping (value = "/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView deleteAuthor(@PathVariable Integer id){
        try {
            editorialService.delete(id);
            bookService.findByEditorial(id).forEach(book -> bookService.delete(book.getIsbn()));
        }catch(NoSuchElementException e){
            return new RedirectView( "/editorials/all");
        }
        return new RedirectView("/editorials/all");
    }

    @GetMapping (value = "/discharge/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView dischargeAuthor(@PathVariable Integer id){
        try {
            editorialService.discharge(id);
            bookService.findByEditorial(id).forEach(book -> bookService.discharge(book.getIsbn()));
        }catch(NoSuchElementException e){
            return new RedirectView( "/editorials/all");
        }
        return new RedirectView("/editorials/all");
    }
}
