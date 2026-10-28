package ru.payroll;

public final class SickAccrual extends Accrual {

    private final long amountKopecks;

    public SickAccrual(long amountKopecks) {
        super("SICK", "Больничный", false);
        if (amountKopecks < 0) {
            throw new IllegalArgumentException("sick must be ≥ 0: " + amountKopecks);
        }
        this.amountKopecks = amountKopecks;
    }

    public long calculate(PayrollContext ctx) {
        return amountKopecks;
    }
}
