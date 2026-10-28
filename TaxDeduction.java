package ru.payroll;

import java.util.Objects;

public final class TaxDeduction extends Deduction {

    private final RateSchedule rateBp;


    public TaxDeduction(RateSchedule rateBp) {
        super("NDFL", "НДФЛ", true);
        this.rateBp = Objects.requireNonNull(rateBp, "rateBp");
    }

    public long calculate(PayrollContext ctx) {
        long base = ctx.getTaxBase();
        long bp = rateBp.requireOn(ctx.getPeriod().from(), "НДФЛ");
        return Math.round(base * (bp / 10_000.0));
    }
}
