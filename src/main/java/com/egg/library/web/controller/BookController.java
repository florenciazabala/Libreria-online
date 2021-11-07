package com.egg.library.web.controller;


import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.BookVO;
import com.egg.library.domain.EditorialVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.service.AuthorService;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.EditorialService;
import com.egg.library.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/books")
public class BookController {


    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private EditorialService editorialService;

    @Autowired
    private LoanService loanService;

    @GetMapping(value = "/all")
    public ModelAndView showBooks(){

        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findAllBooks());
        mav.addObject("title","Listado de libros");
        mav.addObject("checked",false);
        return mav;
    }

    @GetMapping(value = "",params = "genero")
    public ModelAndView showByGenre(@RequestParam("genero") String genero){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findByGenre(genero));
        mav.addObject("title","Listado de libros del género '"+genero+"' ");
        return mav;
    }

    @GetMapping("/dismiss")
    public ModelAndView showDismissbooks(){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findDismissBooks());
        mav.addObject("title","Listado de libros dados de baja");
        return mav;
    }

    @GetMapping("/avaible")
    public ModelAndView showAvaibleToLoan(){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findAvaibleBooks());
        mav.addObject("checked",true);
        mav.addObject("title","Listado de libros disponibles de alquilar");
        return mav;
    }

    @GetMapping(value = "",params = "search")
    public ModelAndView showByAllFields(@RequestParam("search") String search){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findAllFields(search));
        mav.addObject("title","Listado de libros");
        return mav;
    }

    @GetMapping (value = "/{isbn}")
    public ModelAndView shearchById(@PathVariable Long isbn){
        ModelAndView mav = new ModelAndView("bookDetail");
        mav.addObject("book",bookService.findByIsbn(isbn));
        List<Integer> loansId = bookService.findByIsbn(isbn).getLoans().keySet().stream().collect(Collectors.toList());
        List<LoanVO> loanVOS = loansId.stream().map(l -> loanService.findById(l)).collect(Collectors.toList());
        mav.addObject("loans",loanVOS);
        return mav;
    }

    @GetMapping(value = "/create")
    public ModelAndView createBook(){
        ModelAndView mav = new ModelAndView("formBook");
        mav.addObject("book",new BookVO());
        mav.addObject("authors",  authorService.findAll());
        mav.addObject("editorials",editorialService.findAllEditorials());
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping(value = "/update/{isbn}")
    public ModelAndView updateBook(@PathVariable Long isbn){
        ModelAndView mav = new ModelAndView("formBook");

        mav.addObject("book",bookService.findByIsbn(isbn));
        mav.addObject("authors", authorService.findAll());
        mav.addObject("editorials",editorialService.findAllEditorials());
        mav.addObject("action", "saveModifications");
        return mav;
    }

    @PostMapping(value = "/save")
    public RedirectView saveBook(@RequestParam Long isbn,
                                 @RequestParam String title,@RequestParam Integer year,
                                 @RequestParam("genero") String genre,@RequestParam("author") Integer author,
                                 @RequestParam("editorial") Integer editorial,
                                 @RequestParam Integer copy,@RequestParam Integer loanedCopy){
        try{
            AuthorVO authorVO = authorService.findById(author);
            EditorialVO editorialVO = editorialService.findById(editorial);

            bookService.create(isbn,title,year,genre,authorVO,editorialVO,copy,loanedCopy);
        }catch (Exception e){

        }

        return new RedirectView("/books/all");
    }

    @PostMapping(value = "/saveModifications")
    public RedirectView saveModificationsBook(@RequestParam Long isbn,
                                              @RequestParam String title, @RequestParam Integer year,
                                              @RequestParam("genero") String genre, @RequestParam("author") Integer author,
                                              @RequestParam("editorial") Integer editorial,
                                              @RequestParam Integer copy, @RequestParam Integer loanedCopy){


        AuthorVO authorVO = authorService.findById(author);

        EditorialVO editorialVO = editorialService.findById(editorial);

        bookService.update(isbn,title,year,genre,authorVO,editorialVO,copy,loanedCopy);
        return new RedirectView("/books/all");
    }

    @GetMapping (value = "/delete/{isbn}")
    public RedirectView deleteBook(@PathVariable Long isbn){
        bookService.delete(isbn);
        return new RedirectView("/books/all");
    }

    @GetMapping (value = "/discharge/{isbn}")
    public RedirectView dischargeBook(@PathVariable Long isbn){
        bookService.discharge(isbn);
        return new RedirectView("/books/all");
    }


    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        bookService.updatePercentGenre();
    }
}
