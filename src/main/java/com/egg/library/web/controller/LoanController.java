package com.egg.library.web.controller;

import com.egg.library.domain.BookVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.service.BookService;
import com.egg.library.domain.service.CustomerService;
import com.egg.library.domain.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public ModelAndView showAllLoans(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("loans");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("success", flashMap.get("success-name"));
            mav.addObject("error", flashMap.get("error-name"));
        }

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
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView create(HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formLoan");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error-name"));
        }

        mav.addObject("loan", new LoanVO());
        mav.addObject("customers",customerService.findAllCustomers());
        mav.addObject("books",getAvaibleBooks());
        mav.addObject("title","Crear préstamo");
        mav.addObject("action","save");
        return mav;
    }

    @GetMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ModelAndView update(@PathVariable Integer id,HttpServletRequest request){
        ModelAndView mav = new ModelAndView("formLoan");

        Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
        if (flashMap != null) {
            mav.addObject("error", flashMap.get("error-name"));
        }

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
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView save(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate loanDate,
                             @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate,
                             @RequestParam("book") Long book, @RequestParam("customer") Integer customer,
                             RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/loans/all");
        try {
            loanService.create(loanDate, returnDate, bookService.findByIsbn(book), customerService.findBId(customer));
            BookVO bookVO = bookService.findByIsbn(book);
            bookService.reserveOneCopy(bookVO);
            attributes.addFlashAttribute("success-name","El préstamo ha sido creado exitosamente");
        }catch (Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/loans/create");
        }
        return redirectView;
    }

    @PostMapping("/saveModifications")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView saveModifications(@RequestParam Integer id,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate loanDate,
                                          @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate returnDate,
                                          @RequestParam("book") Long book, @RequestParam("customer") Integer customer,
                                          RedirectAttributes attributes){

        RedirectView redirectView = new RedirectView("/loans/all");
        try {
            loanService.update(id, loanDate, returnDate, bookService.findByIsbn(book), customerService.findBId(customer));
            if (loanService.findById(id).getBook().getIsbn() != book) {
                BookVO bookVO = bookService.findByIsbn(book);
                bookService.reserveOneCopy(bookVO);
            }
            attributes.addFlashAttribute("success-name","El préstamo ha sido actualizado exitosamente");
        }catch(Exception e){
            attributes.addFlashAttribute("error-name",e.getMessage());
            redirectView.setUrl("/loans/update/"+id);
        }
        return redirectView;
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView delete(@PathVariable Integer id){
        loanService.delete(id);
        BookVO bookVO = loanService.findById(id).getBook();
        bookService.enableOneCopy(bookVO);
        return new RedirectView("/loans/all");
    }

    @GetMapping("/discharge/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public RedirectView discharge(@PathVariable Integer id){
        loanService.discharged(id);
        BookVO bookVO = loanService.findById(id).getBook();
        bookService.reserveOneCopy(bookVO);
        return new RedirectView("/loans/all");
    }
}
