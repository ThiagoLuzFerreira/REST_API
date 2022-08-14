package com.thiago.apirestheroku.resources;

import com.thiago.apirestheroku.entities.Order;
import com.thiago.apirestheroku.services.OrderService;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/orders")
public class OrderResource {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") Long id){
        try {
            Order order = orderService.findById(id);
            return ResponseEntity.ok().body(order);
        }
        catch (NoSuchElementException e){
            return ResponseEntity.noContent().build();
        }
    }
}
