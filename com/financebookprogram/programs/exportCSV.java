package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class exportCSV {
    static Scanner scanner = new Scanner(System.in);

    public static void exportToCSV(int currentFileMode) {
        String verification;

        String nameFile;

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
            System.out.println("===================================================================================================================");
            System.out.println("=                                                EXPORT TO CSV                                                    =");
            System.out.println("===================================================================================================================");
            System.out.print("Input file name: "); nameFile = scanner.nextLine();
            System.out.println("===================================================================================================================");

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("com/financebookprogram/file/" + nameFile + ".csv"))) {
                writer.write("Date,Transaction Name,Category,Type,Amount,Description");
                writer.newLine();

                if(currentFileMode == 1) {
                    for (Map.Entry<String, LinkedList<Transaction>> entry : financeBook.transactionsByDate.entrySet()) {
                        for (Transaction transactionTemp : entry.getValue()) {
                            writer.write(String.format("%s,%s,%s,%s,%d,%s",
                                entry.getKey(),
                                transactionTemp.nameTrs,
                                transactionTemp.category,
                                transactionTemp.type,
                                transactionTemp.amount,
                                transactionTemp.description.replace(",", ";") 
                            ));
                            writer.newLine();
                        }
                    }
                }
                else {
                    for (Map.Entry<String, LinkedList<Transaction>> entry : financeBook.transactionsByDateFromImport.entrySet()) {
                        for (Transaction transactionTemp : entry.getValue()) {
                            writer.write(String.format("%s,%s,%s,%s,%d,%s",
                                entry.getKey(),
                                transactionTemp.nameTrs,
                                transactionTemp.category,
                                transactionTemp.type,
                                transactionTemp.amount,
                                transactionTemp.description.replace(",", ";") 
                            ));
                            writer.newLine();
                        }
                    }
                }
    
                System.out.println("CSV file \"" + nameFile + ".csv\" has been successfully created!");

            } catch (IOException e) {
                System.out.println("Error while writing the CSV file: " + e.getMessage());
            }

            System.out.print("Do you want to export another CSV file with different name (Y/N)?: "); verification = scanner.nextLine();
        } while(verification.equalsIgnoreCase("Y"));

       consoleUtils.clearScreen();
        financeBook.menu();
    }
}