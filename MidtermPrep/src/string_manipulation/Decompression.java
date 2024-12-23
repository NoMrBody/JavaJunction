package string_manipulation;

public class Decompression {
    static String decompress(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);

            if (Character.isLetter(ch)) {
                int count = 0;
                int j = i + 1;

                while (j < input.length() && Character.isDigit(input.charAt(j))) {
                    count = count * 10 + (input.charAt(j) - '0');
                    j++;
                }
                for (int k = 0; k < count; k++) {
                    result.append(ch);
                }
                i = j - 1;
            }
        }
        return result.toString();
    }


    static String decompressRecursively(String input) {
        if (input.isEmpty()) {
            return "";
        }
        char letter = input.charAt(0);
        int i = 1;
        int count = 0;

        while (i < input.length() && Character.isDigit(input.charAt(i))) {

            count = count * 10 + (input.charAt(i) - '0');
            i++;
        }
        String result = "";
        for (int j = 0; j < count; j++) {
            result += letter;
        }
        return result + decompress(input.substring(i));
    }

    static String decompressAcc(String input, String acc) {
        if (input.isEmpty())
            return acc;
        char letter = input.charAt(0); // assume that letter stands at always 0 index
        int count = 0;
        int i = 1;
        while (i < input.length() && Character.isDigit(input.charAt(i))) {
            count = count * 10 + (input.charAt(i) - '0');
            i += 1;
        }
        for (int j = 0; j < count; j++) {
            acc += letter;
        }
        return decompressAcc(input.substring(i), acc);
    }

    static String decompressAcc(String input) {
        return decompressAcc(input, "");
    }

}
