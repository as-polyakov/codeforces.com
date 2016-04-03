import java.util.*;
import java.io.*;
import java.util.stream.*;

public class Task190D {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] l = r.readLine().split(" ");
        int n = Integer.valueOf(l[0]);
        int k = Integer.valueOf(l[1]);

        TreeSet<Integer> positions = new TreeSet<>();
        Map<Long, Deque<Integer>> valToPos = new HashMap<>();
        Map<Long, Integer> counts = new HashMap<>();
        if(k == 1) {
            System.out.println((long)n * (n + 1) / 2);
            return;
        }
        long[] vals = new long[n];
        l = r.readLine().split(" ");
        for(int i = 0; i < n; i++) {
            long v = Long.valueOf(l[i]);
            vals[i] = v;
            int c = counts.getOrDefault(v, 0) + 1;
            counts.put(v, c);
            if(c >= k) {
                positions.add(i);
                Deque<Integer> poss = valToPos.getOrDefault(v, new LinkedList<Integer>());
                poss.add(i);
                valToPos.put(v, poss);
            }
        }
        long total = 0;
        for(int i = 0; i < n; i++) {
            if(positions.isEmpty())
                break;
            int idx = positions.first();
            total += n - idx;
            if(valToPos.containsKey(vals[i])) {
                Deque<Integer> poss = valToPos.get(vals[i]);
                positions.remove(poss.getFirst());
                poss.removeFirst();
                if(poss.isEmpty()) {
                    valToPos.remove(vals[i]);
                }
            }
       }
        System.out.println(total);
    }
    private static class Tuple implements Comparable<Tuple> {
        int idx;
        long value;
        public Tuple(int idx, long value) {
            this.idx = idx;
            this.value = value;
        }
        public int hashCode() {
            return 31 * idx + (int)value;
        }
        public boolean equals(Object o) {
            Tuple other = (Tuple)o;
            return this.idx == other.idx && this.value == other.value;
        }
        public int compareTo(Tuple other) {
            return this.idx - other.idx;
        }
    }
}


