package g56055.luckynumbers.utils;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * This class aims to provide utility methods to projects
 *
 * @author Duran Rehan g56055,
 */
public class JavaUtils {

    /**
     * Create a random number between a minimum and maximum
     *
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

    /**
     * Shuffle a list 
     * @param list list to be shuffled
     */
    public static void shuffle(List list) {
        int index;
        Random random = new Random();
        for (int i = list.size() - 1; i > 0; i--) {
            index = random.nextInt(i + 1);
            swap(list, i, index);
        }
    }

    /**
     * Swap two elements of a list
     * @param list list on which to shuffle
     * @param pos1 the position of the first to swap
     * @param pos2 the position of the second to swap
     */
    public static void swap(List list, int pos1, int pos2) {
        if (pos1 < 0 || pos2 < 0) {
            throw new IllegalArgumentException(" Position non entière : "
                    + pos1 + " " + pos2);
        }
        var savedValue = list.get(pos1);
        list.add(pos1,list.get(pos2));
        list.remove(pos1+1);
        list.add(pos2, savedValue);
        list.remove(pos2+1);
    }
}
