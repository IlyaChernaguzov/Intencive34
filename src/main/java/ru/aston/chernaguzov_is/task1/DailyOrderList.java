package ru.aston.chernaguzov_is.task1;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

public class DailyOrderList implements OrderCalculation{
    private List<Order> orders;

    public DailyOrderList(List<Order> orders) {
        this.orders = orders;
    }

    public List<Order> getOrders() {
        return orders;
    }

    @Override
    public BigDecimal calcDailyAmount() {
        BigDecimal result = BigDecimal.ZERO;

        for (Order order : orders)
            result = result.add(order.getDiscount());

        return result;
    }

    public List<Order> getSortedList(){
        Collections.sort(orders);
        return this.orders;
    }

    @Override
    public String toString() {
        return "DailyOrderList{" +
                "orders=" + orders +
                '}';
    }
}
