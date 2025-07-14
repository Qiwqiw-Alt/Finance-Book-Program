package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class showFRAll {
    static Scanner scanner = new Scanner(System.in);

    public static void allFinanceRecord(int currentFileMode) {
        int verification;
        boolean repeatShowAll;

        long income = 0;
        long outcome = 0;

        do {
            repeatShowAll = false;
            System.out.println("===================================================================================================================");
            System.out.println("=                                           SHOW ALL FINANCE RECORD                                               =");
            System.out.println("===================================================================================================================");

            if(currentFileMode == 1) {
                for(String keyDate : financeBook.transactionsByDate.keySet()) {

                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);
                    for(Transaction transactionTemp : transactionsTempList) {
                        System.out.println(transactionTemp);
                        System.out.println("===================================================================================================================");
                        if(transactionTemp.type.equalsIgnoreCase("income")){
                            income += transactionTemp.amount;
                        }
                        else {
                            outcome += transactionTemp.amount;
                        }
                    }
                }
                System.out.println("Income total: Rp " + income);
                System.out.println("Outcome total: Rp " + outcome);
                System.out.println("===================================================================================================================");
            }
            else {
                for(String keyDate : financeBook.transactionsByDateFromImport.keySet()) {            

                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);
                    for(Transaction transactionTemp : transactionsTempList) {
                        System.out.println(transactionTemp);
                        System.out.println("===================================================================================================================");
                        if(transactionTemp.type.equalsIgnoreCase("income")){
                            income += transactionTemp.amount;
                        }
                        else {
                            outcome += transactionTemp.amount;
                        }
                    }
                }
                System.out.println("Income total: Rp " + income);
                System.out.println("Outcome total: Rp " + outcome);
                System.out.println("===================================================================================================================");
            }
            
            do {
                System.out.println("\n===================================================================================================================");
                System.out.println("= 1. See all transaction again                                                                                    =");
                System.out.println("= 2. Back to show finance menu                                                                                    =");
                System.out.println("= 3. Back to main menu                                                                                            =");
                System.out.println("===================================================================================================================");
                        
                System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

                switch(verification) {
                    case 1: repeatShowAll = true; break;
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
                consoleUtils.clearScreen();
            } while (verification < 1 || verification > 3);
        } while (repeatShowAll);
    }
}