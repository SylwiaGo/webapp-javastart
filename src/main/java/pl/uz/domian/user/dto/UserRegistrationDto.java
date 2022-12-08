package pl.uz.domian.user.dto;

import pl.uz.domian.user.UserRole;

import java.util.HashSet;
import java.util.Set;

public class UserRegistrationDto {
    private String email;
    private String password;
    private Set<String> roles;
    private String firstName;
    private String lastName;
    private String street;
    private String houseNumber;
    private String City;
    private String zip;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return City;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStreet(String street) {
        this.street = street;
    }


    public void setCity(String city) {
        City = city;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getZip() {
        return zip;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
