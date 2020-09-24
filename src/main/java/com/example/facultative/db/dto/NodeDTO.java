package com.example.facultative.db.dto;

import java.util.Objects;

public class NodeDTO {
    private String login;
    private String password;

    public NodeDTO(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public NodeDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NodeDTO)) return false;
        NodeDTO nodeDTO = (NodeDTO) o;
        return Objects.equals(login, nodeDTO.login) &&
                Objects.equals(password, nodeDTO.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password);
    }

    @Override
    public String toString() {
        return "NodeDTO{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
