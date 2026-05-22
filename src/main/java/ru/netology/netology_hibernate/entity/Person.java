package ru.netology.netology_hibernate.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "PERSONS")
public class Person {

    @EmbeddedId
    private PersonId id;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "city_of_living")
    private String cityOfLiving;

    public Person() {
    }

    public Person(PersonId id, String phoneNumber, String cityOfLiving) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.cityOfLiving = cityOfLiving;
    }

    public PersonId getId() {
        return id;
    }

    public void setId(PersonId id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCityOfLiving() {
        return cityOfLiving;
    }

    public void setCityOfLiving(String cityOfLiving) {
        this.cityOfLiving = cityOfLiving;
    }

    public String getName() {
        return id != null ? id.getName() : null;
    }

    public String getSurname() {
        return id != null ? id.getSurname() : null;
    }

    public int getAge() {
        return id != null ? id.getAge() : 0;
    }
}