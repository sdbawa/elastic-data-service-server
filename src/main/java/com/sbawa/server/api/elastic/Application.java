package com.sbawa.server.api.elastic;

import com.sbawa.server.api.elastic.repository.PersonDetailRepository;
import com.sbawa.server.api.elastic.model.PersonDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableAutoConfiguration
public class Application {

    @Autowired
    PersonDetailRepository mongoPersonDetailsRepository;

    public static void main(String[] args) {
       ApplicationContext ctx = SpringApplication.run(Application.class, args);
    }

    /** This method initializes mongo db with inital data during boot strapping the application
     */
    @Bean
    Boolean mongoTestDataInitialize() {
        PersonDetails pd = new PersonDetails();
        pd.setId("101");
        pd.setAddress("testAddress");
        pd.setSsn("ssn1");
        mongoPersonDetailsRepository.save(pd);
        pd = new PersonDetails();
        pd.setId("102");
        pd.setAddress("testAddress");
        pd.setSsn("ssn1");
        mongoPersonDetailsRepository.save(pd);
        return true;
    }


}
