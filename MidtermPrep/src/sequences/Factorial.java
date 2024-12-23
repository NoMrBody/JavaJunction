package sequences;

public class Factorial {
    static void factorial(int n) {
        int sum = 1;
        for (int i = 5; i > 1; i--) {
            sum *= i;
        }
        System.out.println(sum);
    }
}
