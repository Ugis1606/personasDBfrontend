package org.example;

import com.google.gson.Gson;

import javax.json.JsonObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Optional;

public class PersonService {

    private final static String URL = "http://localhost:8080/api/v1/person/";

    //  GET
    public Optional<PersonData> getPersonData (String email) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client
                .target(URL)
                .path("email/{email}")
                .resolveTemplate("email", email);

        Response response = target.request().get();

        if (response.getStatus() == 200) {
            JsonObject json = target
                    .request(MediaType.APPLICATION_JSON)
                    .get(JsonObject.class);

            response.close();
            return Optional.of(new PersonData(json));
        }

        response.close();
        return Optional.empty();
    }

    //  POST
    public void postPersonData(String name, String email, Integer age) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(URL);

        Person person = new Person();
        person.setName(name);
        person.setEmail(email);
        person.setAge(age);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);

        Entity<String> entity = Entity.json(jsonStr);

        Response response = target.request().post(entity);
        response.close();
    }

    //   PUT
    public void putPersonData (String name, String email, Integer age) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client
                            .target(URL)
                            .path("email/{email}")
                            .resolveTemplate("email", email)
                            .queryParam("name", name)
                            .queryParam("age", age);

        Person person = new Person();
        person.setName(name);
        person.setEmail(email);
        person.setAge(age);

        Gson gson = new Gson();
        String jsonStr = gson.toJson(person);

        Entity<String> entity = Entity.json(jsonStr);

        Response response = target.request().put(entity);
        response.close();
    }

    //  DELETE
    public void deletePersonData(String email) {
        Client client = ClientBuilder.newClient();

        WebTarget target = client
                .target(URL)
                .path("email/{email}")
                .resolveTemplate("email", email);

        target.request(MediaType.APPLICATION_JSON)
                .delete(JsonObject.class);
    }













}
