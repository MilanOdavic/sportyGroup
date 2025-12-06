package com.example.sportyGroup.controller;

import com.example.sportyGroup.dto.PersonDto;
import com.example.sportyGroup.service.impl.PersonServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonServiceImpl personService;

    @GetMapping("/getAllPersons")
    @Operation(description = "Get all persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "Persons not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public List<PersonDto> getAllPersons() {
        return personService.getAllPersons();
    }

    @GetMapping("/getPersonById")
    @Operation(description = "Get person by Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "404", description = "Person not found"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getPersonById(@Parameter(description = "Person id") @RequestParam(name="id") UUID id) {
        return personService.getPersonById(id);
    }

    @PostMapping("/updatePerson")
    @Operation(description = "Add person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void updatePerson(@Parameter(description = "Person id") @RequestParam(name="id") UUID id,
                             @RequestBody PersonDto person) {
        personService.updatePerson(id, person);
    }

    @PostMapping("/deletePerson")
    @Operation(description = "Add person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "403", description = "You don't have enough privileges to access this resource"),
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public void deletePerson(@Parameter(description = "Person id") @RequestParam(name="id") UUID id) {
        personService.deletePerson(id);
    }

    @PostMapping("/addPerson")
    @Operation(description = "Add person with initial balance")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK")
    })
    @ResponseStatus(HttpStatus.OK)
    public PersonDto addPerson(@RequestBody PersonDto person) {
        return personService.addPerson(person);
    }
}
