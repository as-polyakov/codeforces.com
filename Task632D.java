import java.util.*;
import java.io.*;

public class Task632D {
    public static void main(String[] args) throws Exception {
        Scanner s = new Scanner(System.in);
        BufferedReader rdr = new BufferedReader(new InputStreamReader(System.in));
        String[] strs = rdr.readLine().split(" ");
        int n = Integer.parseInt(strs[0]);
        int m = Integer.parseInt(strs[1]);
        strs = rdr.readLine().split(" ");
        int[] in = new int[n];
        for(int i = 0; i < n; i++) {
            in[i] = Integer.parseInt(strs[i]);
        }

        int[] counts = new int[m + 1];
        for(int i = 0; i < n; i++) {
            if(in[i] <= m) {
                counts[in[i]]++;
            }
        }
        int[] divisors = new int[m + 1];
        for(int i = 1; i <= m; i++) {
            if(counts[i] > 0) {
                for(int k = i; k <= m; k += i) {
                    divisors[k] += counts[i];
                }
            }
        }
        int mlcm = 0;
        int l = 1;
        for(int i = 0; i <= m; i++) {
            if(mlcm < divisors[i]) {
                mlcm = divisors[i];
                l = i;
            }
        }
        System.out.println(l + " " + mlcm);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(System.out));
        for(int i = 0; i < n; i++) {
            if(l % in[i] == 0) {
                pw.print( (i + 1) + " ");
            }
        }
        pw.println("");
        pw.flush();
    }
}
            
        


