import java.util.*;

public class Task650B {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in); 
        int n = s.nextInt();
        int a = s.nextInt();
        int b = s.nextInt();
        long T = s.nextLong();
        String p = s.next();
        long[] wf = new long[n];
        long[] wfb = new long[n];
        long[] wb = new long[n];
        long pf = 0;
        long pfb = 0;
        long pb = 0;
        for(int i = 0; i < n; i++) {
            int slide = (i == 0 ? 0 : a);
            wf[i] = p.charAt(i) == 'w' ? pf + b + slide + 1: pf + slide + 1;
            wfb[i] = (i == 0 ? a : wf[i] - wf[0] + a);
            wb[i] = p.charAt(n - i - 1) == 'w' ? pb + b + a + 1: pb + a + 1;
            pf = wf[i];
            pb  = wb[i];
        }
        int N1 = solve(wf, wb, T, a, false);
        int N2 = solve(wb, wfb, T - wf[0], a, true);
        System.out.println(N1 < N2 ? N2 : N1);
    }
    private static int solve(long[] wf, long[] wb, long T, int a, boolean back) {
        int N1, N2, N;
        if(T < 0)
            return 0;
        N = 0;
        //System.out.println(Arrays.toString(wf));
        //System.out.println(Arrays.toString(wb));
        N1 = Arrays.binarySearch(wf, T);
        if(N1 < 0)
            N1 = -N1 - 2;
        if(N1 == wf.length - 1) {
            return wf.length;
        }
        //System.out.println("N1 = " + N1);
        for(int i = N1; i >= 0; i--) {
            long T1 = T - wf[i] - a * i;
            N2 = Arrays.binarySearch(wb, T1);
            N2 = (N2 < 0 ? -N2 - 2 : N2);
            N2 = (N2 >= wb.length - 1 ? wb.length : N2);
            //System.out.println("i = " + i + " T' = " + T1 + " N2 = " + N2);
            int curN = (i + 1) + (N2 >= 0 ? N2 + 1 : (back ? 1 : 0));
            if(N < curN)
                N = curN;
        }
        return N;
    }
}

