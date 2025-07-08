package br.com.vitorramires468.services;

import br.com.vitorramires468.exceptions.ResourceNotFoundException;
import br.com.vitorramires468.model.Person;
import br.com.vitorramires468.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = Logger.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    public List<Person> findAll(){
        logger.info("Find all people!");
        List<Person> personList = repository.findAll();
        return personList;
    }

    public Person findById(Long id){
        logger.info("Finding one Person!");

        return  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));
    }

    public Person create(Person person) {
        return repository.save(person);
    }

    public Person update(Person person) {
        logger.info("Updating one person");

        Person entiity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        entiity.setFirstName(person.getFirstName());
        entiity.setLastName(person.getLastName());
        entiity.setAddress(person.getAddress());
        entiity.setGender(person.getGender());

        return repository.save(person);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        repository.delete(entity);
    }

}
