package dataStructure;

import java.io.*;
import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * @author: Zekun Fu
 * @date: 2023/2/24 14:55
 * @Description: 你能回答这些问题吗
 */
public class acwing_245 {
    private static class Node {
        int maxv, lmaxv, rmaxv, sum;
        Node(int maxv, int lmaxv, int rmaxv, int sum) {
            this.maxv = maxv;
            this.lmaxv = lmaxv;
            this.rmaxv = rmaxv;
            this.sum = sum;
        }
    }
    private static int[] maxv, lmaxv, rmaxv, sum, a;
    private static Node push_up(Node l, Node r) {
        Node u = new Node(0, 0, 0, 0);
        u.sum = l.sum + r.sum;
        u.lmaxv = Math.max(l.lmaxv, l.sum + r.lmaxv);
        u.rmaxv = Math.max(r.rmaxv, r.sum + l.rmaxv);
        u.maxv = Math.max(Math.max(l.maxv, r.maxv), l.rmaxv + r.lmaxv);
        return u;
    }
    private static void push_up(int u) {
        maxv[u] = Math.max(Math.max(maxv[u << 1], maxv[u << 1 | 1]), rmaxv[u << 1] + lmaxv[u << 1 | 1]);            // 左边的右 + 右边的左
        lmaxv[u] = Math.max(lmaxv[u << 1], sum[u << 1] + lmaxv[u << 1 | 1]);                            // 左边左最大值，左边的和 + 右边的左最大值
        rmaxv[u] = Math.max(rmaxv[u << 1 | 1], sum[u << 1 | 1] + rmaxv[u << 1]);                            // 右边的右最大值，右边的和 + 左边的右最大值
        sum[u] = sum[u << 1 | 1] + sum[u << 1];
    }
   public static void init(int n) {
        a = new int[n + 1];
        maxv = new int[n * 4];
        lmaxv = new int[n * 4];
        rmaxv = new int[n * 4];
        sum = new int[n * 4];
    }
   public static void build(int u, int l, int r) {
        if (l == r) {
            sum[u] = maxv[u] = lmaxv[u] = rmaxv[u] = a[l];
            return ;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);
   }
   public static void update(int u, int l, int r, int p, int x) {
        if (p < l || p > r) throw new NoSuchElementException("p越界了,p应该在区间中");
        if (l == r) {
            sum[u] = maxv[u] = lmaxv[u] = rmaxv[u] = x;
            return ;
        }
        int mid = l + r >> 1;
        if (p <= mid) update(u << 1, l, mid, p, x);
        else update(u << 1 | 1, mid + 1, r, p, x);
        push_up(u);
    }
    public static  Node query(int u, int l, int r, int L, int R) {
        if (L > r || R < l) throw new NoSuchElementException("区间[L, R]应该在区间[l, r]之内");
        if (L <= l && r <= R) {
            return new Node(maxv[u], lmaxv[u], rmaxv[u], sum[u]);
        }
        int mid = l + r >> 1;
        if (R <= mid) return query(u << 1, l, mid, L, R);
        if (L > mid) return query(u << 1 | 1, mid + 1, r, L, R);
        Node left = query(u << 1, l, mid, L, R);
        Node rigth = query(u << 1 | 1, mid + 1, r, L, R);
        return push_up(left, rigth);
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
