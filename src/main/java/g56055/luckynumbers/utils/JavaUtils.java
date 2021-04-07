package g56055.luckynumbers.utils;

import java.util.Scanner;

/**
 * This class aims to provide utility methods to projects
 *
 * @author Duran Rehan g56055,
 */
public class JavaUtils {

    /**
     * Create a random number between a minimum and maximum
     * @param min minimum value limit
     * @param max maximum value limit
     * @return a number between a min and max
     */
    public static int rdmNumber(int min, int max) {
        int rdm = min + (int) (Math.random() * max);
        return rdm;
    }

    /**
     * Robustly read an integer entered on keyboard
     *
     * @return the integer entered on the keyboard
     */
    public static int reading_int_Robust() {
        Scanner kbd = new Scanner(System.in);
        while (!kbd.hasNextInt()) {
            System.out.println("Number is not a Integer : ");
            kbd.next();
        }
        return kbd.nextInt();
    }
}
