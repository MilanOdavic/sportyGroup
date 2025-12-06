package com.example.sportyGroup.service.impl;

import com.example.sportyGroup.dto.PersonDto;
import com.example.sportyGroup.model.Person;
import com.example.sportyGroup.repository.PersonRepository;
import com.example.sportyGroup.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<PersonDto> getAllPersons() {
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> PersonDto.builder()
                        .id(person.getId())
                        .name(person.getName())
                        .age(person.getAge())
                        .balance(person.getBalance())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PersonDto getPersonById(UUID id) {
        Optional<Person> personData = personRepository.findById(id);
        if(!personData.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
        Person person = personData.get();
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .age(person.getAge())
                .balance(person.getBalance())
                .build();
    }

    @Override
    public void createPerson(PersonDto personDto) {

        final Person person = Person.builder()
                .name(personDto.getName())
                .age(personDto.getAge())
                .build();

        personRepository.save(person);
    }

    @Override
    public void updatePerson(UUID id, PersonDto personDto) {
        Optional<Person> personData = personRepository.findById(id);
        if (personData.isPresent()) {
            final Person person = Person.builder()
                .id(id)
                .name(personDto.getName())
                .age(personDto.getAge())
                .build();
            personRepository.save(person);
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found by provided id");
        }
    }

    @Override
    public void deletePerson(UUID id) {
        personRepository.deleteById(id);
    }

    public PersonDto addPerson(PersonDto personDto) {
        Person person = Person.builder()
                .name(personDto.getName())
                .age(personDto.getAge())
                .balance(personDto.getBalance() != null ? personDto.getBalance() : 1000.0)
                .build();
        Person saved = personRepository.save(person);
        return PersonDto.builder()
                .id(saved.getId())
                .name(saved.getName())
                .age(saved.getAge())
                .balance(saved.getBalance())
                .build();
    }
}
