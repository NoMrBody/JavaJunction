package string_manipulation;

public class Palindrome {
    static boolean isPalindromeRec(String input, int s, int e) {
        if (s >= e)
            return true;
        if (input.charAt(s) != input.charAt(e))
            return false;
        return isPalindromeRec(input, s + 1, e + 1);
    }
}
