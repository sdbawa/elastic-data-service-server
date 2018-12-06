package com.sbawa.server.api.elastic.controller;

import com.sbawa.server.api.elastic.model.PersonDetails;
import com.sbawa.server.api.elastic.repository.PersonDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author simar bawa
 */

@RestController
@RequestMapping("/mongo")
public class MongoController {


    @Autowired
    private PersonDetailRepository mongoRepository;

    @RequestMapping(path = "/repo", method = RequestMethod.GET)
    public Iterable<PersonDetails> findByRepo() throws IOException {
        return mongoRepository.findAll();
    }


    @RequestMapping(value = "/save/{id}", method = RequestMethod.POST)
    public void save(@PathVariable String id) {
        PersonDetails pd = new PersonDetails();
        pd.setId(id);
        pd.setAddress("testAddress");
        pd.setSsn("ssn1");
        mongoRepository.save(pd);
    }
}
