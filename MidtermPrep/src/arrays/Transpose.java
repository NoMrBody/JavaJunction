package arrays;

public class Transpose {
    static int[][] transpose(int[][] arr) {
        int rows = arr.length;
        int cols = arr[0].length;
        int[][] transposed = new int[cols][rows];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                transposed[j][i] = arr[i][j];
            }
        }
        return transposed;
    }
}
