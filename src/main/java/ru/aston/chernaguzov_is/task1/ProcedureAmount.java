package ru.aston.chernaguzov_is.task1;

import java.math.BigDecimal;

public class ProcedureAmount {
    private BigDecimal amountTherapy;
    private BigDecimal amountCastration;
    private BigDecimal amountConsultation;
    private BigDecimal amountParasites;

    public BigDecimal getAmountTherapy() {
        return amountTherapy;
    }

    public void setAmountTherapy(BigDecimal amountTherapy) {
        this.amountTherapy = amountTherapy;
    }

    public BigDecimal getAmountCastration() {
        return amountCastration;
    }

    public void setAmountCastration(BigDecimal amountCastration) {
        this.amountCastration = amountCastration;
    }

    public BigDecimal getAmountConsultation() {
        return amountConsultation;
    }

    public void setAmountConsultation(BigDecimal amountConsultation) {
        this.amountConsultation = amountConsultation;
    }

    public BigDecimal getAmountParasites() {
        return amountParasites;
    }

    public void setAmountParasites(BigDecimal amountParasites) {
        this.amountParasites = amountParasites;
    }
}
