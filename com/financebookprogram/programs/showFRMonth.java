package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;
import com.financebookprogram.utils.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class showFRMonth {
    static Scanner scanner = new Scanner(System.in);

    public static void showFinanceRecordbyMonth(int currentFileMode) {
        int verification;

        boolean founded;
        boolean repeatShowByMonth;
        String month;
        int year;

        long income = 0;
        long outcome = 0;

        do {
            founded = false;
            repeatShowByMonth = false;
            System.out.println("===================================================================================================================");
            System.out.println("=                                         SHOW FINANCE RECORD BY MONTH                                            =");
            System.out.println("===================================================================================================================");
            System.out.print("Month                : "); month  = scanner.nextLine();
            System.out.print("Year                 : "); year = scanner.nextInt(); scanner.nextLine();
            System.out.println("===================================================================================================================");

            int numberMonth = monthOrNumberConvert.monthToNumber(month);
            String monthStr = String.format("%02d", numberMonth);
            String yearStr = String.format("%04d", year);
    
            if (currentFileMode == 1) {
                for(String keyDate : financeBook.transactionsByDate.keySet()) {
                    String monthKey = keyDate.substring(2, 4);
                    String yearKey = keyDate.substring(4, 8);
                    founded = false;
                    
                    if (monthKey.equals(monthStr) && yearKey.equals(yearStr)) {
                        LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);

                        if (transactionsTempList == null || transactionsTempList.isEmpty()) continue;

                        for (Transaction transactionTemp : transactionsTempList) {
                            System.out.println(transactionTemp);
                            System.out.println("===================================================================================================================");
                            if(transactionTemp.type.equalsIgnoreCase("income")){
                                income += transactionTemp.amount;
                            }
                            else {
                                outcome += transactionTemp.amount;
                            }
                        }
                        founded = true;
                    }
                    System.out.println("Income total: Rp " + income);
                    System.out.println("Outcome total: Rp " + outcome);
                    System.out.println("===================================================================================================================");    
                }
            }
            else {
                for(String keyDate : financeBook.transactionsByDateFromImport.keySet()) {
                    String monthKey = keyDate.substring(2, 4);
                    String yearKey = keyDate.substring(4, 8);
                    founded = false;
                    
                    if (monthKey.equals(monthStr) && yearKey.equals(yearStr)) {
                        LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);

                        if (transactionsTempList == null || transactionsTempList.isEmpty()) continue;

                        for (Transaction transactionTemp : transactionsTempList) {
                            System.out.println(transactionTemp);
                            System.out.println("===================================================================================================================");
                            if(transactionTemp.type.equalsIgnoreCase("income")){
                                income += transactionTemp.amount;
                            }
                            else {
                                outcome += transactionTemp.amount;
                            }
                        }
                        founded = true;
                    }
                    System.out.println("Income total: Rp " + income);
                    System.out.println("Outcome total: Rp " + outcome);
                    System.out.println("===================================================================================================================");    
                }
            }

            if (!founded) {
                System.out.println("\nNo finance entries were found for the selected month and year");
            }

            do {
                System.out.println("\n===================================================================================================================");
                System.out.println("= 1. See another transaction by month                                                                             =");
                System.out.println("= 2. Back to show finance menu                                                                                    =");
                System.out.println("= 3. Back to main menu                                                                                            =");
                System.out.println("===================================================================================================================");
                    
                System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

                switch(verification) {
                    case 1: repeatShowByMonth = true; break;
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
        } while (repeatShowByMonth);
    }
}
