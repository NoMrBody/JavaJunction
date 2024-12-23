package sortings;

public class Insertion {
    //    {4, 7, 2, 3, 6, 11, 21, 0, 10, -2}
    static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int j = i - 1;
            int k = arr[i];
            while (j >= 0 && arr[j] > k) {
                // SWAP
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = k;
        }
    }
}
