package com.thiago.apirestheroku.services;

import com.thiago.apirestheroku.entities.User;
import com.thiago.apirestheroku.repositories.UserRepository;
import com.thiago.apirestheroku.services.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    public static final int INDEX = 0;
    public static final long ID = 1L;
    public static final String NAME = "Thiago";
    public static final String EMAIL = "thiago@email.com";
    public static final String PHONE = "999887766";
    public static final String PASSWORD = "123";
    public static final String RESOURCE_NOT_FOUND = "Resource not found. Id ";
    private User user;

    private Optional<User> optionalUser;

    @InjectMocks
    private UserService service;

    @Mock
    private UserRepository repository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindAllThenReturnsSuccess() {
        when(repository.findAll()).thenReturn(List.of(user));
        List<User> response = service.findAll();

        assertNotNull(response);
        assertEquals(1, response.size());
        assertEquals(User.class, response.get(INDEX).getClass());

        assertEquals(ID, response.get(INDEX).getId());
        assertEquals(NAME, response.get(INDEX).getName());
        assertEquals(EMAIL, response.get(INDEX).getEmail());
        assertEquals(PHONE, response.get(INDEX).getPhone());
        assertEquals(PASSWORD, response.get(INDEX).getPassword());

    }

    @Test
    void whenFindByIdThenReturnsSucess() {
        when(repository.findById(anyLong())).thenReturn(optionalUser);
        User response = service.findById(ID);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PHONE, response.getPhone());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenFindByIdThenReturnsResourceNotFoundException() {
        when(repository.findById(ID)).thenThrow(new ResourceNotFoundException(ID));
        try{
            service.findById(ID);
        }
        catch (Exception e){
            assertEquals(ResourceNotFoundException.class, e.getClass());
            assertEquals(RESOURCE_NOT_FOUND + ID, e.getMessage());
        }
    }

    @Test
    void whenInsertThenReturnsSucess() {
        when(repository.save(any())).thenReturn(user);
        User response = service.insert(user);

        assertNotNull(response);
        assertEquals(User.class, response.getClass());

        assertEquals(ID, response.getId());
        assertEquals(NAME, response.getName());
        assertEquals(EMAIL, response.getEmail());
        assertEquals(PHONE, response.getPhone());
        assertEquals(PASSWORD, response.getPassword());
    }

    @Test
    void whenDeleteThenReturnsSuccess() {
        doNothing().when(repository).deleteById(anyLong());
        service.delete(ID);
        verify(repository, times(1)).deleteById(anyLong());
    }

    @Test
    void update() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PHONE, PASSWORD);
        optionalUser = Optional.of(new User(ID, NAME, EMAIL, PHONE, PASSWORD));
    }
}