package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class financeSum {
    static Scanner scanner = new Scanner(System.in);

     public static void financeSummary(int currentFileMode){
        String verification;

        long totalIncome = 0;
        long totalOutcome = 0;
        int totalTransactions = 0;

        if(currentFileMode == 1) {
            if (financeBook.transactionsByDate.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                financeBook.menu();
            }
        }
        else {
            if (financeBook.transactionsByDateFromImport.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                financeBook.menu();
            }
        }

        do {
            if(currentFileMode == 1) {
                for (String keyDate : financeBook.transactionsByDate.keySet()) {
                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);
                    for (Transaction transactionTemp : transactionsTempList) {
                        if (transactionTemp.type.equalsIgnoreCase("income")) {
                            totalIncome += transactionTemp.amount;
                        } else if (transactionTemp.type.equalsIgnoreCase("outcome")) {
                            totalOutcome += transactionTemp.amount;
                        }
                        totalTransactions++;
                    }
                }
            }
            else {
                for (String keyDate : financeBook.transactionsByDateFromImport.keySet()) {
                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);
                    for (Transaction transactionTemp : transactionsTempList) {
                        if (transactionTemp.type.equalsIgnoreCase("income")) {
                            totalIncome += transactionTemp.amount;
                        } else if (transactionTemp.type.equalsIgnoreCase("outcome")) {
                            totalOutcome += transactionTemp.amount;
                        }
                        totalTransactions++;
                    }
                }
            }

            long balance = totalIncome - totalOutcome;
            double incomePercent = totalTransactions == 0 ? 0 : (double) totalIncome * 100 / (totalIncome + totalOutcome);
            double outcomePercent = totalTransactions == 0 ? 0 : (double) totalOutcome * 100 / (totalIncome + totalOutcome);

            System.out.println("===================================================================================================================");
            System.out.println("=                                              FINANCIAL SUMMARY                                                  =");
            System.out.println("===================================================================================================================");
            System.out.printf("Total Transactions     : %d\n", totalTransactions);
            System.out.printf("Total Income           : Rp %,d\n", totalIncome);
            System.out.printf("Total Outcome          : Rp %,d\n", totalOutcome);
            System.out.printf("Current Balance        : Rp %,d\n", balance);
            System.out.printf("Income Percentage      : %.2f%%\n", incomePercent);
            System.out.printf("Outcome Percentage     : %.2f%%\n", outcomePercent);
            System.out.println("===================================================================================================================");
            System.out.println("= Top spending categories: income                                                                                 =");
            System.out.println("===================================================================================================================");
            sortingForCategory.QsortForCategory(totalIncome, totalOutcome, currentFileMode, "income");
            System.out.println("===================================================================================================================");
            System.out.println("= Top spending categories: outcome                                                                                =");
            System.out.println("===================================================================================================================");
            sortingForCategory.QsortForCategory(totalIncome, totalOutcome, currentFileMode, "outcome");
            System.out.println("===================================================================================================================");


            System.out.print("Do you want to return to main menu? (Y/N): "); verification = scanner.nextLine();
            consoleUtils.clearScreen();
        } while(verification.equalsIgnoreCase("N"));

        consoleUtils.clearScreen();
        financeBook.menu();
    }
}
