import java.util.*;
import java.util.stream.*;

public class Task620E { 
    public static void main(String[] args) {
        int n, m;
        Scanner s = new Scanner(System.in);
        n = s.nextInt();
        m = s.nextInt();
        Tree t = readTree(s, n);
        for(Integer r: processQueries(s, t, m)) {
            System.out.println(r);
        }
    }
    private static List<Integer> processQueries(Scanner s, Tree t, int m) {
        List<Integer> resp = new ArrayList<>();
        for(int i = 0; i < m; i++) {
            int type = s.nextInt();
            if(type == 1) {
                int v = s.nextInt();
                int c = s.nextInt();
                t.paint(t.getOrCreate(v), null, c);
            } else {
                int v = s.nextInt();
                resp.add(t.numColors(t.getOrCreate(v)));
            }
        }
        return resp;
    }



    private static Tree readTree(Scanner s, int n) {
        Tree t = new Tree();
        int[] c = new int[n];
        for(int i = 0; i < n; i++) {
            c[i] = s.nextInt();
        }
        for(int i = 0; i < n - 1; i++) {
            Node a = t.getOrCreate(s.nextInt());
            Node b = t.getOrCreate(s.nextInt());
            a.children.add(b);
            b.children.add(a);
        }
        for(int i = 1; i <= n; i++) {
            t.getOrCreate(i).color = c[i - 1];
        }
        return t;
    }
}
class Node {
    Set<Node> children = new HashSet<>();
    int id;
    int color;

    public Node(int id) {
        this.id = id;
    }

    int hasCode() {
        return id;
    }
    public boolean equals(Object o) {
        if(o.getClass() != this.getClass())
            return false;
        return ((Node)o).id == this.id;
    }
    public String toString() {
        return "{" + id + "(" + color + ") ->" + Arrays.toString(this.children.stream().map(n -> n.id)
                .collect(Collectors.toList()).toArray(new Integer[0])) + "}";
    }
}
class Tree {
    Node root;
    Map<Integer, Node> nodesById = new HashMap<>();
    public Node getOrCreate(int id) {
        Node res = nodesById.get(id);
        if(res == null) {
            res = new Node(id);
            nodesById.put(id, res);
        }
        return res;
    }
    public void paint(Node v, Node p, int color) {
        v.color = color;
        v.children.forEach(x -> {
           if(x != p) {
               paint(x, v, color);
           }
        });
    }

    private int numColors(Node v, Node p, Set<Integer> seenColors) {
        int n = 0;
        if(!seenColors.contains(v.color)) {
            seenColors.add(v.color);
            n++;
        }
        n += v.children.stream().filter(x -> x != p).mapToInt(x -> numColors(x, v, seenColors)).sum();
        return n;
    }

    public int numColors(Node v) {
        Set<Integer> seenColors = new HashSet<>();
        return numColors(v, null, seenColors);    
    }
}


