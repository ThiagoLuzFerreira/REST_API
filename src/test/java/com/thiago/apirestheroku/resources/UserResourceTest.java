package com.thiago.apirestheroku.resources;

import com.thiago.apirestheroku.dtos.UserDTO;
import com.thiago.apirestheroku.entities.User;
import com.thiago.apirestheroku.services.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserResourceTest {

    public static final int INDEX = 0;
    public static final long ID = 1L;
    public static final String NAME = "Thiago";
    public static final String EMAIL = "thiago@email.com";
    public static final String PHONE = "999887766";
    public static final String PASSWORD = "123";
    User user;

    UserDTO userDTO;

    @InjectMocks
    private UserResource resource;

    @Mock
    private UserService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        startUser();
    }

    @Test
    void whenFindAllThenReturnsSucess() {
        when(service.findAll()).thenReturn(List.of(user));
        ResponseEntity<List<UserDTO>> response = resource.findAll();

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(ArrayList.class, response.getBody().getClass());
        assertEquals(UserDTO.class, response.getBody().get(INDEX).getClass());

        assertEquals(ID, response.getBody().get(INDEX).getId());
        assertEquals(NAME, response.getBody().get(INDEX).getName());
        assertEquals(EMAIL, response.getBody().get(INDEX).getEmail());
        assertEquals(PHONE, response.getBody().get(INDEX).getPhone());
    }

    @Test
    void whenFindByIdThenReturnsSucess() {
        when(service.findById(anyLong())).thenReturn(user);
        ResponseEntity<UserDTO> response = resource.findById(ID);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(UserDTO.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PHONE, response.getBody().getPhone());
    }

    @Test
    void whenInsertThenReturnsSuccess() {
        when(service.insert(any())).thenReturn(user);
        ResponseEntity<User> response = resource.insert(userDTO);

        assertNotNull(response.getHeaders().get("Location"));
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(User.class, response.getBody().getClass());

        assertEquals(ID, response.getBody().getId());
        assertEquals(NAME, response.getBody().getName());
        assertEquals(EMAIL, response.getBody().getEmail());
        assertEquals(PHONE, response.getBody().getPhone());
    }

    @Test
    void delete() {
    }

    @Test
    void update() {
    }

    private void startUser(){
        user = new User(ID, NAME, EMAIL, PHONE, PASSWORD);
        userDTO = new UserDTO (new User(ID, NAME, EMAIL, PHONE, PASSWORD));
    }
}