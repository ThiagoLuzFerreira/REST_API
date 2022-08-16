package com.thiago.apirestheroku.resources;

import com.thiago.apirestheroku.entities.User;
import com.thiago.apirestheroku.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<User>> findAll(){
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

     @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){
        obj = userService.insert(obj);
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri())
                .body(obj);
     }

     @DeleteMapping("/{id}")
     public ResponseEntity<Void> delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
     }

     @PutMapping("/{id}")
     public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj){
        return ResponseEntity.ok().body(userService.update(id, obj));
     }
}
