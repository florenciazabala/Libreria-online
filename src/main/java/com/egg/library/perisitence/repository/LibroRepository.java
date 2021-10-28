package com.egg.library.perisitence.repository;

import com.egg.library.domain.BookVO;
import com.egg.library.domain.repository.BookVORepository;
import com.egg.library.perisitence.DAO.LibroDAO;
import com.egg.library.perisitence.entity.Libro;
import com.egg.library.perisitence.mapper.BookMapper;
import com.egg.library.util.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class LibroRepository implements BookVORepository {

    @Autowired
    private LibroDAO libroDAO;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public void createBook(BookVO bookVO) {
        Libro libro = bookMapper.toLibro(bookVO);
        System.out.println("Book: "+bookVO.getTitle());
        System.out.println("Libro: "+libro.getTitulo());
        libroDAO.save(libro);
    }

    @Override
    public Optional<BookVO> getByIsbn(Long isbn) {
        return libroDAO.findById(isbn).map(libro -> bookMapper.toBookVO(libro));
    }

    @Override
    public List<BookVO> getAll() {
        return bookMapper.toBookVO(libroDAO.findAll());
    }

    @Override
    public List<BookVO> getByTitle(String title) {
        return bookMapper.toBookVO(libroDAO.findByTitulo(title));
    }

    @Override
    public List<BookVO> getByAllFields(String search) {
        return bookMapper.toBookVO(libroDAO.findByAllFields(search));
    }

    @Override
    public List<BookVO> getByGenre(Genre genre) {
        return bookMapper.toBookVO(libroDAO.findByGenero(genre));
    }

    @Override
    public List<BookVO> getByAuthor(Integer authorId) {
        return bookMapper.toBookVO(libroDAO.findByAutor(authorId));
    }

    @Override
    public List<BookVO> getByTEditorial(Integer editorialId) {
        return bookMapper.toBookVO(libroDAO.findByEditorial(editorialId));
    }

    @Override
    public List<BookVO> getDismissBooks() {
        return bookMapper.toBookVO(libroDAO.findDismissBooks());
    }

    @Override
    public List<BookVO> getAvaibleBooks() {
        return bookMapper.toBookVO(libroDAO.findAvaiblesBooks());
    }

    @Override
    public void updateBook(BookVO bookVO) {
        Libro libro = bookMapper.toLibro(bookVO);
        libroDAO.update(libro.getTitulo(),libro.getAnio(),libro.getEjemplares(),libro.getEjemplaresPrestados(),
        libro.getEjemplaresRestantes(),libro.getAlta(),libro.getGenero(),libro.getAutor(),libro.getEditorial(),libro.getIsbn());
    }

    @Override
    public BigDecimal getPercentGenre(int genre) {
        return libroDAO.getPorcentajeGenero(genre);
    }

    @Override
    public boolean existsByIsbn(Long isbn) {
        return libroDAO.existsById(isbn);
    }
}
