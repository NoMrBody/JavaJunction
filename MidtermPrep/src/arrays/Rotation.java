package arrays;

// cyclic rotation of array (right shift)
// [1,2,3,4,5] 2 -> [4,5,1,2,3]
public class Rotation {
    public static void rotate(int[] arr, int amount) {
        if (arr.length == 1 || amount % arr.length == 0)
            return;
        int shifting = amount % arr.length;
//        right shift
        reverseAux(arr, 0, arr.length - 1 - shifting);
        reverseAux(arr, arr.length - shifting, arr.length - 1);
        reverseAux(arr, 0, arr.length - 1);

//        For shifting left:
//        reverseAux(arr, 0, shifting - 1);
//        reverseAux(arr, shifting, arr.length - 1);
//        reverseAux(arr, 0, arr.length - 1);

    }

    static void reverseAux(int[] arr, int start, int end) {
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
