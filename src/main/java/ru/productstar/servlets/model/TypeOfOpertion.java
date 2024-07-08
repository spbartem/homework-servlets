package ru.productstar.servlets.model;

public enum TypeOfOpertion {
    PROFIT("Profit"), EXPENSE("Expense");

    private final String name;

    TypeOfOpertion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
