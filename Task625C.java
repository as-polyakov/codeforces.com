import java.util.*;

public class Task625C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, k;
        n = s.nextInt();
        k = s.nextInt();
        int min = 1;
        int max = n * n - (n - k + 1) * n + 1;
        int c = 0;
        //System.out.println("max: " + max + " last: " + (n * n - (n - k)));
        System.out.println(((max + n * n - (n - k)) * n ) / 2);
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= n; j++) {
                if(j >= k) {
                    c = max++;
                } else {
                    c = min++;
                }
                System.out.print(c + " "); 
            }
            System.out.print("\n");
        }
    }
}
