package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;

/**
 * @author: Zekun Fu
 * @date: 2023/2/20 19:28
 * @Description: 线段树，一个简单的整数问题2
 * 区间修改，区间查询
 */
public class Acwing_243 {
    private static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
    private static long[] sum, add;
    private static int[] a;

    private static void push_up(int u){
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
    }
    // 更新节点的sum和懒标记
    private static void push_down(int u, int l, int r, long x) {
        sum[u] += x * (r - l + 1);
        add[u] += x;
    }
    // 懒标记下推
    public static void push_down(int u, int l, int r) {
        if (add[u] == 0) return ;
        int mid = l + r >> 1;
        push_down(u << 1, l, mid, add[u]);
        push_down(u << 1 | 1, mid + 1, r, add[u]);
        add[u] = 0;                                                 // 懒标记下推后删除
    }
    public static void build(int u, int l, int r) {
        if (l == r) {
            sum[u] = a[l - 1];                                      // 注意下标从0开始
            return;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);
    }
    // 更新[L, R] + d
    public static void update(int u, int l, int r, int L, int R, int x) {
        if (R > r || L < l) throw new NoSuchElementException("区间[L, R]应该在区间[l, r]之内");
        if (L <= l && r <= R) {                                     // 恰好包含在这个区间中
            push_down(u, l, r, x);                                  // 更新自己区间的sum和懒标记
            return ;
        }
        push_down(u, l, r);                                         // 更新之前先将懒标记下推
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R, x);
        if (R > mid)update(u << 1 | 1, mid + 1, r, L, R, x);
        push_up(u);
    }
    // 求[L, R]的区间和
    public static long query(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) {                                     // 恰好包含在这个区间中
            return sum[u];
        }
        push_down(u, l, r);                                         // 访问之前，先前懒标记下推
        int mid = l + r >> 1;
        long ans = 0;
        if (L <= mid) ans = query(u << 1, l, mid, L, R);
        if (R > mid) ans += query(u << 1 | 1, mid + 1, r, L, R);
        return ans;
    }
    public static void main(String[] args) throws Exception{
        String[] input1 = in.readLine().split(" ");
        int n = Integer.valueOf(input1[0]), m = Integer.valueOf(input1[1]);
        a =  new int[n + 1];
        sum = new long[n * 4 + 4];
        add = new long[n * 4 + 4];

        String[] input2 = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Integer.valueOf(input2[i]);
        }
        build(1, 1, n);
        for (int i = 0; i < m; i++) {
            String[] input3 = in.readLine().split(" ");
            if (input3[0].equals("Q")) {
                // 查询
                int l = Integer.valueOf(input3[1]);
                int r = Integer.valueOf(input3[2]);
                out.write(String.valueOf(query(1, 1, n, l, r)) + "\n"); // 注意下标是否从0开始，如果是，需要加上1
            } else {
                int l = Integer.valueOf(input3[1]);
                int r = Integer.valueOf(input3[2]);
                int x = Integer.valueOf(input3[3]);
                update(1, 1, n, l, r, x);
            }
        }
        out.flush();
        out.close();
        in.close();
    }
}
