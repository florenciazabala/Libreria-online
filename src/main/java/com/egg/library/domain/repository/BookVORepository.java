package com.egg.library.domain.repository;


import com.egg.library.domain.BookVO;
import com.egg.library.util.Genre;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


public interface BookVORepository {
    BookVO createBook(BookVO bookVO);
    void updateBook(BookVO bookVO);
    Optional<BookVO> getByIsbn(Long isbn);
    List<BookVO> getAll();
    List<BookVO> getByTitle(String title);
    List<BookVO> getByAllFields(String search);
    List<BookVO> getByGenre(Genre genre);
    List<BookVO> getByAuthor(Integer authorId);
    List<BookVO> getByEditorial(Integer editorialId);
    Optional<BookVO> getByTitleAndAuthor(String title,Integer authorId);
    List<BookVO> getDismissBooks();
    List<BookVO> getAvaibleBooks();
    BigDecimal getPercentGenre(int genre);
    boolean existsByIsbn(Long isbn);
}
