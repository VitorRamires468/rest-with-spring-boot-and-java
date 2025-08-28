package br.com.vitorramires468.services;

import br.com.vitorramires468.controllers.PersonController;
import br.com.vitorramires468.data.dto.PersonDTO;
import br.com.vitorramires468.exceptions.RequiredObjectIsNullException;
import br.com.vitorramires468.exceptions.ResourceNotFoundException;
import br.com.vitorramires468.model.Person;
import br.com.vitorramires468.repository.PersonRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import org.springframework.stereotype.Service;

import java.util.List;

import static br.com.vitorramires468.mapper.ObjectMapper.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class PersonServices {
    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<PersonDTO> findAll(){
        logger.info("Find all people!");
        List<Person> personList = repository.findAll();
        var persons =  parseListObjects(personList, PersonDTO.class);

        persons.forEach(PersonServices::addHateoasLinks);

        return persons;
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        var dto = parseObject(entity, PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO create(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Creating one Person!");
        var entity = parseObject(person, Person.class);
        var dto =  parseObject(repository.save(entity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    public PersonDTO update(PersonDTO person) {

        if(person == null) throw new RequiredObjectIsNullException();

        logger.info("Updating one person");

        Person entiity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        entiity.setFirstName(person.getFirstName());
        entiity.setLastName(person.getLastName());
        entiity.setAddress(person.getAddress());
        entiity.setGender(person.getGender());

        var dto = parseObject(repository.save(entiity), PersonDTO.class);
        addHateoasLinks(dto);
        return dto;
    }

    @Transactional
    public PersonDTO disablePerson(Long id){
        logger.info("Disabling one Person!");

        repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        repository.disablePerson(id);

        var entity = repository.findById(id).get();

        var dto = parseObject(entity, PersonDTO.class);

        addHateoasLinks(dto);

        return dto;
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        repository.delete(entity);
    }

    private static void addHateoasLinks(PersonDTO dto) {
        dto.add(linkTo(methodOn(PersonController.class).findById(dto.getId())).withSelfRel().withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).delete(dto.getId())).withRel("delete").withType("DELETE"));
        dto.add(linkTo(methodOn(PersonController.class).findAll()).withRel("findAll").withType("GET"));
        dto.add(linkTo(methodOn(PersonController.class).create(dto)).withRel("create").withType("POST"));
        dto.add(linkTo(methodOn(PersonController.class).update(dto)).withRel("update").withType("PUT"));
        dto.add(linkTo(methodOn(PersonController.class).disablePerson(dto.getId())).withRel("disable").withType("PATCH"));
    }

}
