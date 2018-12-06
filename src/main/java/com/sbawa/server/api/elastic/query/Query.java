package com.sbawa.server.api.elastic.query;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sbawa.server.api.elastic.model.Person;
import com.sbawa.server.api.elastic.model.PersonDetails;
import com.sbawa.server.api.elastic.model.PersonInfo;
import com.sbawa.server.api.elastic.repository.PersonDetailRepository;
import com.sbawa.server.api.elastic.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

/**
 * This Query for Person class should address data fetch operations.
 * @author simar bawa
 */

@Component
public class Query implements GraphQLRootResolver {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDetailRepository personDetailRepository;

    //Each of the public methods below are mapped with IDL schema file
    public List<Person> queryAllPeople() {
        return personRepository.findAll();
    }

    public Person queryPersonById(Integer id ) {
        return personRepository.getOne(id);
    }

    public PersonInfo queryPersonWithDetailsById(String id) {
        PersonInfo perInfo = new PersonInfo();

        Person person = personRepository.getOne(Integer.parseInt(id));

        Optional<PersonDetails> details = personDetailRepository.findById(id);
        PersonDetails pd = details.get();

        perInfo.setPerson(person);
        perInfo.setPersonDetails(pd);

        return perInfo;

    }
}
