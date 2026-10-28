package ru.payroll;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public final class RateSchedule {

    public record Entry(DateInterval interval, long value) {
        public Entry {
            Objects.requireNonNull(interval, "interval");
        }
    }

    private final List<Entry> entries = new ArrayList<>();

    public void add(DateInterval interval, long value) {
        Objects.requireNonNull(interval, "interval");
        entries.add(new Entry(interval, value));
    }


    public void add(LocalDate from, LocalDate to, long value) {
        add(new DateInterval(from, to), value);
    }


    public Optional<Long> valueOn(LocalDate date) {
        Objects.requireNonNull(date, "date");
        return entries.stream()
                .filter(e -> e.interval().contains(date))
                .map(Entry::value)
                .findFirst();
    }

    public long requireOn(LocalDate date, String what) {
        return valueOn(date).orElseThrow(() ->
                new PayrollException("Нет ставки «" + what + "» на дату " + date));
    }


    public List<Entry> entries() {
        return Collections.unmodifiableList(new ArrayList<>(entries));
    }
}
