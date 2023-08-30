package ru.aston.chernaguzov_is;

import ru.aston.chernaguzov_is.task1.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

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

        System.out.println(resultList);
        System.out.println(order1.getProcedure());
        System.out.println(order1.getProcedureAmount());
        System.out.println(order1.getDiscount());
        System.out.println(order3.getProcedure());
        System.out.println(order3.getProcedureAmount());
        System.out.println(order3.getDiscount());
        System.out.println(order2.getProcedure());
        System.out.println(order2.getProcedureAmount());
        System.out.println(order2.getDiscount());

        DailyOrderList d = new DailyOrderList(resultList);

        BigDecimal resultAmount = d.calcDailyAmount();
        List<Order> sortedList = d.getSortedList();

        System.out.println( "Hello World!" );

        System.out.println(resultAmount);
        System.out.println(sortedList);
    }
}
