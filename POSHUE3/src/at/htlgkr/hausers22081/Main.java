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
    }

    public static List<Weapon> sortDescendingAfterDamage(List<Weapon> weapons) {
        return weapons.stream().sorted((w1, w2) -> w2.getDamage() - w1.getDamage()).toList();
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
