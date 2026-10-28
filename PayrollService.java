package ru.payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PayrollService {

    private final int deductionLimitPercent;


    public PayrollService(int deductionLimitPercent) {
        if (deductionLimitPercent < 1 || deductionLimitPercent > 100) {
            throw new IllegalArgumentException(
                    "deductionLimitPercent must be 1..100: " + deductionLimitPercent);
        }
        this.deductionLimitPercent = deductionLimitPercent;
    }

    public PayrollService() {
        this(50);
    }

    public Payslip calculate(Employee employee, PayrollPeriod period, List<PayrollElement> elements) {
        Objects.requireNonNull(employee, "employee");
        Objects.requireNonNull(period, "period");
        Objects.requireNonNull(elements, "elements");

        var ctx = new PayrollContext(employee, period);
        var lines = new ArrayList<PayslipLine>();

        for (var el : elements) {
            if (el.kind() != ElementKind.ACCRUAL) {
                continue;
            }
            long amount = el.calculate(ctx);
            boolean inTax = el.includedInTaxBase();
            ctx.addAccrual(amount, inTax);
            lines.add(new PayslipLine(el, amount, inTax ? "в базе НДФЛ" : "вне базы НДФЛ"));
        }

        for (var el : elements) {
            if (!(el instanceof Deduction d) || !d.isTax()) {
                continue;
            }
            long amount = el.calculate(ctx);
            ctx.addDeduction(amount, true);
            lines.add(new PayslipLine(el, amount,
                    "база " + Money.format(ctx.getTaxBase())));
        }

        for (var el : elements) {
            if (!(el instanceof Deduction d) || d.isTax()) {
                continue;
            }
            long amount = el.calculate(ctx);
            ctx.addDeduction(amount, false);
            lines.add(new PayslipLine(el, amount, ""));
        }

        if (ctx.getAccruedTotal() > 0) {
            long maxAllowed = ctx.getAccruedTotal() * deductionLimitPercent / 100;
            if (ctx.getNonTaxDeducted() > maxAllowed) {
                throw new DeductionLimitException(
                        ctx.getNonTaxDeducted(), ctx.getAccruedTotal(), deductionLimitPercent);
            }
        } else if (ctx.getNonTaxDeducted() > 0) {
            throw new DeductionLimitException(
                    ctx.getNonTaxDeducted(), ctx.getAccruedTotal(), deductionLimitPercent);
        }

        return new Payslip(employee, period, lines,
                ctx.getAccruedTotal(), ctx.getDeductedTotal(), ctx.net());
    }

    public int getDeductionLimitPercent() {
        return deductionLimitPercent;
    }
}
