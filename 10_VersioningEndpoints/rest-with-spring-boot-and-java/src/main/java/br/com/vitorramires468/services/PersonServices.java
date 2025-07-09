package br.com.vitorramires468.services;

import br.com.vitorramires468.data.dto.v1.PersonDTO;
import br.com.vitorramires468.data.dto.v2.PersonDTOV2;
import br.com.vitorramires468.exceptions.ResourceNotFoundException;
import br.com.vitorramires468.mapper.custom.PersonMapper;
import br.com.vitorramires468.model.Person;
import br.com.vitorramires468.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import static br.com.vitorramires468.mapper.ObjectMapper.*;

@Service
public class PersonServices {

    private final AtomicLong counter = new AtomicLong();

    private Logger logger = LoggerFactory.getLogger(PersonServices.class.getName());

    @Autowired
    private PersonRepository repository;

    @Autowired
    private PersonMapper mapper;

    public List<PersonDTO> findAll(){
        logger.info("Find all people!");
        List<Person> personList = repository.findAll();
        return parseListObjects(personList, PersonDTO.class);
    }

    public PersonDTO findById(Long id){
        logger.info("Finding one Person!");

        var entity =  repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        return parseObject(entity, PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        logger.info("Creating one Person!");
        var entity = parseObject(person, Person.class);
        return parseObject(repository.save(entity), PersonDTO.class);
    }

    public PersonDTOV2 createV2(PersonDTOV2 person) {
        logger.info("Creating one Person V2!");
        var entity = mapper.convertDTOToEntity(person);
        return mapper.convertEntityToDTO(repository.save(entity));
    }

    public PersonDTO update(PersonDTO person) {
        logger.info("Updating one person");

        Person entiity = repository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        entiity.setFirstName(person.getFirstName());
        entiity.setLastName(person.getLastName());
        entiity.setAddress(person.getAddress());
        entiity.setGender(person.getGender());

        return parseObject(repository.save(entiity), PersonDTO.class);
    }

    public void delete(Long id){
        logger.info("Deleting one Person!");

        Person entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No records found for ths id"));

        repository.delete(entity);
    }

}
