import java.util.*;

public class Task632C {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String[] strs = new String[s.nextInt()];
        for(int i = 0; i< strs.length; i++) {
            strs[i] = s.next();
        }
        StringBuffer res = new StringBuffer();
        List<Integer> lengths = new ArrayList<>();
        int tl = 0;
        for(int i = 0; i < strs.length; i++) {
            StringBuffer cur = new StringBuffer(res).insert(0, strs[i]);
            StringBuffer min = cur;
            int ml = 0;
            tl = 0;
            for(int j = 0; j < lengths.size(); j++) {
                tl += lengths.get(j);
                cur = new StringBuffer(res).insert(tl, strs[i]);
                if(cur.toString().compareTo(min.toString()) < 0) {
                    min = cur;
                    ml = j;
                }
            }
            lengths.add(ml, strs[i].length());
            res = min;
        }
        System.out.println(res);
    }
}
