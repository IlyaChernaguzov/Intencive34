package ru.aston.chernaguzov_is.task1;

import java.math.BigDecimal;

import static ru.aston.chernaguzov_is.task1.Procedure.CONSULTATION;

public abstract class Order implements Discount, Comparable<Order>{
    private Procedure procedure;
    private User user;
    private int id;

    public Order(Procedure procedure, User user, int id) {
        this.procedure = procedure;
        this.user = user;
        this.id = id;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Order{" +
                "procedure=" + procedure +
                ", user=" + user +
                ", id=" + id +
                '}';
    }

    public BigDecimal getProcedureAmount(){
        BigDecimal amount = BigDecimal.ZERO;
        Procedure procedure = getProcedure();

        switch (procedure){
            case THERAPY:
                amount = amount.add(new BigDecimal("2000"));
                break;
            case CASTRATION:
                amount = amount.add(new BigDecimal("6000"));
                break;
            case CONSULTATION:
                amount = amount.add(new BigDecimal("1000"));
                break;
            case PARASITES:
                amount = amount.add(new BigDecimal("3000"));
                break;
        }
        return amount;
    }

    @Override
    public int compareTo(Order o) {
        return this.getUser().getSourName().compareTo(o.getUser().getSourName());
    }

}
