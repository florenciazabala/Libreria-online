package com.egg.library.web.controller;

import com.egg.library.domain.BookVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private BookService bookService;

    @GetMapping("/all")
    public ModelAndView showAllLoans(){
        ModelAndView mav = new ModelAndView("loans");
        mav.addObject("loans",loanService.findAllLoans());
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView searchById(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("loans");
        List<LoanVO> loans = new ArrayList<>();
        loans.add(loanService.findById(id));
        mav.addObject("loans",loans);
        return mav;
    }

    @GetMapping(value = "/expired")
    public ModelAndView searchLoansExpired(){
        ModelAndView mav = new ModelAndView("loans");
        mav.addObject("loans",loanService.findLoansExpired());
        return mav;
    }

    @GetMapping(value = "",params = {"fromDate","toDate"})
    public ModelAndView searchByReturnDate(@RequestParam LocalDate fromDate,@RequestParam(required = false) LocalDate toDate){
        ModelAndView mav = new ModelAndView("loans");
        mav.addObject("loans",loanService.findLoansByReturnDate(fromDate,toDate));
        return mav;
    }
    protected List<BookVO> getAvaibleBooks(){
        return  bookService.findAllBooks().stream().filter(
                book -> book.getAvaibleCopy()>0
        ).collect(Collectors.toList());
    }

    @GetMapping("/create")
    public ModelAndView create(){
        ModelAndView mav = new ModelAndView("formLoan");
        mav.addObject("loan", new LoanVO());
        mav.addObject("customers",customerService.findAllCustomers());
        mav.addObject("books",getAvaibleBooks());
        mav.addObject("title","Crear préstamo");
        mav.addObject("action","save");
        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView update(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView("formLoan");
        LoanVO loan = loanService.findById(id);
        mav.addObject("loan",loan);
        mav.addObject("customers",customerService.findAllCustomers());
        List<BookVO> books = getAvaibleBooks();
        if(!books.contains(loan.getBook())){
            books.add(bookService.findByIsbn(loan.getBook().getIsbn()));
        }
        mav.addObject("books",books);
        mav.addObject("title","Modificar préstamo");
        mav.addObject("action","saveModifications");
        return mav;
    }

    @PostMapping("/save")
    public RedirectView save(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate loanDate,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate,
                             @RequestParam("book") Long book, @RequestParam("customer") Integer customer){
        loanService.create(loanDate,returnDate,bookService.findByIsbn(book),customerService.findBId(customer));
        BookVO bookVO = bookService.findByIsbn(book);
        bookService.reserveOneCopy(bookVO);
        return new RedirectView("/loans/all");
    }

    @PostMapping("/saveModifications")
    public RedirectView saveModifications(@RequestParam Integer id,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate loanDate,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate,
                                          @RequestParam("book") Long book, @RequestParam("customer") Integer customer){
        loanService.update(id,loanDate,returnDate,bookService.findByIsbn(book),customerService.findBId(customer));
        if(loanService.findById(id).getBook().getIsbn() != book){
            BookVO bookVO = bookService.findByIsbn(book);
            bookService.reserveOneCopy(bookVO);
        }
        return new RedirectView("/loans/all");
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Integer id){
        loanService.delete(id);
        BookVO bookVO = loanService.findById(id).getBook();
        bookService.enableOneCopy(bookVO);
        return new RedirectView("/loans/all");
    }

    @GetMapping("/discharge/{id}")
    public RedirectView discharge(@PathVariable Integer id){
        loanService.discharged(id);
        BookVO bookVO = loanService.findById(id).getBook();
        bookService.reserveOneCopy(bookVO);
        return new RedirectView("/loans/all");
    }
}
