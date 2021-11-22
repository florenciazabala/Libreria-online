package com.egg.library.domain.service;

import com.egg.library.mail.MailNotificationService;
import com.egg.library.domain.BookVO;
import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;
import com.egg.library.domain.repository.LoanVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.FieldInvalidException;
import com.egg.library.exeptions.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanVORepository loanVORepository;

    @Autowired
    private LoanVO loanVO;

    @Autowired
    private MailNotificationService mailNotificationService;

    @Transactional(readOnly = true)
    public List<LoanVO>  findAllLoans(){
        return loanVORepository.getAllLoans();
    }

    @Transactional(readOnly = true)
    public LoanVO findById(Integer id){
        return  loanVORepository.getById(id)
                .orElseThrow(()-> new NoSuchElementException("The loan with id '"+id+"' doesn't exists"));
    }

    @Transactional(readOnly = true)
    public List<LoanVO>  findLoansExpired(){
        return loanVORepository.getLoansByReturnDateExpired(LocalDate.now());
    }

    @Transactional(readOnly = true)
    public List<LoanVO>  findLoansByReturnDate(LocalDate fromRetunDate,LocalDate toReturnDate){
        if(toReturnDate == null){
            toReturnDate= fromRetunDate;
        }
        return loanVORepository.getLoansByReturnDate(fromRetunDate,toReturnDate);
    }

    @Transactional(readOnly = true)
    public List<LoanVO>  findLoansByCustomer(Integer id){
        return loanVORepository.getLoansByCustomer(id);
    }

    @Transactional(readOnly = true)
    public List<LoanVO> findLoansByBook(Long isbn){
        return loanVORepository.getLoansByBook(isbn);
    }

    @Transactional
    public void create(LocalDate loanDate, LocalDate returnDate, BookVO bookVO, CustomerVO customerVO){
        if(loanVORepository.getByBookAndCustomer(bookVO.getIsbn(),customerVO.getId()).isPresent()){
            throw new FieldAlreadyExistException("The client '"+customerVO.getName()+" "+customerVO.getLastName()
                    +"' already have a loan for the book '"+bookVO.getTitle()+"'");
        }
        if(bookVO.getAvaibleCopy()<1) {
            throw new FieldInvalidException("No copies of the book '" + bookVO.getTitle() + "' are available for load");
        }
        setDates(loanDate,returnDate,bookVO,customerVO);
        loanVO = loanVORepository.createLoan(loanVO);
        mailNotificationService.sendMail("Nuevo préstamo registrado",loanVO,loanVO.getCustomer().getUser().getMail());
    }

    @Transactional
    public void update(Integer id,LocalDate loanDate,LocalDate returnDate, BookVO bookVO, CustomerVO customerVO){
        loanVO = loanVORepository.getById(id)
                .orElseThrow(() -> new NoSuchElementException("The loan with id'"+id+"' doesnt't exists"));
        if(loanVO.getBook().getIsbn() != bookVO.getIsbn() && loanVORepository.getByBookAndCustomer(bookVO.getIsbn(),customerVO.getId()).isPresent()){
            throw new FieldAlreadyExistException("The client '"+customerVO.getName()+" "+customerVO.getLastName()
                    +"' already have a loan for the book '"+bookVO.getTitle()+"'");
        }
        if(loanVO.getBook().getIsbn() != bookVO.getIsbn() && bookVO.getAvaibleCopy()<1) {
            throw new FieldInvalidException("No copies of the book '" + bookVO.getTitle() + "' are available for load");
        }
        setDates(loanDate,returnDate,bookVO,customerVO);
        loanVORepository.updateLoan(loanVO);
    }
    private final Boolean DISCHARGED = Boolean.TRUE;
    public void setDates(LocalDate loanDate,LocalDate returnDate, BookVO bookVO, CustomerVO customerVO){

        loanVO.setLoanDate(loanDate);
        loanVO.setReturnDate(returnDate);
        loanVO.setBook(bookVO);
        loanVO.setCustomer(customerVO);
        loanVO.setDischarged(DISCHARGED);
    }

    @Transactional
    public void delete(Integer id){
        loanVO = findById(id);
        loanVO.setDischarged(!DISCHARGED);
        loanVORepository.updateLoan(loanVO);
    }

    @Transactional
    public void discharged(Integer id){
        loanVO = findById(id);
        loanVO.setDischarged(DISCHARGED);
        loanVORepository.updateLoan(loanVO);
    }
}
