package br.com.vitorramires468.controllers;

import br.com.vitorramires468.data.dto.v1.PersonDTO;
import br.com.vitorramires468.data.dto.v2.PersonDTOV2;
import br.com.vitorramires468.services.PersonServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServices services;

    @GetMapping
    public List<PersonDTO> findAll(){
        return services.findAll();
    }

    @GetMapping(value = "/{id}")
    public PersonDTO findById(@PathVariable Long id){
        return services.findById(id);
    }

    @PostMapping
    public PersonDTO create(@RequestBody PersonDTO person){
        return services.create(person);
    }

    @PostMapping(value = "/v2")
    public PersonDTOV2 create(@RequestBody PersonDTOV2 person){
        return services.createV2(person);
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
