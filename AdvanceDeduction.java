package ru.payroll;

public final class AdvanceDeduction extends Deduction {

    private final long amountKopecks;

    public AdvanceDeduction(long amountKopecks) {
        super("ADVANCE", "Аванс", false);
        if (amountKopecks < 0) {
            throw new IllegalArgumentException("advance must be ≥ 0: " + amountKopecks);
        }
        this.amountKopecks = amountKopecks;
    }

    public long calculate(PayrollContext ctx) {
        return amountKopecks;
    }
}
