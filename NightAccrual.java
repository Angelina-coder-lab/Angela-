package ru.payroll;

import java.util.Objects;


public final class NightAccrual extends Accrual {

    private final RateSchedule percentBp; // базисные пункты
    private final RateSchedule monthlySalary;


    public NightAccrual(RateSchedule percentBp, RateSchedule monthlySalary) {
        super("NIGHT", "Доплата за ночные", true);
        this.percentBp = Objects.requireNonNull(percentBp, "percentBp");
        this.monthlySalary = Objects.requireNonNull(monthlySalary, "monthlySalary");
    }

    public long calculate(PayrollContext ctx) {
        var period = ctx.getPeriod();
        long salary = monthlySalary.requireOn(period.from(), "оклад для ночных");
        long bp = percentBp.requireOn(period.from(), "процент ночных");
        return Math.round(salary * (bp / 10_000.0));
    }
}
