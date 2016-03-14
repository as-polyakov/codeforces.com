import java.util.*;
import java.math.*;

public class Task630R {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        BigInteger n = new BigInteger(s.next());
        System.out.println( n.mod(new BigInteger("2")).intValue() != 0 ? "1" : "2");
    }
}

