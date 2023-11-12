package tech.reliab.course.morozovda.bank.entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Person {
    private static int currentId;
    protected int id;
    protected String name;
    protected LocalDate birthdDate;

    private void initId() {
        id = currentId++;
    }

    public Person() {
        initId();
        initWithDefaults();
    }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.birthdDate = person.birthdDate;
    }

    public Person(String name, LocalDate birthDate) {
        initId();
        initWithDefaults();
        this.name = name;
        this.birthdDate = birthDate;
    }

    public Person(int id, String name, LocalDate birthdDate) {
        this.id = id;
        this.name = name;
        this.birthdDate = birthdDate;
    }

    @Override
    public String toString() {
        return "Person:{" +
                "\n id='" + getId() + "'" +
                ",\n name='" + getName() + "'" +
                ",\n birthdDate='" + getBirthdDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + "'" +
                "\n}";
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
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
        name = "No name";
        birthdDate = null;
    }

}
