package ru.aston.chernaguzov_is.task1;

import java.math.BigDecimal;

import static ru.aston.chernaguzov_is.task1.Procedure.CONSULTATION;

public abstract class Order implements Discount, Comparable<Order>{
    private Procedure procedure;

    private ProcedureAmount procedureAmount;
    private User user;
    private int id;

    public Order(Procedure procedure, ProcedureAmount procedureAmount, User user, int id) {
        this.procedure = procedure;
        this.procedureAmount = procedureAmount;
        this.user = user;
        this.id = id;
    }

    public Procedure getProcedure() {
        return procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    public void setProcedureAmount(ProcedureAmount procedureAmount) {
        this.procedureAmount = procedureAmount;
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
                ", procedureAmount=" + procedureAmount +
                ", user=" + user +
                ", id=" + id +
                '}';
    }

    public BigDecimal getProcedureAmount(){
        BigDecimal amount = BigDecimal.ZERO;
        Procedure procedure = getProcedure();

        switch (procedure){
            case THERAPY:
//                amount = amount.add(new BigDecimal("2000"));
                amount = amount.add(procedureAmount.getAmountTherapy());
                break;
            case CASTRATION:
//                amount = amount.add(new BigDecimal("6000"));
                amount = amount.add(procedureAmount.getAmountCastration());
                break;
            case CONSULTATION:
//                amount = amount.add(new BigDecimal("1000"));
                amount = amount.add(procedureAmount.getAmountConsultation());
                break;
            case PARASITES:
//                amount = amount.add(new BigDecimal("3000"));
                amount = amount.add(procedureAmount.getAmountParasites());
                break;
        }
        return amount;
    }

    @Override
    public int compareTo(Order o) {
        return this.getUser().getSourName().compareTo(o.getUser().getSourName());
    }

}
