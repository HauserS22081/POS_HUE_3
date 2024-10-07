package at.htlgkr.hausers22081;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String CSVPATH = "weapons.csv";

    public static void main(String[] args) {
        List<Weapon> weapons = new ArrayList<>();

        weapons = readInCsv();

        weapons = sortDescendingAfterDamage(weapons);

        Printable printableLine = weaponsList -> weaponsList.forEach(w -> System.out.printf("%s: %s | %s - damge: %d - speed: %d - strength: %d - value: %d\n", w.getName(), w.getDamageType(), w.getCombatType(), w.getDamage(), w.getSpeed(), w.getStrength(), w.getValue()));
        printableLine.print(weapons);

        System.out.println("\n");

        printTable(weapons);
    }

    private static void printTable(List<Weapon> weapons) {

        // get longest length

        int[] tableLength = new int[7];
        String[][] weaponsInArray = new String[tableLength.length + 1][weapons.size()];

        weaponsInArray[0][0] = "Name";
        weaponsInArray[1][0] = "CombatType";
        weaponsInArray[2][0] = "DamageType";
        weaponsInArray[3][0] = "Damage";
        weaponsInArray[4][0] = "Speed";
        weaponsInArray[5][0] = "Strength";
        weaponsInArray[6][0] = "Value";

        for (int i = 1; i < weapons.size(); i++) {
            weaponsInArray[0][i] = weapons.get(i).getName();
            weaponsInArray[1][i] = String.valueOf(weapons.get(i).getCombatType());
            weaponsInArray[2][i] = String.valueOf(weapons.get(i).getDamageType());
            weaponsInArray[3][i] = String.valueOf(weapons.get(i).getDamage());
            weaponsInArray[4][i] = String.valueOf(weapons.get(i).getSpeed());
            weaponsInArray[5][i] = String.valueOf(weapons.get(i).getStrength());
            weaponsInArray[6][i] = String.valueOf(weapons.get(i).getValue());
        }

        for (int i = 0; i < tableLength.length; i++) {
            tableLength[i] = Arrays.stream(weaponsInArray[i])
                    .max(Comparator.comparingInt(field -> field.length()))
                    .get().length();
        }


        printLineBetween(tableLength);

        printHeader(tableLength);

        printLineBetween(tableLength);

//        Printable printableTable = weaponsList -> {
//            weaponsList.forEach(w -> System.out.printf("%17s|%11s|%11s|%7d|%6d|%9d\n", w.getName(), w.getCombatType(), w.getDamageType(), w.getDamage(), w.getSpeed(), w.getStrength(), w.getValue()));
//        };
        Printable printableTable = weaponsList -> {
            weaponsList.forEach(w -> {
                System.out.print("| " + w.getName());
                printSpace(tableLength[0] - w.getName().length());

                System.out.print("| " + w.getCombatType());
                printSpace(tableLength[1] - String.valueOf(w.getCombatType()).length());

                System.out.print("| " + w.getDamageType());
                printSpace(tableLength[2] - String.valueOf(w.getDamageType()).length());

                System.out.print("| " + w.getDamage());
                printSpace(tableLength[3] - String.valueOf(w.getDamage()).length());

                System.out.print("| " + w.getSpeed());
                printSpace(tableLength[4] - String.valueOf(w.getSpeed()).length());

                System.out.print("| " + w.getStrength());
                printSpace(tableLength[5] - String.valueOf(w.getStrength()).length());

                System.out.print("| " + w.getSpeed());
                printSpace(tableLength[6] - String.valueOf(w.getSpeed()).length());

                System.out.println("|");

                printLineBetween(tableLength);
            });
        };

        printableTable.print(weapons);
    }

    private static void printHeader(int[] tableLength) {
        System.out.print("| " + "Name");
        printSpace(tableLength[0] - "Name".length());

        System.out.print("| " + "CombatType");
        printSpace(tableLength[1] - "CombatType".length());

        System.out.print("| " + "DamageType");
        printSpace(tableLength[2] - "DamageType".length());

        System.out.print("| " + "Damage");
        printSpace(tableLength[3] - "Damage".length());

        System.out.print("| " + "Speed");
        printSpace(tableLength[4] - "Speed".length());

        System.out.print("| " + "Strength");
        printSpace(tableLength[5] - "Strength".length());

        System.out.print("| " + "Value");
        printSpace(tableLength[6] - "Value".length());

        System.out.println("|");
    }

    private static void printLineBetween(int[] tableLength) {
        System.out.print("+");
        for (int i = 0; i < tableLength.length; i++) {

            for (int j = 0; j < tableLength[i] + 2; j++) {
                System.out.print("-");
            }
            System.out.print("+");
        }
        System.out.println();
    }

    private static void printSpace(int length) {
        for (int j = 0; j <= length; j++) {
            System.out.print(" ");
        }
    }

    public static List<Weapon> sortDescendingAfterDamage(List<Weapon> weapons) {
        return weapons.stream().sorted((w1, w2) -> w2.getDamage() - w1.getDamage()).toList();
    }

    public static List<Weapon> sortCTypeDTypeName(List<Weapon> weapons) {
        return weapons.stream().sorted(Main::compareCTypeDTypeName).toList();
    }

    private static int compareCTypeDTypeName(Weapon w1, Weapon w2) {

        int compare = w1.getCombatType().toString().compareTo(w2.getCombatType().toString());
        if (compare != 0) return compare;

        compare = w1.getDamageType().toString().compareTo(w2.getDamageType().toString());
        if(compare != 0) return compare;

        return w1.getName().compareTo(w2.getName());
    }

    private static List<Weapon> readInCsv() {
        List<Weapon> weapons = new ArrayList<>();
        try {
            weapons = Files.lines(Path.of(CSVPATH))
                    .skip(1)
                    .map(s -> s.split(";"))
                    .map(s -> new Weapon(
                            s[0],
                            CombatType.valueOf(s[1]),
                            DamageType.valueOf(s[2]),
                            Integer.parseInt(s[3]),
                            Integer.parseInt(s[4]),
                            Integer.parseInt(s[5]),
                            Integer.parseInt(s[6]
                            )))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Error in readInCsv: " + e.getMessage());
        }

        return weapons;
    }
}
