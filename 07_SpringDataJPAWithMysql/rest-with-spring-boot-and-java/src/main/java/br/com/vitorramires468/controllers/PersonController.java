package br.com.vitorramires468.controllers;

import br.com.vitorramires468.model.Person;
import br.com.vitorramires468.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping
    public List<Person> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public Person findById(@PathVariable Long id){
        return services.findById(id);
    }

    @PostMapping
    public Person create(@RequestBody Person person){
        return services.create(person);
    }

    @PutMapping
    public Person update(@RequestBody Person person){
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        services.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
