import java.util.*;

public class Task625B {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        char[] text = s.next().toCharArray();
        char[] pat = s.next().toCharArray();
        //System.out.println(Arrays.toString(kmp(text, pat, lps(pat))));
        System.out.println(count(kmp(text, pat, lps(pat))));
    }
    private static int count(char[] matches) {
        //System.out.println(Arrays.toString(matches));
        int c = 0;
        char p = ' ';
        int depth = 0;
        for(int i = 0; i < matches.length; i++) {
            if(matches[i] == '(') {
                c += depth == 0 ? 1 : 0;
                depth++;
            } else if (matches[i] == ')') {
                depth--;
            } else if (matches[i] == '|') {
            } else if (matches[i] == 'x') {
                c++;
            }
        }
        return c;
    }

    private static char[] kmp(char[] in, char[] p, int[] lps) {
        char[] pos = new char[in.length];
        int i = 0;
        int j = 0;
        while (i < in.length) {
            if(in[i] == p[j]) {
                i++;
                j++;
            } 
            if(j == p.length) {
                if(j > 1) {
                    pos[i - j] = (pos[i - j] == ')' ? '|' : '(');
                    pos[i - 1] = ')';
                } else pos[i - 1] = 'x';
                j = 0;//lps[j - 1];
            } else if(i < in.length && in[i] != p[j]) {
                if(j != 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return pos;
    }

    private static int[] lps(char[] in) {
        int[] lps = new int[in.length];
        lps[0] = 0;
        int i = 1;
        int len = 0;
        while(i < in.length) {
            if(in[i] == in[len]) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if(len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }
}
