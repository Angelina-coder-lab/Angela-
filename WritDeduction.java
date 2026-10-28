package ru.payroll;


public final class WritDeduction extends Deduction {

    private final long amountKopecks;


    public WritDeduction(long amountKopecks) {
        super("WRIT", "Исполнительный лист", false);
        if (amountKopecks < 0) {
            throw new IllegalArgumentException("writ must be ≥ 0: " + amountKopecks);
        }
        this.amountKopecks = amountKopecks;
    }

    public long calculate(PayrollContext ctx) {
        return amountKopecks;
    }
}
