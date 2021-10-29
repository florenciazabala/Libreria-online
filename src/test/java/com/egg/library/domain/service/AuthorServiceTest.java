package com.egg.library.domain.service;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.repository.AuthorVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AuthorServiceTest {

    static AuthorVORepository authorVORepository;
    static AuthorService authorService;

    @BeforeAll
    static void setUp() {
        authorVORepository = Mockito.mock(AuthorVORepository.class);
        authorService = new AuthorService(authorVORepository);
    }

    @Test(expected = FieldAlreadyExistException.class)
    void createAuthor() {
        String name ="Cortazar";
        Mockito.when(authorVORepository.existsByName(name)).thenReturn(true);
        FieldAlreadyExistException myException = null;
        try {
            authorService.createAuthor(name);
        } catch (FieldAlreadyExistException e) {
            myException = e;
        }
        assertEquals(myException.getMessage(), "The author with name '"+name+"' already exists");
    }

    @Test
    void updateAuthor() {
    }

    @Test
    void findAllAuthors() {
        Mockito.when(authorVORepository.getAll()).thenReturn(getAuthorsLists());
        authorService.findAllAuthors();
    }

    @Test
    void findByName() {
    }
    /*
    @Test
    void findById() {
        Mockito.when(authorVORepository.getById(3)).thenReturn(new AuthorVO(3,"Ray Bradbury",true);
        authorVORepository.getById(3);
    }*/

    @Test
    void findById_when_author_find_is_null() {
        Optional<AuthorVO> authorVO = Optional.empty();
        Mockito.when(authorVORepository.getById(3)).thenReturn(authorVO);
        Exception myException = null;
        try {
            authorService.findById(3);
        } catch (Exception e) {
            myException = e;
        }
        assertEquals(myException.getMessage(), "The author with id '3' doesn't exists");
    }
    @Test
    void findById_when_author_is_find() {
        Mockito.when(authorVORepository.getById(2)).thenReturn(Optional.of(new AuthorVO(2,"Ray Bradbury",true)));
        Exception myException = null;
        assertEquals(new AuthorVO(2,"Ray Bradbury",true), authorService.findById(2));
    }

    @Test
    void findById_when_find_a_list() {
        Mockito.when(authorVORepository.getById(2)).thenReturn(Optional.of(new AuthorVO(2,"Ray Bradbury",true)));
        Exception myException = null;
        assertEquals(new AuthorVO(2,"Ray Bradbury",true), authorService.findById(2));
    }

    @Test
    void delete() {
    }

    @Test
    void discharge() {
    }

    public List<AuthorVO> getAuthorsLists(){
        List<AuthorVO> authors = new ArrayList<>();
        authors.add(new AuthorVO(1,"Cortazar, julio",true));
        authors.add(new AuthorVO(2,"Borges",false));
        authors.add(new AuthorVO(3,"Ray Bradbury",true));
        authors.add(new AuthorVO(4,"Stephen King",true));
        return authors;
    }

}