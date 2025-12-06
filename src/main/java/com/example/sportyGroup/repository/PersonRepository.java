package com.example.sportyGroup.repository;

import com.example.sportyGroup.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.UUID;

@RepositoryRestResource
public interface PersonRepository extends JpaRepository<Person, UUID> {
}
