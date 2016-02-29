import java.util.*;

public class Task1A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        long n, m, a;
        n = s.nextInt();
        m = s.nextInt();
        a = s.nextInt();
        System.out.println( (long) ( Math.ceil( (double) n / a) * Math.ceil( (double) m / a)));
    }
}

