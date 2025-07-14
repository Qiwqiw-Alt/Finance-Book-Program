package com.financebookprogram.utils;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;

import java.util.Map;

public class sortingForCategory {
    public static void quickSort(CategorySpending[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    public static int partition(CategorySpending[] arr, int low, int high) {
        long pivot = arr[high].totalAmount;
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j].totalAmount > pivot) { 
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    public static void swap(CategorySpending[] arr, int i, int j) {
        CategorySpending temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void QsortForCategory(long income, long outcome, int currentFileMode, String type) {
        Map<String, Long> totalSpendingPerCategory = null;
        if (type.equalsIgnoreCase("income")) {
            totalSpendingPerCategory = (currentFileMode == 1) 
                ? financeBook.totalIncomePerCategory 
                : financeBook.totalIncomePerCategoryFromImport;
        } else {
            totalSpendingPerCategory = (currentFileMode == 1) 
                ? financeBook.totalOutcomePerCategory 
                : financeBook.totalOutcomePerCategoryFromImport;
        }

        CategorySpending[] arr = new CategorySpending[totalSpendingPerCategory.size()];
        int i = 0;
        for (Map.Entry<String, Long> entry : totalSpendingPerCategory.entrySet()) {
            arr[i++] = new CategorySpending(entry.getKey(), entry.getValue());
        }

        quickSort(arr, 0, arr.length - 1);
        int number = 1;

        for (CategorySpending cs : arr) {
            double persenType = 0;
            double persenTotal = 0;

            if (type.equalsIgnoreCase("income")) {
                if (income > 0) {
                    persenType = (double) cs.totalAmount * 100 / income;
                }
                if (income + outcome > 0) {
                    persenTotal = (double) cs.totalAmount * 100 / (income + outcome);
                }
                System.out.printf("%d. %-25s : Rp %,15d | %.2f%% of income | %.2f%% of total\n",
                    number, cs.category, cs.totalAmount, persenType, persenTotal);

            } else {
                if (outcome > 0) {
                    persenType = (double) cs.totalAmount * 100 / outcome;
                }
                if (income + outcome > 0) {
                    persenTotal = (double) cs.totalAmount * 100 / (income + outcome);
                }
                System.out.printf("%d. %-25s : Rp %,15d | %.2f%% of outcome | %.2f%% of total\n",
                    number, cs.category, cs.totalAmount, persenType, persenTotal);
            }
            number++;
        }
    }
}
