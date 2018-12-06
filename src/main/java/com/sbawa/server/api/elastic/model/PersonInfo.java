package com.sbawa.server.api.elastic.model;

/**
 * @author simar bawa
 */
public class PersonInfo {

    private Person person;

    private PersonDetails personDetails;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonDetails getPersonDetails() {
        return personDetails;
    }

    public void setPersonDetails(PersonDetails personDetails) {
        this.personDetails = personDetails;
    }
}
