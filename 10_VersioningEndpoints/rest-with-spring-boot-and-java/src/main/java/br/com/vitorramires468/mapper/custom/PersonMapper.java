package br.com.vitorramires468.mapper.custom;

import br.com.vitorramires468.data.dto.v2.PersonDTOV2;
import br.com.vitorramires468.model.Person;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonMapper {

    public PersonDTOV2 convertEntityToDTO(Person person){
        PersonDTOV2 dto = new PersonDTOV2();

        dto.setId(person.getId());
        dto.setAddress(person.getAddress());
        dto.setLastName(person.getLastName());
        dto.setBirthDay(new Date());
        dto.setFirstName(person.getFirstName());
        dto.setGender(person.getGender());

        return dto;
    }

    public Person convertDTOToEntity(PersonDTOV2 dto){
        Person entity = new Person();

        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setLastName(dto.getLastName());
        entity.setFirstName(dto.getFirstName());
        entity.setGender(dto.getGender());

        return entity;
    }
}
