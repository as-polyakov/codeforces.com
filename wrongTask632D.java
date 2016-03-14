import java.util.*; import java.math.*;

public class wrongTask632D {
    private static Res[] cache = null;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n, m; 
        n = s.nextInt();
        m = s.nextInt();
        long[] arr = new long[n];
        cache = new Res[n];
        for(int i = 0; i < arr.length; i++) {
            arr[i] = s.nextInt();
        }
        Res rmax = null;
        int e = -1;
        for(int i = 0; i < arr.length; i++) {
            Res rs = lcmi(arr, i, m);
            if(rmax == null || rmax.max < rs.max) {
                e = i;
                rmax = rs;
            }
        }
        long ml = rmax.resByMl.stream().map(p -> p.x).min(Long::compare).get(); 
        List<Integer> path = new ArrayList<>();
        if(rmax != null)
            path.add(e);
        Res p = lcmi(arr, e, m);
        while(p.i != -1) {
            path.add(p.i);
            p = lcmi(arr, p.i, m);
        }
        System.out.println(ml + " " + rmax.max);
        Integer[] pathInt = path.stream().map(i -> i + 1).toArray(size -> new Integer[size]);
        Arrays.sort(pathInt);
        for(int i = 0; i < pathInt.length; i++) {
            System.out.print( pathInt[i] + " ");
        }
        System.out.println("");
    }
    private static Res lcmi(long[] arr, int n, int m) {
        if(cache[n] != null) {
            return cache[n];
        }

        Res cur = new Res(-1, -1);
        if(arr[n] <= m) {
            cur = new Res(1, -1);
            cur.resByMl.add(new Pair<Long, Pair<Res, Long>>(arr[n], null));
        } 

        for(int i = 0; arr[n] <= m && i < n; i++) {
            Res r = lcmi(arr, i, m);
            if(r.max != -1 && r.max + 1 >= cur.max) {
                for(Pair<Long, Pair<Res, Long>> prml: r.resByMl) {
                    if(lcm(prml.x, arr[n]) <= m) {
                        if(r.max + 1 > cur.max) {
                            cur = new Res(r.max + 1, i);
                        }
                        cur.resByMl.add(new Pair<Long, Pair<Res, Long>>(lcm(prml.x, arr[n]), new Pair<Res,Long>(r, prml.x)));
                    }
                }
            }
        }
        cache[n] = cur;
        return cur;
    }
    private static long gcd(long a, long b) {
        BigInteger aa = BigInteger.valueOf(a);
        BigInteger bb = BigInteger.valueOf(b);
        return aa.gcd(bb).longValue();
    }

    private static long lcm(long a, long b) {
        if(a == 0 || b == 0)
            throw new RuntimeException("Can't compute lcm(" + a + ", " + b + ")");
        return (a * b) / gcd(a, b);
    }

    private static class Res { 
        int max;
        int i;
        List<Pair<Long, Pair<Res, Long>>> resByMl = new ArrayList<>();
        public Res(int max, int i) {
            this.max = max;
            this.i = i;
        }
    }
    private static class Pair<U, V> {
        U x;
        V y;
        public Pair(U x, V y) {
            this.x = x;
            this.y = y;
        }
    }
} 

