package com.sbawa.server.api.elastic.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;

/**
 * @author simar bawa
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonDetails {

    @Id
    private String id;
    private String address;
    private String ssn;

    public PersonDetails(){
    }

    public PersonDetails( String address, String ssn ){
        this.ssn = ssn;
        this.address = address;

    }


    public PersonDetails( String id, String address, String ssn ){
        this.id = id;
        this.ssn = ssn;
        this.address = address;

    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSsn() {
        return ssn;
    }

    public void setSsn(String ssn) {
        this.ssn = ssn;
    }
}
