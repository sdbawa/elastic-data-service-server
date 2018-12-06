package com.sbawa.server.api.elastic.controller;

import com.sbawa.server.api.elastic.controller.GraphQLEndPoint;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This test serves as integration tests for the GraphQL API.
 * We should write test cases for all the different APIs we expose over this end point
 *  as listed in schema definiton file
 *
 * @author simar bawa
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphQLEndPointTest {

    @Autowired
    private GraphQLEndPoint graphQLEndPoint;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private String url;

    @Before
    public void setUp() {
        url = "http://localhost:" + port + "/graphql";;
    }

    @Test
    public void contexLoads() throws Exception {
        //smoke test
        Assert.assertNotNull(graphQLEndPoint);
    }


    @Test
    public void graphql_WhenqueryAllPeople_forAllfields_ThenReturnsAllDataFields() throws Exception{
           assertThat(this.restTemplate.postForObject(url, queryAllPeople_ReqStr(), String.class))
                        .contains(queryAllPeople_RespStr());
    }


    @Test
    public void graphql_WhenqueryPersonById_ForAllFields_ThenReturnsAllDataFields() throws Exception{
          assertThat(this.restTemplate.postForObject(url, queryPersonById_ReqStr(), String.class))
                        .contains(queryPersonById_RespStr());
    }


    @Test
    public void graphql_WhenmutatePersonName_ForAllFields_ThenReturnsAllDataFields() throws Exception{
        assertThat(this.restTemplate.postForObject(url, mutatePersonName_ReqStr(), String.class))
                        .contains(mutatePersonName_RespStr());
    }


    // request response for queryAllPeople
    private String queryAllPeople_ReqStr() {
        return "query{\n"
                        + "\tqueryAllPeople{\n"
                        + "\t\tage\n"
                        + "        firstName\n"
                        + "        id\n"
                        + "\t}\n"
                        + "}";
    }
    private String queryAllPeople_RespStr() {
        return "{\"queryAllPeople\":[{\"age\":21,\"firstName\":\"person1\",\"id\":101},{\"age\":21,\"firstName\":\"person2\",\"id\":102}";
        //please dont use closing string.
    }

    //request response for queryPersonById
    private String queryPersonById_ReqStr() {
        return "query{\n"
                        + "\tqueryPersonById(id:\"101\"){\n"
                        + "\t\tage\n"
                        + "        firstName\n"
                        + "        id\n"
                        + "\t}\n"
                        + "}";
    }
    private String queryPersonById_RespStr() {
     return "\"queryPersonById\":{\"age\":21,\"firstName\":\"person1\",\"id\":101}}";
    }

    //request response for mutatePersonName
    private String mutatePersonName_ReqStr() {
        return "mutation{\n" +
                        "\tmutatePersonName(firstName:\"temp1\"){\n"
                        + "\t\tfirstName\n"
                        + "\t\tage\n"
                        + "\t\tid\n"
                        + "\t}\n"
                        + "}";
    }
    private String mutatePersonName_RespStr() {
        return "{\"mutatePersonName\":{\"firstName\":\"temp1\",\"id\":210}}";
    }


}
