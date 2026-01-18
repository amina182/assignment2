package hospital;

import java.util.Objects;

public abstract class Person{
    private static int counter = 1;

    private int id;
    private String name;
    private String surname;

    public Person (String name, String surname){
        this.id = counter++;
        this.name = name;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public abstract void displayInfo();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + " " + surname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person)) return false;
        Person person = (Person) o;
        return id == person.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
