package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Streams {

    public static double average(int[] numbers) {
        return (double) Arrays.stream(numbers).sum() / numbers.length;
    }
    
    public List<String> upperCase(String[] strings) {
        return Arrays.stream(strings)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
    }

    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        if(weapons == null || weapons.isEmpty()) return null;

//        return weapons.stream().min((w1, w2) -> w1.getDamage() - w2.getDamage()).get();
        return weapons.stream()
                .min(Comparator.comparingInt(Weapon::getDamage))
                .get();
    }

    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        if(weapons == null || weapons.isEmpty()) return null;

//        return weapons.stream().max((w1, w2) -> w1.getMinStrength() - w2.getMinStrength()).get();
        return weapons.stream()
                .max(Comparator.comparingInt(Weapon::getMinStrength))
                .get();
    }

    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        return weapons.stream()
                .filter(w -> w.getDamageType() == DamageType.MISSILE)
                .collect(Collectors.toList());
    }

    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        if(weapons == null || weapons.isEmpty()) return null;

//        return weapons.stream().max((w1, w2) -> w1.getName().length() - w2.getName().length()).get();

        return weapons.stream()
                .max(Comparator.comparingInt(w -> w.getName().length()))
                .get();
    }

    public List<String> toNameList(List<Weapon> weapons) {
//        return weapons.stream().map(w -> w.getName()).collect(Collectors.toList());
        return weapons.stream()
                .map(Weapon::getName)
                .collect(Collectors.toList());
    }

    public int[] toSpeedArray(List<Weapon> weapons) {
//        return weapons.stream().mapToInt(w -> w.getSpeed()).toArray();
        return weapons.stream()
                .mapToInt(Weapon::getSpeed)
                .toArray();
    }

    public int sumUpValues(List<Weapon> weapons) {
        return weapons.stream()
                .mapToInt(Weapon::getValue)
                .sum();
    }

    public long sumUpHashCodes(List<Weapon> weapons) {
        return weapons.stream()
                .mapToLong(Weapon::hashCode)
                .sum();
    }

    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        return weapons.stream()
                .distinct()
                .collect(Collectors.toList());
    }

    public void increaseValuesByTenPercent(List<Weapon> weapons) {
       weapons.forEach(w -> w.setValue((int) (w.getValue() * 1.1)));
    }
}
