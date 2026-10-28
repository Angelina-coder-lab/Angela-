package ru.payroll;


public class DeductionLimitException extends PayrollException {

    private final long nonTaxDeducted;
    private final long accruedTotal;
    private final int limitPercent;


    public DeductionLimitException(long nonTaxDeducted, long accruedTotal, int limitPercent) {
        super(String.format(
                "Удержания %d коп. превышают %d%% от начислений %d коп.",
                nonTaxDeducted, limitPercent, accruedTotal));
        this.nonTaxDeducted = nonTaxDeducted;
        this.accruedTotal = accruedTotal;
        this.limitPercent = limitPercent;
    }

    public long getNonTaxDeducted() {
        return nonTaxDeducted;
    }

    public long getAccruedTotal() {
        return accruedTotal;
    }

    public int getLimitPercent() {
        return limitPercent;
    }
}
