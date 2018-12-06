package com.sbawa.server.api.elastic.repository;

import static org.junit.Assert.assertNotNull;

import com.sbawa.server.api.elastic.model.PersonDetails;
import com.sbawa.server.api.elastic.repository.PersonDetailRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

/**
 * @author simar bawa
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonDetailsRepositoryTest {

    @Autowired
    PersonDetailRepository personDetailsRepository;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void tesfindById_ForDataInsertedAtstartup_ReturnsCorrectData() throws Exception {
        Optional<PersonDetails> details = personDetailsRepository.findById("101");
        Assert.assertTrue(details.isPresent());
    }

    @Test
    public void tesfindById_ForDataNotAvailable_ReturnsNoData() throws Exception {
        Optional<PersonDetails> details = personDetailsRepository.findById("100");
        Assert.assertFalse(details.isPresent());
    }

}
