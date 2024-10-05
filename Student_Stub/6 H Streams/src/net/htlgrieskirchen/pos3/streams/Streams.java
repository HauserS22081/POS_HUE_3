package net.htlgrieskirchen.pos3.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class Streams {

    public static double average(int[] numbers) {
        return (double) Arrays.stream(numbers).sum() / numbers.length;
    }
    
    public List<String> upperCase(String[] strings) {
        Arrays.stream(strings).forEach(String::toUpperCase).collect;
    }

    public Weapon findWeaponWithLowestDamage(List<Weapon> weapons) {
        //implement this
        return null;
    }

    public Weapon findWeaponWithHighestStrength(List<Weapon> weapons) {
        //implement this
        return null;
    }

    public List<Weapon> collectMissileWeapons(List<Weapon> weapons) {
        //implement this
        return weapons;
    }

    public Weapon findWeaponWithLongestName(List<Weapon> weapons) {
        //implement this
        return null;
    }

    public List<String> toNameList(List<Weapon> weapons) {
        //implement this
        return List.of();
    }

    public int[] toSpeedArray(List<Weapon> weapons) {
        //implement this
        return new int[0];
    }

    public int sumUpValues(List<Weapon> weapons) {
        //implement this
        return 0;
    }

    public long sumUpHashCodes(List<Weapon> weapons) {
        //implement this
        return 0;
    }

    public List<Weapon> removeDuplicates(List<Weapon> weapons) {
        //implement this
        return weapons;
    }

    public void increaseValuesByTenPercent(List<Weapon> weapons) {
       //implement this
    }
}
