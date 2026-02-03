package org.example.model;

public class Doctor {
    private int id;
    private String name;
    private String surname;
    private String specialization;
    private int age;

    public Doctor() {
    }

    public Doctor(int id, String name, String surname, String specialization, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.age = age;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}

