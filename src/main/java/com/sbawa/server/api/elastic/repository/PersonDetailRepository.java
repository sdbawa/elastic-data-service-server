package com.sbawa.server.api.elastic.repository;

import com.sbawa.server.api.elastic.model.PersonDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

/**
 * @author simar bawa
 */

@Component
public interface PersonDetailRepository extends MongoRepository<PersonDetails, String> {
}
