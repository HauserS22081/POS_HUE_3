package at.htlgkr.hausers22081;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    private static final String CSVPATH = "weapons.csv";

    public static void main(String[] args) {
        List<Weapon> weapons = new ArrayList<>();

        weapons = readInCsv();

        sortDescendingAfterDamage(weapons);

        Printable printable = weaponsList -> weaponsList.forEach(w -> String.format("%s: %s | %s - damge: %d - speed: %d - strength: %d - value: %d", w.getName(), w.getDamageType(), w.getCombatType(), w.getDamage(), w.getSpeed(), w.getStrength(), w.getValue()));;

        printable.print(weapons);
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
