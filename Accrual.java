package ru.payroll;


public abstract sealed class Accrual implements PayrollElement
        permits SalaryAccrual, BonusAccrual, NightAccrual, VacationAccrual, SickAccrual {

    private final String code;
    private final String title;
    private final boolean includedInTaxBase;

    protected Accrual(String code, String title, boolean includedInTaxBase) {
        this.code = code;
        this.title = title;
        this.includedInTaxBase = includedInTaxBase;
    }

    public final String code() {
        return code;
    }

    public final String title() {
        return title;
    }

    public final ElementKind kind() {
        return ElementKind.ACCRUAL;
    }


    public final boolean includedInTaxBase() {
        return includedInTaxBase;
    }
}
