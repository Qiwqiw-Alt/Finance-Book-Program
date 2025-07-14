package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.*;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class showFRDate {
    static Scanner scanner = new Scanner(System.in);

    public static void showFinanceRecordbyDate(int currentFileMode) {
        int verification;

        int date;
        boolean date_verif;
        boolean repeatShowByDay;
        String month;
        int year;

        long income = 0;
        long outcome = 0;

        do {
            verification = -1;
            date_verif = false;
            repeatShowByDay = false;
            
            System.out.println("===================================================================================================================");
            System.out.println("=                                          SHOW FINANCE RECORD BY DAY                                             =");
            System.out.println("===================================================================================================================");
            System.out.print("Date                 : "); date = scanner.nextInt(); scanner.nextLine();
            System.out.print("Month                : "); month  = scanner.nextLine();
            System.out.print("Year                 : "); year = scanner.nextInt(); scanner.nextLine();
            System.out.println("===================================================================================================================");
            
            if(date <= 31 && date >= 1) {
                date_verif = true;
                String keyDate = String.format("%02d%02d%04d", date, monthOrNumberConvert.monthToNumber(month), year);

                if (currentFileMode == 1) {
                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);

                    if(transactionsTempList != null && !transactionsTempList.isEmpty()) {
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
                        System.out.println("Income total: Rp " + income);
                        System.out.println("Outcome total: Rp " + outcome);
                        System.out.println("===================================================================================================================");
                    }
                    else {
                        System.out.println("\nThere are no transactions on that date.");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            System.out.println("\nInterrupted pause");
                        }
                        consoleUtils.clearScreen();
                    }
                }
                else {
                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);

                    if(transactionsTempList != null && !transactionsTempList.isEmpty()) {
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
                        System.out.println("Income total: Rp " + income);
                        System.out.println("Outcome total: Rp " + outcome);
                        System.out.println("===================================================================================================================");
                    }
                    else {
                        System.out.println("\nThere are no transactions on that date.");
                        try {
                            TimeUnit.SECONDS.sleep(2);
                        } catch (InterruptedException e) {
                            System.out.println("\nInterrupted pause");
                        }
                        consoleUtils.clearScreen();
                    }
                }
                
                do {
                    System.out.println("\n===================================================================================================================");
                    System.out.println("= 1. See another transaction by day                                                                               =");
                    System.out.println("= 2. Back to show finance menu                                                                                    =");
                    System.out.println("= 3. Back to main menu                                                                                            =");
                    System.out.println("===================================================================================================================");
                    
                    System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

                    switch(verification) {
                        case 1: repeatShowByDay = true; break;
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
            else {
                System.out.println("Please enter a valid date between 1 and 31");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while (!date_verif || repeatShowByDay);
    }

}
