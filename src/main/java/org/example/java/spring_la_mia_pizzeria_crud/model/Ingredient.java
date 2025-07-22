package org.example.java.spring_la_mia_pizzeria_crud.model;

import java.io.Serializable;
import java.util.List;

import org.example.java.spring_la_mia_pizzeria_crud.repo.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ingredients")
public class Ingredient implements Serializable {

    public Ingredient() {
    };

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Column(name = "nome", nullable = false)
    private String name;

    @NotBlank
    @Column(name = "provienienza", nullable = false)
    private String provenance;

    @ManyToMany(mappedBy = "pizzas")
    private List<Pizza> pizzas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
}
