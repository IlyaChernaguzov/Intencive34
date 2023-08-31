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
        if (age != user.age) return false;
        if (status != user.status) return false;
        if (!Objects.equals(name, user.name)) return false;
        return Objects.equals(sourName, user.sourName);
    }

    @Override
    public int hashCode() {
        int result = name == null ? 0 : name.hashCode();
        result = 31 * result + (sourName == null ? 0 : sourName.hashCode());
        result = 31 * result + (status == null ? 0 : status.hashCode());
        result = 31 * result + age;
        return result;
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
