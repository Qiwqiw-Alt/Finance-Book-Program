package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class importCSV {
    static Scanner scanner = new Scanner(System.in);

    public static void importFromCSV() {
        String verification;
        boolean hasFiles;

        String nameFile;

        do {
            hasFiles = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                               IMPORT FROM CSV                                                   =");
            System.out.println("===================================================================================================================");
            File folder = new File("com/financebookprogram/file/");
            File[] listOfFiles = folder.listFiles();

            System.out.println("= Available CSV files:                                                                                            =");
            System.out.println("===================================================================================================================");
            if(listOfFiles != null && listOfFiles.length > 0) {
                for(File fileTemp : listOfFiles) {
                    if(fileTemp.isFile() && fileTemp.getName().endsWith(".csv")) {
                        System.out.println("- " + fileTemp.getName());
                        hasFiles = true;
                    }
                }
            }
            if(!hasFiles) {
                System.out.println("No CSV Files found in 'file' folder");
            }

            System.out.print("\nInput file name (without extension): "); nameFile = scanner.nextLine();

            File file = new File("com/financebookprogram/file/" + nameFile + ".csv");

            if (!file.exists()) {
                System.out.println("File not found. Please make sure the file exists in the 'file' folder.");
            } else {
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line = reader.readLine();
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split(",", -1); 

                        String dateStr = parts[0];
                        String nameTrs = parts[1];
                        String category = parts[2];
                        String type = parts[3];
                        long amount = Long.parseLong(parts[4]);
                        String description = parts[5];

                        int date = Integer.parseInt(dateStr.substring(0, 2));
                        int month = Integer.parseInt(dateStr.substring(2, 4));
                        int year = Integer.parseInt(dateStr.substring(4, 8));


                        Date dMY = new Date(date, monthOrNumberConvert.NumberToMonth(month), year);
                        String keyDate = String.format("%02d%02d%04d", date, month, year);

                        if (!financeBook.uniqueCategoryFromImport.contains(category)) {
                            financeBook.uniqueCategoryFromImport.add(category);
                        }

                        if (type.equalsIgnoreCase("income")) {
                            if (!financeBook.totalIncomePerCategoryFromImport.containsKey(category)) {
                                financeBook.totalIncomePerCategoryFromImport.put(category, amount);
                            } else {
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

                        Transaction transactionImport = new Transaction(dMY, nameTrs, category, type, amount, description);

                        financeBook.transactionsByDateFromImport.putIfAbsent(keyDate, new LinkedList<>());
                        financeBook.transactionsByDateFromImport.get(keyDate).add(transactionImport);
                    }

                    System.out.println("Import successful!");

                } catch (IOException e) {
                    System.out.println("Error while reading file: " + e.getMessage());
                }
            }

            System.out.print("Do you want to change to another CSV file (Y/N)?: ");verification = scanner.nextLine();
        } while(verification.equalsIgnoreCase("Y"));

        consoleUtils.clearScreen();
        financeBook.menu();
    }
}
