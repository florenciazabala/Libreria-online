package com.egg.library.perisitence.repository;

import com.egg.library.domain.LoanVO;
import com.egg.library.domain.repository.LoanVORepository;
import com.egg.library.perisitence.DAO.PrestamoDAO;
import com.egg.library.perisitence.entity.Prestamo;
import com.egg.library.perisitence.mapper.LoanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class PrestamoRepository implements LoanVORepository {

    @Autowired
    private PrestamoDAO prestamoDAO;

    @Autowired
    private LoanMapper loanMapper;


    @Override
    public LoanVO createLoan(LoanVO loanVO) {
        return loanMapper.toLoanVO(prestamoDAO.save(loanMapper.toPrestamo(loanVO)));
    }

    @Override
    public void updateLoan(LoanVO loanVO) {
        Prestamo prestamo = loanMapper.toPrestamo(loanVO);
        prestamoDAO.update(prestamo.getFechaPrestamo(),prestamo.getFechaDevolucion(),
                prestamo.getAlta(),prestamo.getLibro(),prestamo.getCliente(),prestamo.getId());
    }

    @Override
    public Optional<LoanVO> getById(Integer id) {
        return prestamoDAO.findById(id).map(prestamo -> loanMapper.toLoanVO(prestamo));
    }

    @Override
    public Optional<LoanVO> getByBookAndCustomer(Long isbnBook, Integer idCustomer) {
        return prestamoDAO.findByLibroAndCliente(isbnBook,idCustomer)
                .map(prestamo -> loanMapper.toLoanVO(prestamo));
    }

    @Override
    public List<LoanVO> getAllLoans() {
        return loanMapper.toLoanVO(prestamoDAO.findAll());
    }

    @Override
    public List<LoanVO> getLoansByBook(Long isbnBook) {
        return loanMapper.toLoanVO(prestamoDAO.findByLibro(isbnBook));
    }

    @Override
    public List<LoanVO> getLoansByCustomer(Integer idCoustomer) {
        return loanMapper.toLoanVO(prestamoDAO.findByCliente(idCoustomer));
    }

    @Override
    public List<LoanVO> getLoansByLoanDate(LocalDate loanDate) {
        return loanMapper.toLoanVO(prestamoDAO.findByFechaPrestamo(loanDate));
    }

    @Override
    public List<LoanVO> getLoansByReturnDate(LocalDate fromReturnDate,LocalDate toReturnDate) {
        return loanMapper.toLoanVO(prestamoDAO.findByFechaDevolucion(fromReturnDate,toReturnDate));
    }

    @Override
    public List<LoanVO> getLoansByReturnDateExpired(LocalDate returnDate) {
        return loanMapper.toLoanVO(prestamoDAO.findByFechaDevolucionVencida(returnDate));
    }
}
