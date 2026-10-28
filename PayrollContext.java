package ru.payroll;

import java.util.Objects;


public final class PayrollContext {

    private final Employee employee;
    private final PayrollPeriod period;

    private long accruedTotal;      // сумма всех начислений, коп.
    private long taxBase;           // база для НДФЛ, коп.
    private long deductedTotal;     // сумма всех удержаний, коп.
    private long nonTaxDeducted;    // удержания кроме налога (для лимита доли)


    public PayrollContext(Employee employee, PayrollPeriod period) {
        this.employee = Objects.requireNonNull(employee, "employee");
        this.period = Objects.requireNonNull(period, "period");
    }

    public Employee getEmployee() {
        return employee;
    }

    public PayrollPeriod getPeriod() {
        return period;
    }

    public long getAccruedTotal() {
        return accruedTotal;
    }

    public long getTaxBase() {
        return taxBase;
    }

    public long getDeductedTotal() {
        return deductedTotal;
    }

    public long getNonTaxDeducted() {
        return nonTaxDeducted;
    }


    public void addAccrual(long amount, boolean includedInTax) {
        if (amount < 0) {
            throw new IllegalArgumentException("accrual amount must be ≥ 0: " + amount);
        }
        accruedTotal += amount;
        if (includedInTax) {
            taxBase += amount;
        }
    }


    public void addDeduction(long amount, boolean isTax) {
        if (amount < 0) {
            throw new IllegalArgumentException("deduction amount must be ≥ 0: " + amount);
        }
        deductedTotal += amount;
        if (!isTax) {
            nonTaxDeducted += amount;
        }
    }

    public long net() {
        return accruedTotal - deductedTotal;
    }
}
