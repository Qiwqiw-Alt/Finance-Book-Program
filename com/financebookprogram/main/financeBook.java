// javac com/financebookprogram/main/*.java com/financebookprogram/programs/*.java com/financebookprogram/models/*.java com/financebookprogram/utils/*.java
// java com.financebookprogram.main.financeBook
package com.financebookprogram.main;

import com.financebookprogram.models.*;
import com.financebookprogram.programs.*;
import com.financebookprogram.utils.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;


public class financeBook {
    static Scanner scanner = new Scanner(System.in);

    public static Map<String, LinkedList<Transaction>> transactionsByDate = new HashMap<>();
    public static Set<String> uniqueCategory = new HashSet<>();
    public static Map<String, Long> totalIncomePerCategory = new HashMap<>();
    public static Map<String, Long> totalOutcomePerCategory = new HashMap<>();

    public static Map<String, LinkedList<Transaction>> transactionsByDateFromImport = new HashMap<>();
    public static Set<String> uniqueCategoryFromImport = new HashSet<>();
    public static Map<String, Long> totalIncomePerCategoryFromImport = new HashMap<>();
    public static Map<String, Long> totalOutcomePerCategoryFromImport = new HashMap<>();

    static int currentFileMode = 1;

    public static void main(String[] args) {
        consoleUtils.welcome();

        int fileChoice;
        do {
            System.out.println("=                                                 CHOOSE FILE                                                     =");
            System.out.println("===================================================================================================================");
            System.out.println("= 1. New file                                                                                                     =");
            System.out.println("= 2. Imported file                                                                                                =");
            System.out.println("===================================================================================================================");

            System.out.print("\nInput the number of file you want to use: "); fileChoice = scanner.nextInt(); scanner.nextLine();

            if (fileChoice == 1) {
                currentFileMode = fileChoice;
                break;
            } 
            else if (fileChoice == 2) {
                currentFileMode = fileChoice;
                if (transactionsByDateFromImport.isEmpty()) {
                    consoleUtils.clearScreen(); 
                    importCSV.importFromCSV();
                }
                break;
            }
            else {
                System.out.println("\nThe number of menu is not found, please input the correct number");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("\nInterrupted pause");
                }
                consoleUtils.clearScreen();
                }
        } while(fileChoice  < 1 || fileChoice > 2);

        consoleUtils.clearScreen();
        menu();
    }

    public static void menu() {
        int choice;

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                           FINANCE BOOK MENU                                                     =");
            consoleUtils.fileChoiceInfo(currentFileMode);
            System.out.println("= 1. Add finance record                                                                                           =");
            System.out.println("= 2. Edit finance record                                                                                          =");
            System.out.println("= 3. Show finance record                                                                                          =");
            System.out.println("= 4. View income & expense summary                                                                                =");
            System.out.println("= 5. Export data to file (csv)                                                                                    =");
            System.out.println("= 6. Import data from file (csv)                                                                                  =");
            System.out.println("= 7. Exit                                                                                                         =");
            System.out.println("===================================================================================================================");

            System.out.print("\nInput the number of menu you want to use: "); choice = scanner.nextInt(); scanner.nextLine();


            switch(choice) {
                case 1: consoleUtils.clearScreen(); addFR.addFinanceRecordDate(currentFileMode); break;
                case 2: consoleUtils.clearScreen(); editFinanceRecord(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); showFinanceRecord(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); financeSum.financeSummary(currentFileMode); break;
                case 5: consoleUtils.clearScreen(); exportCSV.exportToCSV(currentFileMode); break;
                case 6: consoleUtils.clearScreen(); importCSV.importFromCSV(); break;
                case 7: consoleUtils.clearScreen(); main(null); break;
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
        } while (choice < 1 || choice > 6);
    }

    public static void editFinanceRecord(int currentFileMode) {
        int choice;
        if(currentFileMode == 1) {
            if (transactionsByDate.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                menu();
            }
        }
        else {
            if (transactionsByDateFromImport.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                menu();
            }
        }

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                             EDIT FINANCE RECORD                                                 =");
            System.out.println("===================================================================================================================");
            System.out.println("= 1. Delete finance record                                                                                        =");
            System.out.println("= 2. Edit finance record                                                                                          =");
            System.out.println("= 3. Back to main menu                                                                                            =");
            System.out.println("===================================================================================================================");

            System.out.print("\nInput the number of menu you want to use: "); choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1: consoleUtils.clearScreen(); deleteFR.editFinanceRecordDelete(currentFileMode); break;
                case 2: consoleUtils.clearScreen(); editFR.editFinanceRecordEdit(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); menu(); break;
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
        } while (choice < 1 || choice > 3);
    }

    public static void showFinanceRecord(int currentFileMode) { 
        int choice;

        if(currentFileMode == 1) {
            if (transactionsByDate.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                menu();
            }
        }
        else {
            if (transactionsByDateFromImport.isEmpty()) {
                System.out.println("No transaction records found. Please add a financial record or import a CSV file");
                System.out.println("You will be redirected to the main menu");
                try {
                    TimeUnit.SECONDS.sleep(2);
                } catch (InterruptedException e) {
                    System.out.println("Pause interrupted");
                }
                consoleUtils.clearScreen();
                menu();
            }
        }

        do {
            System.out.println("===================================================================================================================");
            System.out.println("=                                             SHOW FINANCE RECORD                                                 =");
            System.out.println("===================================================================================================================");
            System.out.println("= 1. Finance record by date                                                                                       =");
            System.out.println("= 2. Finance record by month                                                                                      =");
            System.out.println("= 3. Finance record by category                                                                                   =");
            System.out.println("= 4. All finance record                                                                                           =");
            System.out.println("= 5. Back to main menu                                                                                            =");
            System.out.println("===================================================================================================================");

            System.out.print("\nInput the number of menu you want to use: "); choice = scanner.nextInt(); scanner.nextLine();

            switch(choice) {
                case 1: consoleUtils.clearScreen(); showFRDate.showFinanceRecordbyDate(currentFileMode); break;
                case 2: consoleUtils.clearScreen(); showFRMonth.showFinanceRecordbyMonth(currentFileMode); break;
                case 3: consoleUtils.clearScreen(); showFRCat.showFinanceRecordbyCategory(currentFileMode); break;
                case 4: consoleUtils.clearScreen(); showFRAll.allFinanceRecord(currentFileMode); break;
                case 5: consoleUtils.clearScreen(); menu(); break;
                default:
                    System.out.println("The number of menu is not found, please input the correct number");
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        System.out.println("Pause interrupted");
                    }
                    consoleUtils.clearScreen();
                    break;
            }
        } while (choice < 1 || choice > 5);
    }
}