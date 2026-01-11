import java.util.*;

class Next_permutation {

    void nextPermutation(int[] arr) {
        int n = arr.length;
        int pivot = -1;

        // Find the pivot
        for (int i = n - 2; i >= 0; i--) {
            if (arr[i] < arr[i + 1]) {
                pivot = i;
                break;
            }
        }

        // reverse whole array as no pivot
        if (pivot == -1) {
            reverseF(arr, 0, n - 1);
            return;
        }

        // Find element just greater than pivot
        for (int i = n - 1; i > pivot; i--) {
            if (arr[i] > arr[pivot]) {
                swapF(arr, pivot, i);
                break;
            }
        }

        // Reverse part after pivot
        reverseF(arr, pivot + 1, n - 1);
    }

    private static void reverseF(int[] arr, int start, int end) {
        while (start < end) {
            swapF(arr, start, end);
            start++;
            end--;
        }
    }

    private static void swapF(int[] arr, int l, int r) {
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of array: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        Next_permutation obj = new Next_permutation();
        obj.nextPermutation(arr);

        System.out.println("Next Permutation:");
        for (int x : arr) {
            System.out.print(x + " ");
        }

        sc.close();
    }
}
