package com.example.patientmgt.utils;

import lombok.experimental.UtilityClass;

import java.util.Scanner;

@UtilityClass
public class AppUtils {
    public void waitUntilSystemInput(final String exitCode) {
        try (final Scanner scanner = new Scanner(System.in)) {
            while (scanner.hasNextLine()) {
                final String nextLine = scanner.nextLine();
                if (nextLine.contains(exitCode)) {
                    return;
                }
            }
        }
    }

}
