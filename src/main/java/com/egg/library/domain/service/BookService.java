package com.egg.library.domain.service;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.BookVO;
import com.egg.library.domain.EditorialVO;
import com.egg.library.domain.repository.BookVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import com.egg.library.util.Genre;
import com.egg.library.util.Validations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BookService {

    @Autowired
    private BookVORepository bookVORepository;

    @Autowired
    private BookVO bookVO;


    @Transactional
    public void create(Long isbn,String title,Integer year,String genre, AuthorVO author, EditorialVO editorial,Integer copy, Integer loanedCopy){
        if(bookVORepository.getByIsbn(isbn).isPresent()){
            throw new FieldAlreadyExistException("The book with isbn '"+isbn+"' already exists");
        }

        if(bookVORepository.getByTitleAndAuthor(title,author.getIdAuthor()).isPresent()){
            throw new FieldAlreadyExistException("The book with title '"+title+"' already exists");
        }
        bookVO = new BookVO();
        setDates(bookVO,isbn,title,year,genre,author,editorial,copy,loanedCopy);

        bookVORepository.createBook(bookVO);
        updatePercentGenre();
    }

    @Transactional
    public void update(Long isbn,String title,Integer year,String genre, AuthorVO author, EditorialVO editorial,Integer copy, Integer loanedCopy){
        bookVO = bookVORepository.getByIsbn(isbn)
                .orElseThrow(() -> new NoSuchElementException("The book with isbn '"+isbn+"' doesn't exists"));

        if( bookVORepository.getByTitleAndAuthor(title,author.getIdAuthor()).isPresent() && bookVORepository.getByTitleAndAuthor(title,author.getIdAuthor()).get().getIsbn() != isbn){
            throw new FieldAlreadyExistException("The book with title '"+title+"' already exists");
        }

        setDates(bookVO,isbn,title,year,genre,author,editorial,copy,loanedCopy);
        bookVORepository.updateBook(bookVO);
        updatePercentGenre();
    }

    private final Boolean DISCHARGED = Boolean.TRUE;
    public void setDates(BookVO bookVO,Long isbn, String title,Integer year,String genre, AuthorVO author, EditorialVO editorial,Integer copy, Integer loanedCopy){

        Validations.validISBN(isbn);
        Validations.validYear(year);
        Validations.validCopy(copy,loanedCopy);
        Genre genreEnum = Validations.getGenre(genre);

        Integer avaibleCopy = copy - loanedCopy;

        bookVO.setIsbn(isbn);
        bookVO.setTitle(title);
        bookVO.setYear(year);
        bookVO.setGenre(genreEnum);
        bookVO.setAuthor(author);
        bookVO.setEditorial(editorial);
        bookVO.setCopy(copy);
        bookVO.setLoanedCopy(loanedCopy);
        bookVO.setAvaibleCopy(avaibleCopy);
        bookVO.setDischarged(DISCHARGED);
    }

    @Transactional(readOnly = true)
    public List<BookVO> findAllBooks(){
        return bookVORepository.getAll();
    }

    @Transactional(readOnly = true)
    public List<BookVO> findByGenre(String genre){
            Genre genreEnum = Validations.getGenre(genre);
            return bookVORepository.getByGenre(genreEnum);
    }

    @Transactional(readOnly = true)
    public List<BookVO> findAllFields(String search){
        return bookVORepository.getByAllFields(search);
    }

    @Transactional(readOnly = true)
    public List<BookVO> findByAuthor(Integer authorId){
        return bookVORepository.getByAuthor(authorId);
    }

    @Transactional(readOnly = true)
    public List<BookVO> findByEditorial(Integer authorId){
        return bookVORepository.getByEditorial(authorId);
    }

    @Transactional(readOnly = true)
    public BookVO findByIsbn(Long isbn){
        return bookVORepository.getByIsbn(isbn)
                .orElseThrow(()->new NoSuchElementException("The book with isbn '"+isbn+"' doesn't exists"));
    }

    @Transactional(readOnly = true)
    public List<BookVO> findDismissBooks(){
        return bookVORepository.getDismissBooks();
    }

    @Transactional(readOnly = true)
    public List<BookVO> findAvaibleBooks(){
        return bookVORepository.getAvaibleBooks();
    }

    @Transactional
    public void delete(Long isbn){
        BookVO bookVO = bookVORepository.getByIsbn(isbn)
                .orElseThrow(()->new NoSuchElementException("The book with isbn '"+isbn+"' doesn't exists"));
        bookVO.setDischarged(!DISCHARGED);
        bookVORepository.updateBook(bookVO);
        updatePercentGenre();
    }

    @Transactional
    public void discharge(Long isbn){
        BookVO bookVO = bookVORepository.getByIsbn(isbn)
                .orElseThrow(()->new NoSuchElementException("The book with isbn '"+isbn+"' doesn't exists"));
        bookVO.setDischarged(DISCHARGED);
        bookVORepository.updateBook(bookVO);
        updatePercentGenre();
    }

    @Transactional
    public void reserveOneCopy(BookVO bookVO){
        Integer reserveCopys = bookVO.getLoanedCopy()+1;
        bookVO.setLoanedCopy(reserveCopys);
        bookVO.setAvaibleCopy(bookVO.getCopy()-reserveCopys);
        bookVORepository.updateBook(bookVO);
    }

    @Transactional
    public void enableOneCopy(BookVO bookVO){
        Integer reserveCopys = bookVO.getLoanedCopy()-1;
        bookVO.setLoanedCopy(reserveCopys);
        bookVO.setAvaibleCopy(bookVO.getCopy()-reserveCopys);
        bookVORepository.updateBook(bookVO);
    }

    public void updatePercentGenre(){
        //Arrays.stream(Genre.values()).forEach(g -> g.setPercent(bookVORepository.getPercentGenre(g.ordinal())));
        for(Genre genre : Genre.values()){
            BigDecimal percent = bookVORepository.getPercentGenre(genre.ordinal());
            if(percent == null){
                percent = new BigDecimal(0.0);
            }
            genre.setPercent(percent);
        }
    }

}
