import java.util.*;
import java.io.*;

public class Task631D {

    private static Tuple<Integer, Integer> getStart(Sentence t, Sentence p) {
        for(int i = 0; i < t.count(); i++) {
            if(t.tt[i].c == p.tt[0].c && t.tt[i].n <= p.tt[0].n)
                return new Tuple<>(i, t.tt[i].n - p.tt[0].n);
        }
        return null;
    }

    private static int roll(int hash, Sentence pattern, Sentence text, Tuple<Integer, Integer> start, Tuple<Integer, Integer> end) {
        int moved = 0;
        char oldFirst = text.tt[start.u].c;
        int hcount = end.u - start.u;
        if(start.v != 0) {
            moved = text.tt[start.u].n - start.v;
            start.v = 0;
            start.u++;
        } else if(text.tt[start.u].c == pattern.tt[0].c && text.tt[start.u].n > pattern.tt[0].n){
            start.v = text.tt[start.u].n - pattern.tt[0].n;
            moved = start.v;
        } else {
            moved += text.tt[start.u].n;
            start.u++;
        }
        hash -= pow(31, hcount) * oldFirst * moved;

        int emoved = moved;
        while(emoved > 0 && end.u < text.count()) {
            int d = text.tt[end.u].n - end.v;
            if(d <= emoved) {
                hash += text.tt[end.u].c * d;
                hash *= 31;
                end.u++;
                end.v = 0;
            } else {
                end.v += emoved;
                d = emoved;
                hash += text.tt[end.u].c * d;
            }
            emoved -= d;
        }
        return hash;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] l = r.readLine().split(" ");
        int n = Integer.valueOf(l[0]);
        int m = Integer.valueOf(l[1]);
        Sentence s1 = canonize(r.readLine().split(" "));
        Sentence s2 = canonize(r.readLine().split(" "));
        Sentence text = (s2.tl >= s1.tl ? s2 : s1);
        Sentence pattern = (s2.tl < s1.tl ? s2 : s1);
        int count = 0;

        int hash = hash(pattern, pattern.tl);
        Tuple<Integer, Integer> start = new Tuple<>(0, 0);
        Tuple<Integer, Integer> end = getEnd(text, pattern.tl);
        int rhash = hash(text, pattern.tl);

        while(end.u < text.count()) {
            if(hash == rhash && verify(text, pattern, start)) {
                count++;
            }
            rhash = roll(rhash, pattern, text, start, end);
        }
        System.out.println(count);
    }

    private static boolean verify(Sentence text, Sentence pattern, Tuple<Integer, Integer> start) {
        int i = 0;
        if(start.v != 0 && text.tt[start.u].n - start.v < pattern.tt[0].n)
                return false;
        while(i < pattern.count() && text.tt[start.u + i].c == pattern.tt[i].c)  {
             i++;
        }
        return text.tt[start.u + i - 1].n >= pattern.tt[i - 1].n;
    }

    private static int pow (int a, long b) {
        if ( b == 0) 
            return 1;
        if ( b == 1)
            return a;
        if (b % 2 == 0)    
            return  pow ( a * a, b/2); //even a=(a^2)^b/2
        else 
            return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2
    }

    private static Tuple<Integer, Integer> getEnd(Sentence s, long p) {
        long l = 0;
        int i = 0;
        while(l < p) {
            l += s.tt[i++].n;
        }
        return new Tuple<Integer, Integer>(i - 1, s.tt[i - 1].n  - (int) (l - p));
    }

    private static int hash(Sentence s, long l) {
        int h = 0;
        long ll = 0;
        for(int i = 0; i < s.count() && ll < l; i++) {
            long nn = Math.min(l - ll, s.tt[i].n);
            h = 31 * h + s.tt[i].c * (int)nn;
            ll += nn;
        }
        return h;
    }


    private static Sentence canonize(String[] a) {
        Collection<Token> res = new ArrayList<>();
        Token p = null;
        long tl = 0;
        for(int i = 0; i < a.length; i++) {
            String[] aa = a[i].split("-");
            int an = Integer.valueOf(aa[0]);
            char ac = aa[1].charAt(0);
            if(p != null && p.c == ac)
                p.n += an;
            else {
                p = new Token(ac, an);
                res.add(p);
            }
            tl += an;
        }
        return new Sentence(res.toArray(new Token[0]), tl);
    }

    static class Tuple<U, V> {
        U u;
        V v;
        Tuple(U u, V v) {
            this.u = u;
            this.v = v;
        }
    }

    static class Sentence {
        Token[] tt;
        long tl;
        Sentence(Token[] tt, long tl) {
            this.tt = tt;
            this.tl = tl;
        }
        public int count() {
            return tt.length;
        }
        public String toString() {
            return Arrays.toString(tt);
        }
    }


    static class Token {
        char c;
        int n;
        Token(char c, int n) {
            this.c = c;
            this.n = n;
        }
        public String toString() {
            return n + "->" + c;
        }
    }

}

