import java.util.*;

public class Task630I {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        long m = 0;
        if(n - 4 >= 0) {
            m = (long)Math.pow(4, n - 3) * 4 * 3 * 2 + (n - 3) * (long)Math.pow(4, n - 4) * 36;
        } else if(n - 2 > 0) {
            m = 4 * 3 * 2;
        }
        System.out.println(m);
    }
}
