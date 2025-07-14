package com.financebookprogram.utils;

import java.io.IOException;

public class consoleUtils {
    public static void welcome() {
        System.out.println("===================================================================================================================");
        System.out.println("=                         _____ _                            ____              _                                  =");
        System.out.println("=                        |  ___(_)_ __   __ _ _ __   ___ ___| __ )  ___   ___ | | __                              =");
        System.out.println("=                        | |_  | | '_ \\ / _` | '_ \\ / __/ _ \\  _ \\ / _ \\ / _ \\| |/ /                              =");
        System.out.println("=                        |  _| | | | | | (_| | | | | (_|  __/ |_) | (_) | (_) |   <                               =");
        System.out.println("=                        |_|   |_|_| |_|\\__,_|_| |_|\\___\\___|____/ \\___/ \\___/|_|\\_\\                              =");
        System.out.println("===================================================================================================================");
    }

    public static void fileChoiceInfo(int fileChoice) {
        String fileType = (fileChoice == 1) ? "new file" : "import file";
        System.out.println("===================================================================================================================");
        System.out.println("File type: " + fileType);
        System.out.println("===================================================================================================================");
    }


    public static String capitalization(String input) {
        if (input == null || input.isEmpty()) return input;
        return input.substring(0, 1).toUpperCase() + input.substring(1).toLowerCase();
    }

    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException ex) {
            for (int i = 0; i < 50; ++i) System.out.println();
        }
    }
}
