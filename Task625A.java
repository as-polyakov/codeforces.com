import java.util.*;

public class Task625A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long a, b, c, n;
        n = s.nextLong();
        a = s.nextLong();
        b = s.nextLong();
        c = s.nextLong();
        if (a <= b - c) {
            System.out.println(n / a);
            return; 
        } else if(n - b < 0) {
            System.out.println(n / a);
            return;
        }
        long l = (n - b) / (b - c) + 1;
        n -= l * (b - c);
        if( n / a > 0)
            l += n / a;
        System.out.println(l);
/*        long l = 0;

        while(n  / b > 0) {
            long cl = n / b;
            n -= cl* (b - c);
            l += cl;
        }
        if (n / a > 0)
            l += n / a;
        System.out.println(l);
        */
    }
}
