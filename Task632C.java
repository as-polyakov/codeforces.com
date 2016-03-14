import java.util.*;
import java.util.stream.*;

public class Task632C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] strs = new String[s.nextInt()];
        for(int i = 0; i< strs.length; i++) {
            strs[i] = s.next();
        }
        Arrays.<String>sort(strs, (String x, String y) -> { return (x + y).compareTo(y + x); } );
        System.out.println(Arrays.asList(strs).stream().collect(Collectors.joining("")));
    }
}
