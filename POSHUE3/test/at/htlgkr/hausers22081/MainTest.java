package at.htlgkr.hausers22081;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static at.htlgkr.hausers22081.Main.sortDescendingAfterDamage;
import static org.junit.jupiter.api.Assertions.*;
class MainTest {

    @Test
    void testSortDescendingAfterDamage() {
        Weapon w1 = new Weapon("Varscona", CombatType.MELEE, DamageType.SLASHING, 11, 3, 5, 4250);
        Weapon w2 = new Weapon("Tuigan Bow", CombatType.RANGED, DamageType.MISSILE, 1, 5, 6, 3500);
        Weapon w3 = new Weapon("Crom Faeyr", CombatType.MELEE, DamageType.BLUNT, 16, 1, 25, 15500);

        List<Weapon> weapons = sortDescendingAfterDamage(List.of(w1, w2, w3));
        List<Weapon> sortedWeapons = List.of(w3, w1, w2);

        Assertions.assertArrayEquals(sortedWeapons.toArray(), weapons.toArray());
    }
}