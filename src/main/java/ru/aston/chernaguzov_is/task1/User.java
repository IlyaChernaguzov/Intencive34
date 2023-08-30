package ru.aston.chernaguzov_is.task1;

import java.util.Objects;

public class User {
    private int age;
    private String sourName;
    private String name;
    private UserStatus status;

    public User(int age, String sourName, String name, UserStatus status) {
        this.age = age;
        this.sourName = sourName;
        this.name = name;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return age == user.age && Objects.equals(sourName, user.sourName) && Objects.equals(name, user.name) && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, sourName, name, status);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSourName() {
        return sourName;
    }

    public void setSourName(String sourName) {
        this.sourName = sourName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "User{" +
                "age=" + age +
                ", sourName='" + sourName + '\'' +
                ", name='" + name + '\'' +
                ", status=" + status +
                '}';
    }
}
