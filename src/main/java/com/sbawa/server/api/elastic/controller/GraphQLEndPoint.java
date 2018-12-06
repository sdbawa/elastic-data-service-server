package com.sbawa.server.api.elastic.controller;

import com.coxautodev.graphql.tools.SchemaParser;
import com.google.gson.Gson;
import com.sbawa.server.api.elastic.mutation.Mutation;
import com.sbawa.server.api.elastic.query.Query;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * The entry point for this application.
 * @author simar bawa
 */

@RestController
public class GraphQLEndPoint {

    @Autowired
    private Query query;

    @Autowired
    private Mutation mutation;

    @Autowired
    private ResourceLoader resourceLoader;

    private GraphQL graphQl;

    @PostConstruct
    public void loadSchema() throws IOException {

        /*Be careful with the name of schema file. I needed to end it with qls for some reason*/
        Resource resource = resourceLoader.getResource("classpath:persons.graphqls");

        GraphQLSchema schema = SchemaParser.newParser()
                                .file(resource.getFilename())
                                .resolvers(query, mutation)
                                .build()
                                .makeExecutableSchema();

        this.graphQl = GraphQL.newGraphQL(schema).build();

    }

    /**
     * This end point serves all needs for GraphAPI clients.
     * It is capable of serving any kind of mutations as well as queries.
     *
     * It uses Spring capabilities to serve the requests which acts as a wrapper over
     * graphql in this context.
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/graphql", method = RequestMethod.POST, produces = "text/plain")
    public String executeGraphQL(@RequestBody String request) {

        /* replace \ from input request { getPersonById (id: "\101\") { firstName age } } */
        String temp1 = request.replace('\\', ' ');
        /* fix for request { getPersonById (id: "101 ") { firstName age } } */
        request = temp1.replace(" \"", "\"");

        ExecutionResult result = graphQl.execute(request);

        /*RConvert and return the response as map*/
        Gson gson = new Gson();
        String responseJson = gson.toJson(result.getData(), LinkedHashMap.class);
        return responseJson;
    }

}
