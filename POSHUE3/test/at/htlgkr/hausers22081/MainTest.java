package at.htlgkr.hausers22081;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static at.htlgkr.hausers22081.Main.sortCTypeDTypeName;
import static at.htlgkr.hausers22081.Main.sortDescendingAfterDamage;

class MainTest {

    private static List<Weapon> weapons;
    private static Weapon w1;
    private static Weapon w2;
    private static Weapon w3;
    private static Weapon w4;

    static{
        w1 = new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250);
        w2 = new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500);
        w3 = new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500);
        w4 = new Weapon("Carsomyr", CombatType.MELEE, DamageType.SLASHING, 17, 5, 14, 20000);

        weapons = sortDescendingAfterDamage(List.of(w1, w2, w3, w4));

    }

    @Test
    void testSortDescendingAfterDamage() {
        List<Weapon> sortedWeapons = sortDescendingAfterDamage(weapons);
        Assertions.assertArrayEquals(List.of(w4, w3, w1, w2).toArray(), sortedWeapons.toArray());
    }

    @Test
    void testSortCTypeDTypeName() {
        List<Weapon> sortedWeapons = sortCTypeDTypeName(weapons);
        Assertions.assertArrayEquals(List.of(w3, w4, w1, w2).toArray(), sortedWeapons.toArray());

    }
}