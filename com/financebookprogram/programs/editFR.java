package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class editFR {
    static Scanner scanner = new Scanner(System.in);

    public static void editFinanceRecordEdit(int currentFileMode) {
        int editChoice;
        boolean choiceVerif;

        do {
            int number = 1;
            choiceVerif = false;

            List<String> dateKeys = new ArrayList<>();
            List<Transaction> flatList = new ArrayList<>();

            System.out.println("===================================================================================================================");
            System.out.println("=                                             EDIT FINANCE RECORD                                                 =");
            System.out.println("=                                          ALL FINANCIAL RECORD LIST                                              =");
            System.out.println("===================================================================================================================");
            
            if(currentFileMode == 1) {
                for(String keyDate : financeBook.transactionsByDate.keySet()) {
                    String dateKey = keyDate.substring(0,2);
                    String monthKey = keyDate.substring(2, 4);
                    String yearKey = keyDate.substring(4, 8);
                    String formattedDate = yearKey + "-" + dateKey + "-" + monthKey;

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
            }
            else {
                for(String keyDate : financeBook.transactionsByDateFromImport.keySet()) {
                    String dateKey = keyDate.substring(0,2);
                    String monthKey = keyDate.substring(2, 4);
                    String yearKey = keyDate.substring(4, 8);
                    String formattedDate = yearKey + "-" + dateKey + "-" + monthKey;

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
            }
           
            System.out.print("\nEnter the number of the transaction to edit: "); editChoice = scanner.nextInt(); scanner.nextLine();

            if (editChoice < 1 || editChoice > flatList.size()) {
                System.out.println("\nInvalid selection. Please try again");
                try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        System.out.println("\nInterrupted pause");
                    }
               consoleUtils.clearScreen();
            }
            else {
                Transaction selected = flatList.get(editChoice - 1);
                String selectedDate = dateKeys.get(editChoice - 1);
                choiceVerif = true;

                editFRUtils.editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
            }
        } while (!choiceVerif);
    }
}
