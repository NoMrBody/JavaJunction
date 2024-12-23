package patterns;

public class X {
    static int[][] pattern1(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j)
                    arr[i][j] = 1;
                if (i == n - 1 - j) {
                    arr[i][j] = 1;
                }
            }
        }
        return arr;
    }
}
