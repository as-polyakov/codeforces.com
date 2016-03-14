import java.util.*;

public class Task620A {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int x1, y1, x2, y2;
        x1 = s.nextInt();
        y1 = s.nextInt();
        x2 = s.nextInt();
        y2 = s.nextInt();

        int dx = Math.abs(x1 - x2);
        int dy = Math.abs(y1 - y2);
        int md = dx < dy ? dx : dy;
        System.out.println(md + ((md == dx ? dy : dx) - md));
    }
}
