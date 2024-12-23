package string_manipulation;

import java.util.HashSet;
import java.util.Set;

public class Anagram {
    static boolean isAnagram(String inp1, String inp2) {
        if (inp1.length() != inp2.length())
            return false;

        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();

        for (char c : inp1.toCharArray()) {
            set1.add(c);
        }
        for (char c : inp2.toCharArray()) {
            set2.add(c);
        }
        return set1 == set2;
    }
}
