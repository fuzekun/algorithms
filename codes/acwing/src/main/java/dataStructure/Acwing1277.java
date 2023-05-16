package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/2/20 11:39
 * @Description: 线段树，维护序列
 */
public class Acwing1277 {

//    private static final int N = (int)4e5 + 5;
    private static long[] sum, mul, add;
    private static int[] a;
    private static int n, m, p;
//    private static Scanner sc = new Scanner(System.in);
    public static void push_up(int u) {
        sum[u] = (sum[u << 1] + sum[u << 1 | 1]) % p;
    }

    // 使用父亲的蓝标记更新自己结点的值和蓝标记。
    public static void push_down(int u, int l, int r, long mul_x, long add_x) {
        sum[u] = (sum[u] * mul_x + add_x * (r - l + 1)) % p;
        add[u] = (add[u] * mul_x + add_x) % p;
        mul[u] = (mul[u] * mul_x) % p;
    }
    // 更新子节点的懒标记
    public static void push_down(int u, int l, int r) {
        if (mul[u] == 1 && add[u] == 0) return ;
        int mid = (l + r) >> 1;
        push_down(u << 1, l, mid, mul[u], add[u]);
        push_down(u << 1 | 1, mid + 1, r, mul[u], add[u]);
        mul[u] = 1;
        add[u] = 0;
    }
    // 构建线段树
    public static void build(int u, int l, int r) {
        if (l == r) {
            sum[u] = a[l];
            return ;
        }
        int mid = (l + r) >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);                     // 别忘了更新自己
    }
    // 更新[L, R], 乘mul + add
    public static void update(int u, int l, int r, int L, int R, int mul_x, int add_x) {
        if (L <= l && r <= R) {
            // 更新自己的懒标记
            push_down(u, l, r, mul_x, add_x);
            return;
        }
        push_down(u, l, r);
        int mid = l + r >> 1;
        if (L <= mid) update(u << 1, l, mid, L, R, mul_x, add_x);
        if (R > mid) update(u << 1 | 1, mid + 1, r, L, R, mul_x, add_x);
        push_up(u);                 // 别忘了更新自己
    }
    public static long query(int u, int l, int r, int L, int R) {
        if (L <= l && r <= R) return sum[u];
        int mid = l + r >> 1;
        push_down(u, l, r);
        long ans = 0;
        if (L <= mid) ans = query(u << 1, l, mid, L, R);
        if (R > mid) ans = (ans + query(u << 1 | 1, mid + 1, r, L, R)) % p;
        return ans;
    }
    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input1 = bf.readLine().split(" ");
        n = Integer.valueOf(input1[0]);
        p = Integer.valueOf(input1[1]);
        sum = new long[n * 4 + 5];
        mul = new long[n * 4 + 5];
        add = new long[n * 4 + 5];
        a = new int[n + 5];
        String[] input = bf.readLine().split(" ");
        for (int i = 1; i <= n; i++) {
            a[i] = Integer.valueOf(input[i - 1]);
        }
        m = Integer.valueOf(bf.readLine());
        Arrays.fill(mul, 1);
        build(1, 1, n);
        for (int i = 0; i < m; i++) {
//            System.out.println(sum[1] + " " +  mul[1] + " " + add[1]);
            String[] input2 = bf.readLine().split(" ");
            if (input2[0].equals("1")) {
                int l = Integer.parseInt(input2[1]);
                int r = Integer.parseInt(input2[2]);
                int x = Integer.parseInt(input2[3]);
                update(1, 1, n, l, r, x, 0);
            } else if (input2[0].equals("2")) {
                int l = Integer.parseInt(input2[1]);
                int r = Integer.parseInt(input2[2]);
                int x = Integer.parseInt(input2[3]);
                update(1, 1, n, l, r, 1, x);
            } else {
                int l = Integer.parseInt(input2[1]);
                int r = Integer.parseInt(input2[2]);
//                System.out.println(query(1, 1, n, l, r));
                out.write(Long.toString(query(1, 1, n, l, r)) + "\n");
            }
        }
        out.flush();
    }
}
