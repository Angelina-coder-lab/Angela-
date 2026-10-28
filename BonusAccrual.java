package ru.payroll;

public final class BonusAccrual extends Accrual {

    private final long amountKopecks;

    public BonusAccrual(long amountKopecks) {
        super("BONUS", "Премия", true);
        if (amountKopecks < 0) {
            throw new IllegalArgumentException("bonus must be ≥ 0: " + amountKopecks);
        }
        this.amountKopecks = amountKopecks;
    }

    @Override
    public long calculate(PayrollContext ctx) {
        return amountKopecks;
    }
}
