package ru.aston.chernaguzov_is.task4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class User {
    @JsonIgnore
    private Long id;
    private String surname;
    private String name;
    private String phone;
    private String email;
    private Long orderId;

    public User(){}

    public User(Long id, String surname, String name, String phone, String email, Long orderId) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.orderId = orderId;
    }

    public User(String surname, String name, String phone, String email, Long orderId) {
        this.surname = surname;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.orderId = orderId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id) && surname.equals(user.surname) && name.equals(user.name) && phone.equals(user.phone) && email.equals(user.email) && orderId.equals(user.orderId);
    }

    @Override
    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result = 29 * result + (surname == null ? 0 : surname.hashCode());
        result = 31 * result + (name == null ? 0 : name.hashCode());
        result = 29 * result + (phone == null ? 0 : phone.hashCode());
        result = 31 * result + (email == null ? 0 : email.hashCode());
        result = 29 * result + (orderId == null ? 0 : orderId.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                ", email='" + email + '\'' +
                ", orderId=" + orderId +
                '}';
    }
}
