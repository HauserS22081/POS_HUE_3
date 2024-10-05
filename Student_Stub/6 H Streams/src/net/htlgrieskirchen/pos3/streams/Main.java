package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        int[] rdmNumbers = createIntArr(10000, 100);
        // .Streams.average()

        String[] rdmStrings = createStringArr(10, 10);
    }

    private static int[] createIntArr(int number, int upperBound) {
        int[] numbers = new int[number];

        Arrays.stream(numbers).forEach(n -> n = random.nextInt(upperBound));

        return numbers;
    }

    private static String[] createStringArr(int number, int length) {
        String[] strings = new String[number];

        Arrays.stream(strings).forEach(s -> {
            for (int i = 0; i < length; i++) {
                s += (char) (int) 'a' + random.nextInt(25);
            }
        });

        return strings;
    }
}
