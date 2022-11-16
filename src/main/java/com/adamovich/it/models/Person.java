package com.adamovich.it.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Comparator;
import java.util.Objects;

public class Person {

    private int id;
    @NotEmpty(message = "Field must not be empty")
    @Size(min = 2, max = 20 , message = "Min character 2, Max character 20")
    private String firstName;
    @NotEmpty(message = "Field must not be empty")
    @Size(min = 2, max = 20 , message = "Min character 2, Max character 20")
    private String lastName;
    @NotEmpty(message = "Field must not be empty")
    @Size(min = 1, max = 20 , message = "Min character 1, Max character 20")
    private String city;

    @Min(value = 0, message = "Min age 0")
    private int age;

    @NotEmpty(message = "Field must not be empty")
    @Size(min = 7, max = 15 , message = "Min character for mobile number - 7")
    private String mobileNumber;
    @Email(message = "Your write not valid email")
    private String email;

    public Person(){}

    public Person(String firstName, String lastName, String city, int age, String mobileNumber, String email, int id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
