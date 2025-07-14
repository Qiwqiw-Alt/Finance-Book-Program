package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;

import java.util.LinkedList;
import java.util.concurrent.TimeUnit;
import java.util.Scanner;

public class showFRCat {
    static Scanner scanner = new Scanner(System.in);

    public static void showFinanceRecordbyCategory(int currentFileMode) {
        boolean categoryExists;
        String selectedCategory;

        do {
            categoryExists = false;
            int number = 1;

            System.out.println("===================================================================================================================");
            System.out.println("=                                       SHOW FINANCE RECORD BY CATEGORY                                           =");
            System.out.println("=                                               CATEGORY LIST                                                     =");
            System.out.println("===================================================================================================================");
            
            if(currentFileMode == 1) {
                for(String category : financeBook.uniqueCategory) {
                    System.out.println(number + ". " + category);
                    number++;
                }
            }
            else {
                for(String category : financeBook.uniqueCategoryFromImport) {
                    System.out.println(number + ". " + category);
                    number++;
                }
            }
            
            System.out.println("===================================================================================================================");

            System.out.print("\nInput the category you want to see: "); selectedCategory = scanner.nextLine();

            if(currentFileMode == 1) {
                for (String category : financeBook.uniqueCategory) {
                    if (category.equalsIgnoreCase(selectedCategory)) {
                        categoryExists = true;
                        break;
                    }
                }
            }
            else {
                for (String category : financeBook.uniqueCategoryFromImport) {
                    if (category.equalsIgnoreCase(selectedCategory)) {
                        categoryExists = true;
                        break;
                    }
                }
            }
            
            if (!categoryExists) {
                System.out.println("Selected category is not on the list. Please input a category from the list");
            }
            else {
                showFinanceRecordbyCategoryHelper(selectedCategory, currentFileMode);
            }
        } while (!categoryExists);
    }

    public static void showFinanceRecordbyCategoryHelper(String category, int currentFileMode) {
        int verification;

        System.out.println("===================================================================================================================");
        System.out.println("=                                       SHOW FINANCE RECORD BY CATEGORY                                           =");
        System.out.println("===================================================================================================================");
        System.out.println("Category           : " + category); 
        System.out.println("===================================================================================================================");

        if(currentFileMode == 1) {
            for(String keyDate : financeBook.transactionsByDate.keySet()) {
                LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);
                for(Transaction transactionTemp : transactionsTempList) {
                    if(transactionTemp.category.equals(category)) {
                        System.out.println(transactionTemp);
                        System.out.println("===================================================================================================================");
                    }
                }
            }
        }
        else {
            for(String keyDate : financeBook.transactionsByDateFromImport.keySet()) {
                LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);
                for(Transaction transactionTemp : transactionsTempList) {
                    if(transactionTemp.category.equals(category)) {
                        System.out.println(transactionTemp);
                        System.out.println("===================================================================================================================");
                    }
                }
            }
        }
        
        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. See another transaction by category                                                                          =");
            System.out.println("= 2. Back to show finance menu                                                                                    =");
            System.out.println("= 3. Back to main menu                                                                                            =");
            System.out.println("===================================================================================================================");
                    
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); showFinanceRecordbyCategory(currentFileMode); break;
                case 2: consoleUtils.clearScreen(); financeBook.showFinanceRecord(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.menu(); break;
                default: 
                    System.out.println("\nThe number of menu is not found, please input the correct number");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        System.out.println("\nInterrupted pause");
                    }
                    consoleUtils.clearScreen();
                    break;
            }
        } while (verification < 1 || verification > 3);
    }
}
