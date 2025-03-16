package org.example.util;

import java.util.Scanner;

public class InputUtil {
    public static final Scanner sc=new Scanner(System.in);

    public static <T> T getInput(String text, Class<T> clazz) {

        System.out.print(text);

        while (true) {
            String input = sc.nextLine();
            try {
                if (clazz == Integer.class) {
                    return clazz.cast(Integer.parseInt(input));
                } else if (clazz == Double.class) {
                    return clazz.cast(Double.parseDouble(input));
                } else if (clazz == Boolean.class) {
                    return clazz.cast(Boolean.parseBoolean(input));
                }else {
                    return clazz.cast(input);
                }
            }
            catch (NumberFormatException e) {
                System.out.println( "Invalid ,try again(input integer)..");
            }
        }
    }
}
