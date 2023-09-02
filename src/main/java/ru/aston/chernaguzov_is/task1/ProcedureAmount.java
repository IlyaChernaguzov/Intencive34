package ru.aston.chernaguzov_is.task1;

import ru.aston.chernaguzov_is.task1.exceptions.CodException;
import ru.aston.chernaguzov_is.task1.exceptions.CustomException;

import java.math.BigDecimal;

public class ProcedureAmount {
    private BigDecimal amountTherapy;
    private BigDecimal amountCastration;
    private BigDecimal amountConsultation;
    private BigDecimal amountParasites;

    public BigDecimal getAmountTherapy() {
        return amountTherapy;
    }

    public BigDecimal getAmountCastration() {
        return amountCastration;
    }

    public BigDecimal getAmountConsultation() {
        return amountConsultation;
    }

    public BigDecimal getAmountParasites() {
        return amountParasites;
    }

    public void setAmount (BigDecimal amountTherapy, BigDecimal amountCastration, BigDecimal amountConsultation, BigDecimal amountParasites) throws CustomException {
        if (amountTherapy.compareTo(BigDecimal.ZERO) <= 0 || amountConsultation.compareTo(BigDecimal.ZERO) <= 0 ||
                amountCastration.compareTo(BigDecimal.ZERO) <= 0 || amountParasites.compareTo(BigDecimal.ZERO) <= 0){
            throw new CustomException("Стоимость процедур не может быть меньше либо ровно 0", CodException.BAD_REQUEST);
        }
        this.amountTherapy = amountTherapy;
        this.amountCastration = amountCastration;
        this.amountConsultation = amountConsultation;
        this.amountParasites = amountParasites;

    }
}
