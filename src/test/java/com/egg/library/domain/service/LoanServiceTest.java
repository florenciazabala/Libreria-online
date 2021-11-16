package com.egg.library.domain.service;

import com.egg.library.domain.*;
import com.egg.library.domain.repository.LoanVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.exeptions.FieldInvalidException;
import com.egg.library.util.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class LoanServiceTest {

    @Mock
    private LoanVORepository loanVORepository;

    @InjectMocks
    private LoanService loanService;

    List<LoanVO> loans;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        //loans = getLoansList();
    }
/*
    public List<LoanVO> getLoansList(){
        List<LoanVO> loans = new ArrayList<>();
        BookVO book = new BookVO(2342634263426L,"Crónicas marcianas",1950, Genre.CUENTO,3,2,1,true,new AuthorVO(),new EditorialVO(),null);
        CustomerVO customerVO=new CustomerVO(1,39455376L,"Juan","Perez","juanperez@gmail.com","1168148261",true,null,null);
        BookVO book2 = new BookVO(2342634263430L,"Rayuela",1970,Genre.NOVELA,2,2,0,true,new AuthorVO(),new EditorialVO(),null);
        loans.add(new LoanVO(1,LocalDate.parse("12/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                    LocalDate.parse("16/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),true,
                    book,customerVO));
        loans.add(new LoanVO(2,LocalDate.parse("30/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse("05/11/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),true,
                book2,customerVO));
        loans.add(new LoanVO(3,LocalDate.parse("12/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse("16/10/2021", DateTimeFormatter.ofPattern("dd/MM/yyyy")),false,
                book,customerVO));

        return loans;
    }
*/
    @Test
    void create_when_loan_already_exists() {
        LoanVO loan = loans.get(0);
        when(loanVORepository.getByBookAndCustomer(any(Long.class),any(Integer.class))).
                thenReturn(Optional.of(loan));
        Exception exception = assertThrows(FieldAlreadyExistException.class,()->{
            loanService.create(loan.getLoanDate(),loan.getReturnDate(),loan.getBook(),loan.getCustomer());
        });

        String expectedMessage = "The client '"+loan.getCustomer().getName()+" "+loan.getCustomer().getLastName()
                +"' already have a loan for the book '"+loan.getBook().getTitle()+"'";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void create_when_loan_when_arent_copies() {
        LoanVO loan = loans.get(1);
        Exception exception = assertThrows(FieldInvalidException.class,()->{
            loanService.create(loan.getLoanDate(),loan.getReturnDate(),loan.getBook(),loan.getCustomer());
        });

        String expectedMessage = "No copies of the book '" + loan.getBook().getTitle() + "' are available for load";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}