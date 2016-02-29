import java.util.*;

public class Task624B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        Integer[] arr = new Integer[n];
        for(int i = 0; i < n; i++) {
            arr[i] = s.nextInt();
        }
        System.out.println(longest(arr));
    }

    private static long longest(Integer[] arr) {
        Arrays.sort(arr, Collections.reverseOrder());
        int min = arr[0] + 1;
        long n = 0;
        for(int a: arr) {
            int ni = (a >= min ? min - 1: a);
            min = ni;
            n += (ni > 0 ? ni : 0);
        }
        return n;
    }
}
