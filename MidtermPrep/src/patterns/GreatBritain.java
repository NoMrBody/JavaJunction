package patterns;

/* 
1 0 0 1 0 0 1
0 1 0 1 0 1 0
0 0 1 1 1 0 0
1 1 1 1 1 1 1
0 0 1 1 1 0 0
0 1 0 1 0 1 0
1 0 0 1 0 0 1
 */
public class GreatBritain {
    static int[][] pattern3(int n) {
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n / 2 || i == n / 2 || i == j || i == n - 1 - j)
                    arr[i][j] = 1;
            }
        }
        return arr;
    }
}
