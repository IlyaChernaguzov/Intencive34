package ru.aston.chernaguzov_is.task1;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class DailyOrderListTest {


    @Test
    public void calcDailyAmount() {
        User user1 = new User(18, "Ivanov", "Ivan", UserStatus.NEW);
        User user2 = new User(25, "Petrov", "Petr", UserStatus.ORDINARY);
        User user3 = new User(30, "Semenov", "Semen", UserStatus.REGULAR);

        Order order1 = new OrderCatProcedure(Procedure.CONSULTATION, user1, 1);
        Order order2 = new OrderDogProcedure(Procedure.CASTRATION, user2, 2);
        Order order3 = new OrderCatProcedure(Procedure.PARASITES, user3, 3);

        List<Order> resultList = new ArrayList<>();
        resultList.add(order3);
        resultList.add(order2);
        resultList.add(order1);

        DailyOrderList dailyOrderList = new DailyOrderList(resultList);

        assertEquals(BigDecimal.valueOf(8900), dailyOrderList.calcDailyAmount().setScale(0, RoundingMode.HALF_UP));
    }

    @Test
    public void getSortedList() {

        User user1 = new User(18, "Ivanov", "Ivan", UserStatus.NEW);
        User user2 = new User(25, "Petrov", "Petr", UserStatus.ORDINARY);
        User user3 = new User(30, "Semenov", "Semen", UserStatus.REGULAR);

        Order order1 = new OrderCatProcedure(Procedure.CONSULTATION, user1, 1);
        Order order2 = new OrderDogProcedure(Procedure.CASTRATION, user2, 2);
        Order order3 = new OrderCatProcedure(Procedure.PARASITES, user3, 3);

        List<Order> resultList = new ArrayList<>();
        resultList.add(order3);
        resultList.add(order2);
        resultList.add(order1);

        DailyOrderList dailyOrderList = new DailyOrderList(resultList);

        assertEquals("Ivanov", dailyOrderList.getSortedList().get(0).getUser().getSourName());
        assertEquals("Semenov", dailyOrderList.getSortedList().get(2).getUser().getSourName());
        assertEquals(resultList.size(), dailyOrderList.getSortedList().size());
    }
}