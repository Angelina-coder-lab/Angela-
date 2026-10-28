package ru.payroll;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class Payslip {

    private final Employee employee;
    private final PayrollPeriod period;
    private final List<PayslipLine> lines;
    private final long accruedTotal;
    private final long deductedTotal;
    private final long net;

    public Payslip(Employee employee, PayrollPeriod period, List<PayslipLine> lines,
                   long accruedTotal, long deductedTotal, long net) {
        this.employee = Objects.requireNonNull(employee, "employee");
        this.period = Objects.requireNonNull(period, "period");
        this.lines = List.copyOf(Objects.requireNonNull(lines, "lines"));
        this.accruedTotal = accruedTotal;
        this.deductedTotal = deductedTotal;
        this.net = net;
    }

    public Employee getEmployee() {
        return employee;
    }

    public PayrollPeriod getPeriod() {
        return period;
    }

    public List<PayslipLine> getLines() {
        return lines;
    }

    public long getAccruedTotal() {
        return accruedTotal;
    }

    public long getDeductedTotal() {
        return deductedTotal;
    }

    public long getNet() {
        return net;
    }

    public String render() {
        var sb = new StringBuilder();
        sb.append("=== Расчётный листок ===\n");
        sb.append("Сотрудник: ").append(employee).append('\n');
        sb.append("Период:    ").append(period).append('\n');
        sb.append("----------------------------------------------\n");
        for (var line : lines) {
            sb.append(line.format()).append('\n');
        }
        sb.append("----------------------------------------------\n");
        sb.append(String.format("Итого начислено:  %s\n", Money.format(accruedTotal)));
        sb.append(String.format("Итого удержано:   %s\n", Money.format(deductedTotal)));
        sb.append(String.format("К выдаче:         %s\n", Money.format(net)));
        return sb.toString();
    }

    public String toString() {
        return render();
    }
}
