package com.example.facultative.db.dto;

import java.util.Objects;

public class UserDTO {
    private String firstName;
    private String lastName;
    private String password;
    private String login;

    public UserDTO(String firstName, String lastName, String password, String login) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.login = login;
    }
    public UserDTO() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDto = (UserDTO) o;
        return Objects.equals(firstName, userDto.firstName) &&
                Objects.equals(lastName, userDto.lastName) &&
                Objects.equals(password, userDto.password) &&
                Objects.equals(login, userDto.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, password, login);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
