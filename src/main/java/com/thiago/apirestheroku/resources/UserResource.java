package com.thiago.apirestheroku.resources;

import com.thiago.apirestheroku.dtos.UserDTO;
import com.thiago.apirestheroku.entities.User;
import com.thiago.apirestheroku.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserResource {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll(){
        List<User> list = userService.findAll();
        List<UserDTO> dtoList = list.stream().map(user -> new UserDTO(user)).collect(Collectors.toList());;
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long id){
        User user = userService.findById(id);
        return ResponseEntity.ok().body(new UserDTO(user));
    }

     @PostMapping
    public ResponseEntity<User> insert(@RequestBody UserDTO objDto){
        User obj = new User(objDto.getId(), objDto.getName(), objDto.getEmail(), objDto.getPhone(), null);
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
     public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserDTO objDto){
        User obj = new User(null, objDto.getName(), objDto.getEmail(), objDto.getPhone(), null);
        obj.setId(id);
        obj = userService.update(id, obj);
        return ResponseEntity.ok().body(obj);
     }
}
