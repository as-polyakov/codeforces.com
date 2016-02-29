import java.util.stream.*;
import java.util.*;

public class Task620D {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        Tuple[] a = new Tuple[s.nextInt()];
        for(int i = 0; i < a.length; i++) {
            a[i] = new Tuple(2 * s.nextInt(), i);
        }
        Tuple[] b = new Tuple[s.nextInt()];
        for(int i = 0; i < b.length; i++) {
            b[i] = new Tuple(2 * s.nextInt(), i);
        }
        long sa = Arrays.asList(a).stream().mapToLong(o -> o.v).sum() / 2;
        long sb = Arrays.asList(b).stream().mapToLong(o -> o.v).sum() / 2;
        long mins = sa - sb;
        Res res1 = optimize(mins, a, b);
        Res res2 = optimize(mins, genPerm(a), genPerm(b));
        printRes(res1, res2);
    }
    private static Tuple[] genPerm(Tuple[] in) {
        List<Tuple> res = new ArrayList<>();
        for(int i = 0; i < in.length - 1; i++) {
            for(int j = i + 1; j < in.length; j++) {
                res.add(new Tuple(in[i].v + in[j].v, in[i].idx, in[j].idx));
            }
        }
        return res.toArray(new Tuple[0]);
    }

    private static void printRes(Res res1, Res res2) {
        int num = 0;
        if(res1.a != null)
            num = 1;
        if(res2.a != null && res2.s < res1.s)
            num = 2;
        System.out.println(num < 2 ? res1.s : res2.s);
        System.out.println(num);
        if(num == 1) {
            System.out.println((res1.a.idx + 1) + " " + (res1.b.idx + 1));
        } else if(num == 2) {
            System.out.println((res2.a.idx + 1) + " " + (res2.b.idx + 1));
            System.out.println((res2.a.idx2 + 1) + " " + (res2.b.idx2 + 1));
        }
    }

    private static Res optimize(long sdelta, Tuple[] a, Tuple[] b) {
        Tuple mini, minj;
        mini = null;
        minj = null;
        long min = Math.abs(sdelta);
        Arrays.sort(b);
        for(int i = 0; i < a.length && b.length > 0; i++) {
            long d = sdelta - a[i].v;
            Tuple m = tryAbs(d, b);
            if(m.v < min) {
                min = m.v;
                mini = a[i]; 
                minj = b[m.idx];
            }
        }
        return new Res(min, mini, minj);
    }
    private static Tuple tryAbs(long d, Tuple[] b) {
        int j = Arrays.binarySearch(b, new Tuple(-d, -1));
        Tuple m = null;
        if(j < 0) {
            int j1 = -j - 1;
            int j2 = -j - 2;
            j1 = Math.max(0, Math.min(j1, b.length - 1));
            j2 = Math.max(0, Math.min(j2, b.length - 1));
            long m1 = Math.abs(d + b[j1].v);
            long m2 = Math.abs(d + b[j2].v); 
            j = (m1 < m2 ? j1 : j2);
        } 
        m = new Tuple(Math.abs(d + b[j].v), j);
        return m;
    }

    static class Tuple implements Comparable {
        long v;
        int idx, idx2;
        Tuple(long v, int idx) {
            this.v = v;
            this.idx = idx;
            this.idx2 = -1;
        }
        Tuple(long v, int idx, int idx2) {
            this.v = v;
            this.idx = idx;
            this.idx2 = idx2;
        }
        public int compareTo(Object o) {
            Tuple t = (Tuple)o;
            return Long.valueOf(this.v).compareTo(Long.valueOf(t.v));
        }
        public boolean equals(Object o) {
            return this.v == ((Tuple)o).v;
        }
        public String toString() {
            return "(" + v + ", " + idx +  (idx2 != -1 ? ", " + idx2 : "") + ")";
        }
    }
    static class Res {
        long s;
        Tuple a;
        Tuple b;
        Res(long s, Tuple a, Tuple b) {
            this.s = s;
            this.a = a;
            this.b = b;
        }
    }
}
