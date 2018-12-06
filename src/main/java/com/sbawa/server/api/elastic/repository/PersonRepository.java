package com.sbawa.server.api.elastic.repository;


import com.sbawa.server.api.elastic.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author simar bawa
 */
@Component
public interface PersonRepository extends JpaRepository<Person, Integer>{
}
