package tech.reliab.course.morozovda.bank.entity;

import java.time.LocalDate;
import java.util.UUID;

public class Person {
    protected UUID id;
    protected String name;
    protected LocalDate birthdDate;

    public Person() {
        initWithDefaults();
    }

    public Person(Person person) {
        this.id = UUID.fromString(person.id.toString());
        this.name = person.name;
        this.birthdDate = person.birthdDate;
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
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthdDate='" + getBirthdDate() + "'" +
                "\n}";
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

    private void initWithDefaults() {
        id = UUID.randomUUID();
        name = "No name";
        birthdDate = null;
    }

}
