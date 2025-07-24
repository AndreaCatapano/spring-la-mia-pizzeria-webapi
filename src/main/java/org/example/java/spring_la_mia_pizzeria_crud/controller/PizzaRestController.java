package org.example.java.spring_la_mia_pizzeria_crud.controller;

import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.model.Pizza;
import org.example.java.spring_la_mia_pizzeria_crud.service.PizzaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/pizzas")
public class PizzaRestController {

    @Autowired
    private PizzaService pizzaService;

    @GetMapping
    public List<Pizza> index() {
        return pizzaService.findAll();
    }

    @GetMapping("/{id}")
    public Pizza get(@PathVariable Integer id) {
        return pizzaService.findById(id);
    }

    @PostMapping
    public Pizza post(@RequestBody Pizza pizza) {
        return pizzaService.create(pizza);
    }

    @PutMapping("/{id}")
    public Pizza put(@PathVariable Integer id, @RequestBody Pizza pizza) {
        Pizza pizzaAttempt = pizzaService.findById(id);
        return pizzaService.update(pizzaAttempt);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        Pizza pizzaAttempt = pizzaService.findById(id);

        pizzaService.delete(pizzaAttempt);
    }

}
