package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class deleteFR {
    static Scanner scanner = new Scanner(System.in);
    public static void editFinanceRecordDelete(int currentFileMode) {
        int  verification;
        int deleteChoice;

        boolean deleteVerif;
        boolean repeatDelete;

        do {
            int number = 1;
            deleteVerif = true;
            repeatDelete = false;

            List<String> dateKeys = new ArrayList<>();
            List<Transaction> flatList = new ArrayList<>();

            System.out.println("===================================================================================================================");
            System.out.println("=                                            DELETE FINANCE RECORD                                                =");
            System.out.println("=                                          ALL FINANCIAL RECORD LIST                                              =");
            System.out.println("===================================================================================================================");
            if(currentFileMode == 1) {
                for(String keyDate : financeBook.transactionsByDate.keySet()) {
                String dateKey = keyDate.substring(0,2);
                String monthKey = keyDate.substring(2, 4);
                String yearKey = keyDate.substring(4, 8);
                String formattedDate = yearKey + "-" + monthKey + "-" + dateKey;

                LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDate.get(keyDate);
                for(Transaction transactionTemp : transactionsTempList) {
                    System.out.printf("[%d] %s | %-20s | %-7s | %-20s | Rp %,10d\n",
                            number,
                            formattedDate,
                            transactionTemp.nameTrs,      
                            transactionTemp.type,
                            transactionTemp.category,
                            transactionTemp.amount);
                    flatList.add(transactionTemp);
                    dateKeys.add(keyDate);
                    number++;
                }
            }
            System.out.println("===================================================================================================================");
            }
            else {
                for(String keyDate : financeBook.transactionsByDateFromImport.keySet()) {
                    String dateKey = keyDate.substring(0,2);
                    String monthKey = keyDate.substring(2, 4);
                    String yearKey = keyDate.substring(4, 8);
                    String formattedDate = yearKey + "-" + monthKey + "-" + dateKey;

                    LinkedList<Transaction> transactionsTempList = financeBook.transactionsByDateFromImport.get(keyDate);
                    for(Transaction transactionTemp : transactionsTempList) {
                        System.out.printf("[%d] %s | %-20s | %-7s | %-20s | Rp %,10d\n",
                                number,
                                formattedDate,
                                transactionTemp.nameTrs,      
                                transactionTemp.type,
                                transactionTemp.category,
                                transactionTemp.amount);
                        flatList.add(transactionTemp);
                        dateKeys.add(keyDate);
                        number++;
                    }
                }
                System.out.println("===================================================================================================================");
            }


            System.out.print("\nEnter the number of the transaction to delete: "); deleteChoice = scanner.nextInt(); scanner.nextLine();
            if (deleteChoice < 1 || deleteChoice > flatList.size()) {
                System.out.println("\nInvalid selection. Please try again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
            else {
                Transaction selected = flatList.get(deleteChoice - 1);
                String selectedDate = dateKeys.get(deleteChoice - 1);
                deleteVerif = false;

                System.out.println("Selected transaction: ");
                System.out.println(selected);

                System.out.print("Are you sure you want to delete this transaction? (Y/N): ");
                String confirm = scanner.nextLine();

                if (confirm.equalsIgnoreCase("Y")) {
                    financeBook.transactionsByDate.get(selectedDate).remove(selected);
                    if (financeBook.transactionsByDate.get(selectedDate).isEmpty()) {
                        financeBook.transactionsByDate.remove(selectedDate);
                    }
                    System.out.println("\nTransaction successfully deleted");
                } else {
                    System.out.println("\nDeletion cancelled");
                }

                do {
                    System.out.println("\n===================================================================================================================");
                    System.out.println("= 1. Delete another transaction                                                                                   =");
                    System.out.println("= 2. Back to edit menu                                                                                            =");
                    System.out.println("= 3. Back to main menu                                                                                            =");
                    System.out.println("===================================================================================================================");
                        
                    System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

                    switch(verification) {
                        case 1: repeatDelete = true; break;
                        case 2: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
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
            consoleUtils.clearScreen(); 
        } while(repeatDelete || !deleteVerif);
    }
}
