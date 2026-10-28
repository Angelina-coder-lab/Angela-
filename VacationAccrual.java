package ru.payroll;


public final class VacationAccrual extends Accrual {

    private final long amountKopecks;

    public VacationAccrual(long amountKopecks) {
        super("VACATION", "Отпускные", true);
        if (amountKopecks < 0) {
            throw new IllegalArgumentException("vacation must be ≥ 0: " + amountKopecks);
        }
        this.amountKopecks = amountKopecks;
    }

    public long calculate(PayrollContext ctx) {
        return amountKopecks;
    }
}
