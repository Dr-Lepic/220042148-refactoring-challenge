package edu.iutcs.cr.persons;

import edu.iutcs.cr.value.EmailAddress;

import java.io.Serializable;
import java.util.Objects;
import java.util.Scanner;

/**
 * @author Raian Rahman
 * @since 4/18/2024
 */
public class Person implements Serializable {

    private String name;
    private String id;
    private EmailAddress email;

    public Person() {
        setName();
        setId();
        setEmail();
    }

    public Person(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        Scanner scanner = new Scanner(System.in);

        while (this.name == null || this.name.isBlank()) {
            System.out.print("Enter name: ");
            this.name = scanner.nextLine();

            if (name == null || name.isBlank()) {
                System.out.println("Name is mandatory!");
            }
        }
    }

    public String getId() {
        return id;
    }

    public void setId() {
        Scanner scanner = new Scanner(System.in);

        while (this.id == null || this.id.isBlank()) {
            System.out.print("Enter id: ");
            this.id = scanner.nextLine();

            if (id == null || id.isBlank()) {
                System.out.println("Id is mandatory!");
            }
        }
    }

    public String getEmail() {
        return email == null ? null : email.getValue();
    }

    public void setEmail() {
        Scanner scanner = new Scanner(System.in);

        while (this.email == null) {
            System.out.print("Enter email: ");
            String input = scanner.nextLine();

            try {
                this.email = new EmailAddress(input);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", email='" + email + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
