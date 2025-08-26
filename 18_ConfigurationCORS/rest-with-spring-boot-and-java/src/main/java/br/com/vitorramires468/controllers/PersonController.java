package br.com.vitorramires468.controllers;

import br.com.vitorramires468.controllers.docs.PersonControllerDocs;
import br.com.vitorramires468.data.dto.PersonDTO;
import br.com.vitorramires468.services.PersonServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@CrossOrigin(origins = {"http://localhost:8080"}) - Aqui ele so libera o acesso para o seguinte endpoint mostrado.
@RestController
@RequestMapping("/api/person/v1")
@Tag(name ="", description = "Endpoint for managing people")
public class PersonController implements PersonControllerDocs {

    @Autowired
    private PersonServices services;

    @Override
    @CrossOrigin(origins = {"http://localhost:8080"})
    @GetMapping(value = "/{id}"
            ,produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO findById(@PathVariable Long id){
        return services.findById(id);
    }

    @Override
    @GetMapping()
    public List<PersonDTO> findAll(){
        return services.findAll();
    }

    @Override
    @CrossOrigin(origins = {"http://localhost:8080"})
    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO create(@RequestBody PersonDTO person){
        return services.create(person);
    }

    @Override
    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public PersonDTO update(@RequestBody PersonDTO person){
        return services.update(person);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        services.delete(id);
        return  ResponseEntity.noContent().build();
    }

}
