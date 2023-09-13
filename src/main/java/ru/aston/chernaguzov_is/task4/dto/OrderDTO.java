package ru.aston.chernaguzov_is.task4.dto;

public class OrderDTO {
    private Long id;
    private String item;

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
    public String toString() {
        return "OrderDTO{" +
                "id=" + id +
                ", item='" + item + '\'' +
                '}';
    }
}
