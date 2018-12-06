package com.sbawa.server.api.elastic.mutation;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.sbawa.server.api.elastic.model.Person;
import com.sbawa.server.api.elastic.repository.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * This Mutations for Person class should address data update/creation.
 *
 * @author simar bawa
 */

@Component
public class Mutation implements GraphQLRootResolver {

    private static final Logger logger = LoggerFactory.getLogger(Mutation.class);

    @Autowired
    private PersonRepository personRepository;

    //Each of the public methods below are mapped with IDL schema file
    public Person mutatePersonName(String firstName) {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setId(210);
        p = personRepository.save(p);
        logger.info("mutatePersonName : {}", p);
        return p;
    }

    public Person mutatePersonNameAge(String firstName, Integer age) {
        Person p = new Person();
        p.setFirstName(firstName);
        p.setId(212);
        p.setAge(age);
        p = personRepository.save(p);
        logger.info("mutatePersonNameAge : {}", p);
        return p;
    }

}
