package ru.payroll;


public interface PayrollElement {


    String code();

    String title();

    ElementKind kind();

    long calculate(PayrollContext ctx);

    default boolean includedInTaxBase() {
        return kind() == ElementKind.ACCRUAL;
    }
}
