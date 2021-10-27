package com.egg.library.domain.repository;

import com.egg.library.domain.BookVO;
import com.egg.library.domain.CustomerVO;
import com.egg.library.domain.LoanVO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface LoanVORepository {
    void createLoan(LoanVO loanVO);
    void updateLoan(LoanVO loanVO);
    Optional<LoanVO> getById(Integer id);
    Optional<LoanVO> getByBookAndCustomer(Long isbnBook, Integer idCustomer);
    List<LoanVO> getAllLoans();
    List<LoanVO> getLoansByBook(Long isbnBook);
    List<LoanVO> getLoansByCustomer(Integer idCustomr);
    List<LoanVO> getLoansByLoanDate(LocalDate loanDate);
    List<LoanVO> getLoansByReturnDate(LocalDate fromReturnDate,LocalDate toRetunrDate);
    List<LoanVO> getLoansByReturnDateExpired(LocalDate returnDate);
}
