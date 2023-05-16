package dataStructure;

import java.io.*;
import java.util.NoSuchElementException;

/**
 * @author: Zekun Fu
 * @date: 2023/2/24 16:50
 * @Description:
 * 把l, r放在函数中。
 *
 * 12 1
 * 38 148 456 -960 -390 470 271 763 -458 -52 647 -205
 * 1 1 16
 */
public class Acwing_245_3 {

    private static class Node {
        public int maxv, lmax, rmax, sum;
        public Node(int maxv, int lmax, int rmax, int sum){
            this.maxv = maxv;
            this.lmax = lmax;
            this.rmax = rmax;
            this.sum = sum;
        }
        public Node() {}
        public void setSame(int x) {
            maxv = lmax = rmax = sum = x;
        }
    }
    private static int[] a;

    private static Node[] nodes;
    public static void init(int n) {
        nodes = new Node[n * 4];
        a = new int[n + 1];
    }
    public static void push_up(Node node, Node left, Node right) {
        node.maxv = Math.max(Math.max(left.maxv, right.maxv), left.rmax + right.lmax);
        node.lmax = Math.max(left.lmax, left.sum + right.lmax);
        node.rmax = Math.max(right.rmax, right.sum + left.rmax);
        node.sum = left.sum + right.sum;
    }
    public static void push_up(int u) {
        push_up(nodes[u], nodes[u << 1], nodes[u << 1 | 1]);
    }
    public static void build(int u, int l, int r) {
        // if (nodes[u] == null) nodes[u] = new Node();
        if (l == r) {
            nodes[u].setSame(a[l]);
            return ;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);
    }
    public static void update(int u, int l, int r, int p, int x) {
        // if (p < l || p > r) throw new NoSuchElementException("p越界了,p应该在区间中");
        if (l == r) {
            nodes[u].setSame(x);
            return;
        }
        int mid = l + r >> 1;
        if (p <= mid) update(u << 1, l, mid, p, x);
        else update(u << 1 | 1, mid + 1, r, p, x);
        push_up(u);
    }
    public static Node query(int u, int l, int r, int L, int R) {
        // if (L > r || R < l) throw new NoSuchElementException("区间[L, R]应该在区间[l, r]之内");
        if (L <= l && r <= R) {
            return nodes[u];
        }
        int mid = l + r >> 1;
        if (R <= mid) return query(u << 1, l, mid, L, R);
        if (L > mid) return query(u << 1 | 1, mid + 1, r, L, R);
        Node left = query(u << 1, l, mid, L, R);
        Node rigth = query(u << 1 | 1, mid + 1, r, L, R);
        Node ans = new Node();
        push_up(ans, left, rigth);
        return ans;
    }


    public static void main(String[] args) throws IOException {

        BufferedReader in  = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input1 = in.readLine().split(" ");
        int n = Integer.parseInt(input1[0]);
        int m = Integer.parseInt(input1[1]);
        init(n);
        String []input2 = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i + 1] = Integer.parseInt(input2[i]);
        }
        build(1, 1, n);
        for (int i = 0; i < m; i++) {
            String []input3 = in.readLine().split(" ");
            if (input3[0].equals("1")) {
                int l = Integer.parseInt(input3[1]);
                int r = Integer.parseInt(input3[2]);
                if (l > r) {
                    int tmp = l;
                    l = r;
                    r = tmp;
                }
                out.write(String.valueOf(query(1,1, n, l, r).maxv) + "\n");
            }
            else {
                int p = Integer.parseInt(input3[1]);
                int x = Integer.parseInt(input3[2]);
                update(1,1, n, p, x);
            }
        }
        out.flush();
        out.close();
        in.close();
    }

}
