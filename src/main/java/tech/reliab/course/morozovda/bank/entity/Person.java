package main.java.tech.reliab.course.morozovda.bank.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Person {
    protected UUID id;
    protected String name;
    protected LocalDate birthdDate;

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        birthdDate = null;
    }

    public Person() {
        initWithDefaults();
    }

    public Person(String name, LocalDate birthDate) {
        initWithDefaults();
        this.name = name;
        this.birthdDate = birthDate;
    }

    public Person(UUID id, String name, LocalDate birthdDate) {
        this.id = id;
        this.name = name;
        this.birthdDate = birthdDate;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                ", birthdDate='" + getBirthdDate() + "'" +
                "}";
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdDate() {
        return this.birthdDate;
    }

    public void setBirthdDate(LocalDate birthdDate) {
        this.birthdDate = birthdDate;
    }

}