package com.financebookprogram.models;

public class Transaction {
    public Date dateTrs;
    public String nameTrs;
    public String category;
    public String type;
    public long amount;
    public String description;

    public Transaction(Date dateTrs, String nameTrs, String category, String type, long amount, String description) {
        this.dateTrs = dateTrs;
        this.nameTrs = nameTrs;
        this.category = category;
        this.type = type;
        this.amount = amount;
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format(
            "Name        : %s\n" +
            "Category    : %s\n" +
            "Type        : %s\n" +
            "Amount      : Rp %,d\n" +
            "Description : %s\n" +
            "Date        : %s",
            nameTrs, category, type, amount, description, dateTrs.toString()
        );
} 
}
