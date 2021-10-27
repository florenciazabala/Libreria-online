package com.egg.library.web.controller;


import com.egg.library.domain.BookVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.service.AuthorService;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.EditorialService;
import com.egg.library.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
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
        bookService.updatePercentGenre();
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findAllBooks());
        return mav;
    }

    @GetMapping(value = "",params = "genero")
    public ModelAndView showByGenre(@RequestParam("genero") String genero){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findByGenre(genero));
        return mav;
    }

    @GetMapping(value = "",params = "search")
    public ModelAndView showByAllFields(@RequestParam("search") String search){
        ModelAndView mav = new ModelAndView("books");
        mav.addObject("books",  bookService.findAllFields(search));
        return mav;
    }

    @GetMapping (value = "/{isbn}")
    public ModelAndView shearchById(@PathVariable Long isbn){
        /*
        ModelAndView mav = new ModelAndView("books");
        List<BookVO> books = new ArrayList<>();
        books.add(bookService.findByIsbn(isbn));
        mav.addObject("books",books);
        return mav;*/
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
        mav.addObject("authors",  authorService.findAllAuthors());
        mav.addObject("editorials",editorialService.findAllEditorials());
        mav.addObject("action", "save");
        return mav;
    }

    @GetMapping(value = "/update/{isbn}")
    public ModelAndView updateBook(@PathVariable Long isbn){
        ModelAndView mav = new ModelAndView("formBook");

        mav.addObject("book",bookService.findByIsbn(isbn));
        mav.addObject("authors", authorService.findAllAuthors());
        mav.addObject("editorials",editorialService.findAllEditorials());
        mav.addObject("action", "saveModifications");
        return mav;
    }

    @PostMapping(value = "/save")
    public RedirectView saveBook(@RequestParam Long isbn,
                                 @RequestParam String title,@RequestParam Integer year,
                                 @RequestParam("genero") String genre,@RequestParam("autor") String author,
                                 @RequestParam("editorial") String editorial,
                                 @RequestParam Integer copy,@RequestParam Integer loanedCopy,
                                 @RequestParam String otherAuthor, @RequestParam String otherEditorial){
        if(author.equals("otro")){
            authorService.createAuthor(otherAuthor);
        }
        if(editorial.equals("otro")){
            editorialService.createEditorial(otherEditorial);
        }
        bookService.create(isbn,title,year,genre,author,editorial,copy,loanedCopy,otherAuthor,otherEditorial);
        return new RedirectView("/books/all");
    }

    @PostMapping(value = "/saveModifications")
    public RedirectView saveModificationsBook(@RequestParam Long isbn,
                                              @RequestParam String title, @RequestParam Integer year,
                                              @RequestParam("genero") String genre, @RequestParam("autor") String author,
                                              @RequestParam("editorial") String editorial,
                                              @RequestParam Integer copy, @RequestParam Integer loanedCopy,
                                              @RequestParam String otherAuthor, @RequestParam String otherEditorial){
        if(author.equals("otro")){
            authorService.createAuthor(otherAuthor);
        }
        if(editorial.equals("otro")){
            editorialService.createEditorial(otherEditorial);
        }
        bookService.update(isbn,title,year,genre,author,editorial,copy,loanedCopy,otherAuthor,otherEditorial);
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
}
