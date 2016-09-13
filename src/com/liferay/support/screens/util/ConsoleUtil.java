package com.liferay.support.screens.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by javdaniel on 13/09/16.
 */
public class ConsoleUtil {

    private static BufferedReader input;

    public static String askForAnswer(String question) throws IOException {

        if (input == null) {
            initializeInput();
        }

        System.out.print(question + " ");

        return input.readLine();
    }

    private static void initializeInput() {
        input = new BufferedReader(new InputStreamReader(System.in));
    }
}
