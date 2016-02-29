import java.util.*;
import java.io.*;

public class Task620C {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(r.readLine());
        String[] str = r.readLine().split(" ");
        int num = 0;
        Set<String> seen = new HashSet<>();
        Stack<Tuple> segments = new Stack<>();
        int s = 0;
        for(int i = 0; i < str.length; i++) {
            if(seen.contains(str[i])) {
                num++;
                seen = new HashSet<>();
                segments.push(new Tuple(s + 1, i + 1));
                s = i + 1;
            } else {
                seen.add(str[i]);
            }
        }
        if(!segments.isEmpty() && segments.peek().b != str.length) {
            segments.peek().b = str.length;
        }
        PrintWriter pw = new PrintWriter(System.out);
        pw.println(num == 0 ? "-1" : num);
        for(Tuple t: segments) {
            pw.println(t.a + " " + t.b);
        }
        pw.flush();
    }
    static class Tuple {
        Integer a, b;
        public Tuple(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }
    }
}
