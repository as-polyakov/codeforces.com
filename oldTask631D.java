import java.util.*;
import java.io.*;

public class oldTask631D {
    public static void main(String[] args) throws Exception {
        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String[] l = r.readLine().split(" ");
        int n = Integer.valueOf(l[0]);
        int m = Integer.valueOf(l[1]);
        Sentence s1 = canonize(r.readLine().split(" "));
        Sentence s2 = canonize(r.readLine().split(" "));
        Sentence ls = (s2.l >= s1.l ? s2 : s1);
        Sentence ss = (s2.l < s1.l ? s2 : s1);

        //System.out.println("canonical ls: " + ls);
        //System.out.println("canonical ss: " + ss);
        int hash = hash(ss, ss.l);
        int rhash = hash(ls, ss.l);
        //System.out.println("hash: " + hash);
        //System.out.println("rhash: " + rhash);

        int count = 0;
        int pi = -1;
        int bti = 0;
        int bi = 0;
        Tuple<Integer, Integer> end = getToken(ls, ss.l);
        int eti = end.u;
        int ei = end.v;

        char s = ls.tt[bti].c;
        char e = ls.tt[eti].c;
        for(long i = 0; i + ss.l <= ls.l; i++) {
            if(hash == rhash)
                count++;
            
            if(i + ss.l >= ls.l)
                break;
            rhash -= pow(31, ss.l - 1) * s;

            if(++bi >= ls.tt[bti].n) {
                bti++;
                bi = 0;
                s = ls.tt[bti].c;
            }
            if(++ei >= ls.tt[eti].n) {
                eti++;
                ei = 0;
                e = ls.tt[eti].c;
            }
            rhash = 31 * rhash + e;
        }
        System.out.println(count);
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

    private static Tuple<Integer, Integer> getToken(Sentence s, long p) {
        long l = 0;
        int i = 0;
        while(l < p) {
            l += s.tt[i++].n;
        }
        return new Tuple<Integer, Integer>(i - 1, s.tt[i - 1].n  - 1 - (int) (l - p));
    }

    private static int hash(Sentence s, long l) {
        int h = 0;
        int ll = 0;
        for(int i = 0; i < s.tt.length && ll < l; i++) {
            for(int j = 0; j < s.tt[i].n && ll < l; j++) {
                h = 31 * h + s.tt[i].c;
                ll++;
            }
        }
        return h;
    }

    private static Sentence canonize(String[] a) {
        long l = 0;
        Collection<Token> res = new ArrayList<>();
        Token p = null;
        for(int i = 0; i < a.length; i++) {
            String[] aa = a[i].split("-");
            int an = Integer.valueOf(aa[0]);
            char ac = aa[1].charAt(0);
            l += an;
            if(p != null && p.c == ac)
                p.n += an;
            else {
                p = new Token(ac, an);
                res.add(p);
            }
        }
        return new Sentence(res.toArray(new Token[0]), l);
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
        long l;
        Sentence(Token[] tt, long l) {
            this.tt = tt;
            this.l = l;
        }
        public String toString() {
            return l + " " + Arrays.toString(tt);
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

