package br.com.vitorramires468.controllers;

import br.com.vitorramires468.data.dto.PersonDTO;
import br.com.vitorramires468.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping
    public List<PersonDTO> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable Long id){

        var person = services.findById(id);
        person.setBirthDay(new Date());
        //person.setPhoneNumber("+5592991262262");
        person.setPhoneNumber("");
        person.setLastName(null);
        person.setSensitiveData("Foo Bar");
        return person;
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO person){
        return services.create(person);
    }

    @PutMapping
    public PersonDTO update(@RequestBody PersonDTO person){
        return services.update(person);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        services.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
