package com.financebookprogram.models;

public class CategorySpending {
    public String category;
    public long totalAmount;

    public CategorySpending(String category, Long totalAmount) {
        this.category = category;
        this.totalAmount = totalAmount;
    }
}