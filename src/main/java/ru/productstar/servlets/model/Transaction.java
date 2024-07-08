package ru.productstar.servlets.model;

public class Transaction {
    private String name;
    private int sum;
    private TypeOfOpertion operation;

    public Transaction(String name, int sum, TypeOfOpertion operation) {
        this.name = name;
        this.sum = sum;
        this.operation = operation;
    }

    public String getName() {
        return name;
    }

    public int getSum() {
        return sum;
    }

    public TypeOfOpertion getOperation() {
        return operation;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", sum=" + sum +
                ", operation=" + operation +
                '}';
    }
}
