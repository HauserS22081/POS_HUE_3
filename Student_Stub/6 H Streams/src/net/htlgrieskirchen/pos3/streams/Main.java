package net.htlgrieskirchen.pos3.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {
    private static Random random = new Random();

    public static void main(String[] args) {
        // Bsp 2:
        secondExample();


        // Bsp 3:
        thirdExample();


        // Bsp 4:
        fourthExample();


        // Bsp 5:
        fifthExample();
    }

    private static void fifthExample() {
        // Bsp 5:
        System.out.println("\n\nBsp 5:\n");

        final List<String> names = Arrays.asList("Tim", "Andi", "Michael");

        final Mapper<String, Integer> intMapper = String::length;
        System.out.println(intMapper.mapAll(names));

        final Mapper<String, String> stringMapper = str -> ">> " + str.toUpperCase() + " << ";
        final List<String> uppercaseNames = stringMapper.mapAll(names);
        System.out.println(uppercaseNames);
    }

    private static void fourthExample() {
        // Bsp 4:
        System.out.println("\n\nBsp 4:");

        final IntPredicate isEven = number -> (number % 2 == 0);


        final int result = IntStream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .filter(isEven.negate())
                .map(number -> (int) Math.pow(number, 2))
//                .reduce(0, (n1, n2) -> n1 + n2);
                .reduce(0, Integer::sum);

        System.out.println("\nSum of quadrats uneven numbers 1 - 10: \n" + result);
    }

    private static void thirdExample() {
        // Bsp 3:
        System.out.println("\n\nBsp 3:");

        final Predicate<Integer> isEven = number -> (number % 2 == 0);
        final Predicate<Integer> isPositive = number -> (number > 0);
//        final Predicate<Integer> isNull = number -> (number == null);
        final Predicate<Integer> isNull = Objects::isNull;
        final Predicate<Integer> is0 = number -> (number == 0);
        final Predicate<String> isShortWord = word -> (word.length() < 4);

        List<Integer> numbers = Arrays.asList(-10, -7, 0, 1, 4);
        System.out.println("\nNumbers: ");
        numbers.forEach(System.out::println);

        System.out.println("\nIs postive and even: ");
        isPositivAndEven(isPositive, isEven, numbers).forEach(System.out::println);

        System.out.println("\nIs positive and uneven: ");
        isPositiveAndUneven(isPositive, isEven, numbers).forEach(System.out::println);

        List<Integer> numbersWithNull = Arrays.asList(-10, -7, 0, 1, 4, null);

        System.out.println("\nIs Null: ");
        numbersWithNull.stream()
                .filter(isNull)
                .forEach(System.out::println);

        System.out.println("\nIs 0: ");
        numbers.stream()
                .filter(is0)
                .forEach(System.out::println);

        List<String> strings = Arrays.asList("abcdef", "xyz", "awwrwerwer", "a");
        System.out.println("\nStrings: ");
        strings.forEach(System.out::println);

        System.out.println("\nIs short string");
        strings.stream()
                .filter(isShortWord)
                .forEach(System.out::println);
    }

    private static List<Integer> isPositivAndEven(Predicate<Integer> isPositive, Predicate<Integer> isEven, List<Integer> numbers) {
        return numbers.stream()
                .filter(isPositive.and(isEven))
                .collect(Collectors.toList());
    }

    private static List<Integer> isPositiveAndUneven(Predicate<Integer> isPositive, Predicate<Integer> isEven, List<Integer> numbers) {
        return numbers.stream()
                .filter(isPositive.and(isEven).negate())
                .collect(Collectors.toList());
    }

    private static void secondExample() {
        System.out.println("Bsp 2:\n");

        Streams streams = new Streams();

        int[] rdmNumbers = createIntArr(10000, 100);
        System.out.println("\nAverage: \n" + Streams.average(rdmNumbers));

        String[] rdmStrings = createStringArr(10, 10);
        System.out.println("\nRandom Strings: ");
        streams.upperCase(rdmStrings).forEach(System.out::println);


        List<Weapon> weapons = readInCSV("weapons.csv");
        System.out.println("\nWeapons: ");
        weapons.forEach(System.out::println);

        System.out.println("\nWeapon with lowest damage: \n" + streams.findWeaponWithLowestDamage(weapons));

        System.out.println("\nWeapon with highest strength: \n" + streams.findWeaponWithHighestStrength(weapons));

        System.out.println("\nWeapons with DamageType missle: ");
        streams.collectMissileWeapons(weapons).forEach(System.out::println);

        System.out.println("\nWeapon with longest name: \n" + streams.findWeaponWithLongestName(weapons));

        System.out.println("\nName of weapons: ");
        streams.toNameList(weapons).forEach(System.out::println);

        System.out.println("\nSpeed of weapons: ");
        Arrays.stream(streams.toSpeedArray(weapons)).forEach(System.out::println);

        System.out.println("\nSum of values: \n" + streams.sumUpValues(weapons));

        System.out.println("\nSum of hashCodes: \n" + streams.sumUpHashCodes(weapons));

        System.out.println("\nWeapons without duplicates: ");
        streams.removeDuplicates(weapons).forEach(System.out::println);

        System.out.println("\nWeapons with 10% increased value: ");
        streams.increaseValuesByTenPercent(weapons);
        weapons.forEach(System.out::println);
    }

    private static List<Weapon> readInCSV(String filename) {
        List<Weapon> weapons = null;
        try {
            weapons = Files.lines(Paths.get(filename))
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                            s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6])
                            ))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error in readInCsv: " + e.getMessage());
        }
        return weapons;
    }

    private static int[] createIntArr(int number, int upperBound) {
        return IntStream.range(1, number - 1)
                .map(n -> new Random().nextInt(upperBound))
                .toArray();
    }

    private static String[] createStringArr(int number, int length) {

        String[] strings = new String[number];
        strings = Arrays.stream(strings)
                .map(s -> {
                    s = "";
                    for (int i = 0; i < length; i++) {
                        s += (char) ('a' + new Random().nextInt(26));
                    }
                    return s;
                })
                .toArray(String[]::new);

        return strings;
    }
}
