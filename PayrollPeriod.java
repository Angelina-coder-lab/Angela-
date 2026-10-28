package ru.payroll;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

public record PayrollPeriod(DateInterval interval) {

    public PayrollPeriod {
        Objects.requireNonNull(interval, "interval");
    }

    public static PayrollPeriod ofMonth(int year, int month) {
        YearMonth ym = YearMonth.of(year, month);
        return new PayrollPeriod(new DateInterval(ym.atDay(1), ym.atEndOfMonth()));
    }

    public static PayrollPeriod of(LocalDate from, LocalDate to) {
        return new PayrollPeriod(new DateInterval(from, to));
    }

    public LocalDate from() {
        return interval.from();
    }

    public LocalDate to() {
        return interval.to();
    }

    public long days() {
        return interval.calendarDays();
    }

    public String toString() {
        return interval.toString();
    }
}
