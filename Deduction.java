package ru.payroll;


public abstract sealed class Deduction implements PayrollElement
        permits TaxDeduction, AdvanceDeduction, WritDeduction {

    private final String code;
    private final String title;
    private final boolean tax;


    protected Deduction(String code, String title, boolean tax) {
        this.code = code;
        this.title = title;
        this.tax = tax;
    }

    public final String code() {
        return code;
    }

    public final String title() {
        return title;
    }

    public final ElementKind kind() {
        return ElementKind.DEDUCTION;
    }

    public final boolean isTax() {
        return tax;
    }
}
