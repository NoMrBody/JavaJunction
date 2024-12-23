package sequences;

// iterative generation of fibonacci sequence
public class Fibonacci {
    static void fibo(int n) {
        int first = 0;
        int second = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
    }
}
