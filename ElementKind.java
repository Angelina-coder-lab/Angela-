package ru.payroll;

public enum ElementKind {
    ACCRUAL("Начисление", true),
    DEDUCTION("Удержание", false);

    private final String title;
    private final boolean increasesPay;

    ElementKind(String title, boolean increasesPay) {
        this.title = title;
        this.increasesPay = increasesPay;
    }

    public String getTitle() {
        return title;
    }

    public boolean increasesPay() {
        return increasesPay;
    }


    public String sign() {
        return switch (this) {
            case ACCRUAL -> "+";
            case DEDUCTION -> "−";
        };
    }
}
