package com.egg.library.domain.service;

import com.egg.library.domain.AuthorVO;
import com.egg.library.domain.repository.AuthorVORepository;
import com.egg.library.exeptions.FieldAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

class AuthorServiceTest {

    @Mock
    private AuthorVORepository authorVORepository;

    @InjectMocks
    private AuthorService authorService;

    List<AuthorVO> authors;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        authors = getAuthorsLists();
    }

    public List<AuthorVO> getAuthorsLists(){
        List<AuthorVO> authors = new ArrayList<>();
        authors.add(new AuthorVO(1,"Cortazar",true));
        authors.add(new AuthorVO(2,"Borges",false));
        authors.add(new AuthorVO(3,"Ray Bradbury",true));
        authors.add(new AuthorVO(4,"Stephen King",true));
        return authors;
    }

    @Test
    void createAuthor() {
        String name = "Cortazar";
        Mockito.when(authorVORepository.existsByName(any(String.class))).thenReturn(true);
        FieldAlreadyExistException myException = null;
        try {
            authorService.createAuthor(name);
        } catch (FieldAlreadyExistException e) {
            myException = e;
        }
        assertEquals(myException.getMessage(), "Conflict Exception (409). Field Already Exist Exception . The author with name '"+name+"' already exists");
        //assertEquals(myException.getClass(), FieldAlreadyExistException.class);
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


    @Test
    void findById_when_author_find_is_null() {
        Optional<AuthorVO> authorVO = Optional.empty();
        Mockito.when(authorVORepository.getById(any(Integer.class))).thenReturn(authorVO);
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
        Mockito.when(authorVORepository.getById(any(Integer.class))).thenReturn(Optional.of(authors.get(2)));
        Exception myException = null;
        assertEquals(authors.get(2), authorService.findById(2));
    }

    @Test
    void findById_when_find_a_list() {
        Mockito.when(authorVORepository.getById(2)).thenReturn(Optional.of(authors.get(2)));
        Exception myException = null;
        assertEquals(authors.get(2), authorService.findById(2));
    }

    @Test
    void delete() {
    }

    @Test
    void discharge() {
    }



}