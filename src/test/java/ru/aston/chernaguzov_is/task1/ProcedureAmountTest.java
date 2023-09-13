package ru.aston.chernaguzov_is.task1;



import org.junit.Test;
import ru.aston.chernaguzov_is.task1.exceptions.CustomException;

import java.math.BigDecimal;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProcedureAmountTest {

    @Test
    public void setAmount() throws CustomException {
        ProcedureAmount procedureAmount = new ProcedureAmount();

        procedureAmount.setAmount(new BigDecimal("2000"), new BigDecimal("6000"), new BigDecimal("1000"), new BigDecimal("3000"));
        assertEquals(new BigDecimal("2000"), procedureAmount.getAmountTherapy());
    }


    @Test(expected = CustomException.class)
    public void setAmount_exceptionAmountValue() throws CustomException {
        ProcedureAmount procedureAmount = new ProcedureAmount();

        procedureAmount.setAmount(new BigDecimal("-1"), new BigDecimal("6000"), new BigDecimal("1000"), new BigDecimal("3000"));

    }

}