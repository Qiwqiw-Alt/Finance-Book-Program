package com.financebookprogram.programs;

import com.financebookprogram.main.*;
import com.financebookprogram.models.*;
import com.financebookprogram.utils.consoleUtils;
import com.financebookprogram.utils.monthOrNumberConvert;

import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class editFRUtils {
    static Scanner scanner = new Scanner(System.in);

    public static void selectedDisplay(Transaction selected) {
        Date dMY = selected.dateTrs;
        System.out.println("===================================================================================================================");
        System.out.println("=                                             SELECTED FINANCE RECORD                                                 =");
        System.out.println("===================================================================================================================");
        System.out.println("Date : " + dMY.year + "-" + dMY.month + "-" + dMY.date);
        System.out.println("===================================================================================================================");
        System.out.println("Transaction name     : " + selected.nameTrs); 
        System.out.println("Category             : " + selected.category ); 
        System.out.println("Type                 : " + selected.type); 
        System.out.println("Amount               : Rp " + selected.amount); 
        System.out.println("Description          : " + selected.description); 
        System.out.println("===================================================================================================================");

        System.out.println();
    }

    public static void editFinanceRecordEditMenu(Transaction selected, String selectedDate, int currentFileMode){
        int editChoiceMenu;

        selectedDisplay(selected);

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                           EDIT FINANCE RECORD MENU                                              =");
            System.out.println("===================================================================================================================");
            System.out.println("= 1. Edit finance record date                                                                                     =");
            System.out.println("= 2. Edit finance record name                                                                                     =");
            System.out.println("= 3. Edit finance record category                                                                                 =");
            System.out.println("= 4. Edit finance record type                                                                                     =");
            System.out.println("= 5. Edit finance record amount                                                                                   =");
            System.out.println("= 6. Edit finance record description                                                                              =");
            System.out.println("= 7. Edit another transaction                                                                                     =");
            System.out.println("= 8. Back to edit menu                                                                                            =");
            System.out.println("= 9. Back to main menu                                                                                            =");
            System.out.println("===================================================================================================================");
            System.out.print("\nInput the number of menu you want to use: "); editChoiceMenu = scanner.nextInt(); scanner.nextLine();

            switch(editChoiceMenu) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordDate(selected, selectedDate, currentFileMode); break;
                case 2: consoleUtils.clearScreen(); editFinanceRecordName(selected, selectedDate, currentFileMode); break;
                case 3: consoleUtils.clearScreen(); editFinanceRecordCategory(selected, selectedDate, currentFileMode); break;
                case 4: consoleUtils.clearScreen(); editFinanceRecordType(selected, selectedDate, currentFileMode); break;
                case 5: consoleUtils.clearScreen(); editFinanceRecordAmount(selected, selectedDate, currentFileMode); break;
                case 6: consoleUtils.clearScreen(); editFinanceRecordDescription(selected, selectedDate, currentFileMode); break;
                case 7: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 8: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 9: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while(editChoiceMenu < 1 || editChoiceMenu > 7);
    }

    public static void editFinanceRecordDate(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        int newDate;
        String newMonth;
        int newYear;

        boolean newDateVerif;

        do {
            newDateVerif = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                           EDIT FINANCE RECORD DATE                                              =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record date before: " + selected.dateTrs);
            System.out.println("===================================================================================================================");
            System.out.print("Input new date : "); newDate = scanner.nextInt(); scanner.nextLine();
            System.out.print("Input new month: "); newMonth = scanner.nextLine();
            System.out.print("Input new year : "); newYear = scanner.nextInt(); scanner.nextLine();
            System.out.println("===================================================================================================================");

            if(newDate >= 1 && newDate <= 31) {
                String newKeyDate = String.format("%02d%02d%04d", newDate, monthOrNumberConvert.monthToNumber(newMonth), newYear);
                Date newDateTrs = new Date(newDate, newMonth, newYear);
                selected.dateTrs = newDateTrs;

                if(currentFileMode == 1) {
                    financeBook.transactionsByDate.get(selectedDate).remove(selected);
                    if (financeBook.transactionsByDate.get(selectedDate).isEmpty()) {
                        financeBook.transactionsByDate.remove(selectedDate);
                    }

                    if (!financeBook.transactionsByDate.containsKey(newKeyDate)) {
                        financeBook.transactionsByDate.put(newKeyDate, new LinkedList<>());
                    }
                    financeBook.transactionsByDate.get(newKeyDate).add(selected);
                }
                else {
                    financeBook.transactionsByDateFromImport.get(selectedDate).remove(selected);
                    if (financeBook.transactionsByDateFromImport.get(selectedDate).isEmpty()) {
                        financeBook.transactionsByDateFromImport.remove(selectedDate);
                    }

                    if (!financeBook.transactionsByDateFromImport.containsKey(newKeyDate)) {
                        financeBook.transactionsByDateFromImport.put(newKeyDate, new LinkedList<>());
                    }
                    financeBook.transactionsByDate.get(newKeyDate).add(selected);
                }
                
                System.out.println("\nDate successfully updated");

                newDateVerif = true;
            }
            else {
                System.out.println("\nInvalid date. Must be between 1 and 31");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }      
        } while(!newDateVerif);

         do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu/Cancel                                                                                     =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }

    public static void editFinanceRecordName(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        String newName;

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                           EDIT FINANCE RECORD NAME                                              =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record name before: " + selected.nameTrs);
            System.out.println("===================================================================================================================");
            System.out.print("Input new name : "); newName = scanner.nextLine().trim();
            System.out.println("===================================================================================================================");

            if(!newName.isEmpty()){
                selected.nameTrs = newName;
                System.out.println("Name successfully updated");
            }
            else {
                System.out.println("Name cannot be empty. Please try again.");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while(newName.isEmpty());

        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu/Cancel                                                                                     =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }

    public static void editFinanceRecordCategory(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        String newCategory;

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                         EDIT FINANCE RECORD CATEGORY                                            =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record category before: " + selected.category);
            System.out.println("===================================================================================================================");
            System.out.print("Input new category : "); newCategory = scanner.nextLine().trim();
            System.out.println("===================================================================================================================");

            if (!newCategory.isEmpty()) {
                selected.category = newCategory;

                if (currentFileMode == 1) {
                    if (!financeBook.uniqueCategory.contains(newCategory)) {
                        financeBook.uniqueCategory.add(newCategory);
                    }
                }
                else {
                    if (!financeBook.uniqueCategoryFromImport.contains(newCategory)) {
                        financeBook.uniqueCategoryFromImport.add(newCategory);
                    }
                }
                
                System.out.println("Category successfully updated");
            } else {
                System.out.println("Category cannot be empty. Please try again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while(newCategory.isEmpty());

        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu                                                                                            =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }

    public static void editFinanceRecordType(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        String newType;
        boolean newTypeVerif;

        do {
            newTypeVerif = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                           EDIT FINANCE RECORD TYPE                                              =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record type before: " + selected.type);
            System.out.println("===================================================================================================================");
            System.out.print("Input new type (income/outcome): "); newType = scanner.nextLine().trim();
            System.out.println("===================================================================================================================");

            if(!newType.isEmpty()){
                if(newType.equalsIgnoreCase("income") || newType.equalsIgnoreCase("outcome")) {
                    selected.type = newType;
                    newTypeVerif = true;
                    System.out.println("Type successfully updated");
                }
                else {
                    System.out.println("Type must between income or outcome");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        System.out.println("\nInterrupted pause");
                    }
                    consoleUtils.clearScreen();
                }
            }
            else {
                System.out.println("Type cannot be empty. Please try again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while(newType.isEmpty() || !newTypeVerif);

        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu/Cancel                                                                                     =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }

    public static void editFinanceRecordAmount(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        long newAmount;
        boolean newAmountVerif;

        do {
            newAmountVerif = false;

            System.out.println("===================================================================================================================");
            System.out.println("=                                          EDIT FINANCE RECORD AMOUNT                                             =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record amount before: " + selected.amount);
            System.out.println("===================================================================================================================");
            System.out.print("Input new amount : "); newAmount = scanner.nextLong(); scanner.nextLine();
            System.out.println("===================================================================================================================");

            if(newAmount >= 0){
                selected.amount = newAmount;
                newAmountVerif = true;
                System.out.println("Amount successfully updated");
            }
            else {
                System.out.println("Amount cannot be negative. Please try again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while(!newAmountVerif);

        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu/Cancel                                                                                     =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }

    public static void editFinanceRecordDescription(Transaction selected, String selectedDate, int currentFileMode) {
        int verification;

        String newDescription;

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                        EDIT FINANCE RECORD DESCRIPTION                                          =");
            System.out.println("===================================================================================================================");
            System.out.println("Finance record description before: " + selected.description);
            System.out.println("===================================================================================================================");
            System.out.print("Input new description : "); newDescription = scanner.nextLine().trim();
            System.out.println("===================================================================================================================");

            if(!newDescription.isEmpty()){
                selected.description = newDescription;
                System.out.println("Name successfully updated");
            }
            else {
                System.out.println("Name cannot be empty. Please try again");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
            }
        } while(newDescription.isEmpty());

        do {
            System.out.println("\n===================================================================================================================");
            System.out.println("= 1. Edit another option                                                                                          =");
            System.out.println("= 2. Edit another transaction                                                                                     =");
            System.out.println("= 3. Back to edit menu                                                                                            =");
            System.out.println("= 4. Back to main menu/Cancel                                                                                     =");
            System.out.println("===================================================================================================================");
                
            System.out.print("\nInput the number of menu you want to use: ");  verification = scanner.nextInt(); scanner.nextLine();

            switch(verification) {
                case 1: consoleUtils.clearScreen(); editFinanceRecordEditMenu(selected, selectedDate, currentFileMode);
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); financeBook.editFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeBook.menu(); break;
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
        } while (verification < 1 || verification > 4);
    }
}