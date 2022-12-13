package org.example;

import javax.json.JsonObject;

public class PersonData {

    private final String name;
    private final String email;
    private final Integer age;

    public PersonData (JsonObject json) {
        this.name = json.getString("name");
        this.email = json.getString("email");
        this.age = json.getJsonNumber("age").intValue();
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getAge() {
        return age;
    }



}
