package sortings;

public class Bubble {
    static void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            boolean swapped = false; // adding the flag
            for (int j = 0; j < arr.length - 1 - i; j++) { // subtracting i avoids making unnecessary operations
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true; // the swap has occurred
                }
            }
            // if no swaps happened => the array is sorted
            if (!swapped)
                break;
        }
    }
}
