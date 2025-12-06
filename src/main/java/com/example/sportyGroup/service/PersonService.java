package com.example.sportyGroup.service;

import com.example.sportyGroup.dto.PersonDto;

import java.util.List;
import java.util.UUID;

public interface PersonService {

    List<PersonDto> getAllPersons();

    PersonDto getPersonById(UUID id);

    void createPerson(PersonDto person);

    void updatePerson(UUID id, PersonDto person);

    void deletePerson(UUID id);

}
