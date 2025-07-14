package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class addFR {
    static Scanner scanner = new Scanner(System.in);

    public static void addFinanceRecordDate(int currentFileMode) {
        boolean date_verif;

        int date; 
        String month;
        int year;

        do {
            date_verif = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                        ADD FINANCE RECORD DATE                                                  =");
            System.out.println("===================================================================================================================");
            System.out.print("Date                 : "); date = scanner.nextInt(); scanner.nextLine();
            System.out.print("Month                : "); month  = scanner.nextLine();
            System.out.print("Year                 : "); year = scanner.nextInt(); scanner.nextLine();
            System.out.println("===================================================================================================================");
            
            if(date <= 31 && date >= 1) {
                date_verif = true;
            }
            else {
                System.out.println("\nPlease enter a valid date between 1 and 31");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while (!date_verif);
  
        String keyDate = String.format("%02d%02d%04d", date, monthOrNumberConvert.monthToNumber(month), year);
        Date dMY = new Date(date, month, year);
        consoleUtils.clearScreen();
        addFinanceRecordTrs(keyDate, dMY, currentFileMode);
    }

    public static void addFinanceRecordTrs(String keyDate, Date dMY, int currentFileMode) {
        int verification;
        boolean repeatAdd;

        String nameTrs;
        String category;
        String type;
        long  amount;
        String description;

        do {
            repeatAdd = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                       ADD FINANCE RECORD TRANSACTION                                            =");
            System.out.println("===================================================================================================================");
            System.out.println("Date : " + dMY.year + "-" + dMY.month + "-" + dMY.date);
            System.out.println("===================================================================================================================");
            System.out.print("Transaction name     : "); nameTrs = scanner.nextLine();
            System.out.print("Category             : "); category = scanner.nextLine();
            System.out.print("Type (income/outcome): "); type = scanner.nextLine();
            System.out.print("Amount               : Rp "); amount = scanner.nextLong(); scanner.nextLine();
            System.out.print("Description          : "); description = scanner.nextLine();
            System.out.println("===================================================================================================================");

            Transaction trs = new Transaction(dMY, nameTrs, category, type, amount, description);

            if (currentFileMode == 1) {
                financeBook.transactionsByDate.putIfAbsent(keyDate, new LinkedList<>());
                financeBook.transactionsByDate.get(keyDate).add(trs);

                if (!financeBook.uniqueCategory.contains(category)) {
                    financeBook.uniqueCategory.add(category);
                }

                if (type.equalsIgnoreCase("income")) {
                    if (!financeBook.totalIncomePerCategory.containsKey(category)) {
                        financeBook.totalIncomePerCategory.put(category, amount);
                    } 
                    else {
                        long current = financeBook.totalIncomePerCategory.get(category);
                        financeBook.totalIncomePerCategory.put(category, current + amount);
                    }
                } else {
                    if (!financeBook.totalOutcomePerCategory.containsKey(category)) {
                        financeBook.totalOutcomePerCategoryFromImport.put(category, amount);
                    } else {
                        long current = financeBook.totalOutcomePerCategory.get(category);
                        financeBook.totalOutcomePerCategory.put(category, current + amount);
                    }
                }
            }
            else {
                financeBook.transactionsByDateFromImport.putIfAbsent(keyDate, new LinkedList<>());
                financeBook.transactionsByDateFromImport.get(keyDate).add(trs);

                if (!financeBook.uniqueCategoryFromImport.contains(category)) {
                    financeBook.uniqueCategoryFromImport.add(category);
                }

                if (type.equalsIgnoreCase("income")) {
                    if (!financeBook.totalIncomePerCategoryFromImport.containsKey(category)) {
                        financeBook.totalIncomePerCategoryFromImport.put(category, amount);
                    } 
                    else {
                        long current = financeBook.totalIncomePerCategoryFromImport.get(category);
                        financeBook.totalIncomePerCategoryFromImport.put(category, current + amount);
                    }
                } else {
                    if (!financeBook.totalOutcomePerCategoryFromImport.containsKey(category)) {
                        financeBook.totalOutcomePerCategoryFromImport.put(category, amount);
                    } else {
                        long current = financeBook.totalOutcomePerCategoryFromImport.get(category);
                        financeBook.totalOutcomePerCategoryFromImport.put(category, current + amount);
                    }
                }
            }

            do {
                System.out.println("\n===================================================================================================================");
                System.out.println("= 1. Add another transaction                                                                                      =");
                System.out.println("= 2. Add another transaction at diferent date                                                                     =");
                System.out.println("= 3. Back to main menu                                                                                            =");
                System.out.println("===================================================================================================================");
                    
                System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

                switch(verification) {
                    case 1: consoleUtils.clearScreen(); repeatAdd = true; break;
                    case 2: consoleUtils.clearScreen(); addFinanceRecordDate(currentFileMode); break;
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
        } while (repeatAdd);
    }
}
