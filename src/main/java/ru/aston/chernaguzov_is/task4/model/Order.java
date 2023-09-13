package ru.aston.chernaguzov_is.task4.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class Order {
    @JsonIgnore
    private Long id;
    private String item;

    public Order(){}

    public Order(Long id, String item) {
        this.id = id;
        this.item = item;
    }

    public Order(String item) {
        this.item = item;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id) && item.equals(order.item);
    }

    @Override
    public int hashCode() {
        int result = id == null ? 0 : id.hashCode();
        result = 31 * result + (item == null ? 0 : item.hashCode());

        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", item='" + item + '\'' +
                '}';
    }
}
