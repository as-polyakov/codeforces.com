import java.util.*;

public class Task626C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, m;
        n = s.nextInt();
        m = s.nextInt();
        int nl = 2 * n;
        int ml = 3 * m;
        int c = Math.min(nl, ml) / 6;

        for(int i = 1; i <= c; i++) {
            if(nl <= ml) {
                nl += 2;
                if(nl % 3 == 0 && ml >= nl)
                    nl += 2;
            } else {
                ml += 3;
                if(ml % 2 == 0 && nl >= ml) {
                    if(ml < nl)
                        ml += 3;
                    else
                        nl += 2;
                }
            }
        }
        System.out.println(Math.max(nl, ml));
    }
}
