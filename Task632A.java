import java.util.*;

public class Task632A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int p = s.nextInt();
        int[] cust = new int[n];
        for(int i = 0; i < n; i++) {
            cust[i] = s.next().equals("half") ? 0 : 1;
        }
        long m = 0;
        double r = 0; 
        for(int i = n - 1; i >= 0; i--) {
            if(cust[i] == 0) {
                r *= 2;
                m += r * p / 2; 
            }
            else {
                double r0 = r;
                r = 2 * r + 1;
                m += p * (r - r0 - 0.5);
            }
        }

        System.out.println(m);
    }
}
