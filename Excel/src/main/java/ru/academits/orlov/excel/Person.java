package ru.academits.orlov.excel;

public class Person {
    private final String surname;
    private final String name;
    private final int age;
    private final String phoneNumber;

    public Person(String surname, String name, int age, String phoneNumber) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
    }

    public String getSurname() {
        return surname;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
