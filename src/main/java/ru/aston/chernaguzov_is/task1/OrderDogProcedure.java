package ru.aston.chernaguzov_is.task1;

import java.math.BigDecimal;

public class OrderDogProcedure extends Order{

    public OrderDogProcedure(Procedure procedure, User user, int id) {
        super(procedure, user, id);
    }

    @Override
    public BigDecimal getDiscount() {
        BigDecimal amountWithDiscount = getProcedureAmount();
        UserStatus status = getUser().getStatus();

        switch (status){
            case NEW:
                amountWithDiscount = amountWithDiscount.multiply(BigDecimal.valueOf(0.9));
                break;
            case ORDINARY:
                amountWithDiscount = amountWithDiscount.multiply(BigDecimal.valueOf(1));
                break;
            case REGULAR:
                amountWithDiscount = amountWithDiscount.multiply(BigDecimal.valueOf(0.75));
                break;
        }
        return amountWithDiscount;
    }
}
