package dataStructure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.NoSuchElementException;

/**
 * @author: Zekun Fu
 * @date: 2023/2/20 20:47
 * @Description:  区间最大公约数
 * 线段树
 * 最大公约数性质
 * 差分
 *
 *
 * 1. l < r的情况
 * 2.
 *
 * 1. query的初始化不容易，所以直接考虑全部情况就行了。
 * 2. long使用，数组、更新的元素都应该使用long
 * 3. 单点修改和区间修改的update
 *
 *
 *
 * 总结
 * 1. 线段树最重要的push_up和push_down操作
 * 2. 使用long
 * 3. build的时候的下标，使用的时候的下标。
 */
class Pair {
    public long a;
    public long b;
    public Pair(long a, long b) {
        this.a = a;
        this.b = b;
    }
}
public class Acwing_246 {

    private static long[] sum, d, a, b;                         // 都是单点修改, a, b分别是原数组和差分数组
    // 最大公约数
    private static long gcd(long a, long b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }
    // 向上标记更新，求和，求最大公约数
    private static void push_up(int u) {
        sum[u] = sum[u << 1] + sum[u << 1 | 1];
        d[u] = gcd(d[u << 1], d[u << 1 | 1]);
    }
    private static void build(int u, int l, int r) {
        if (l == r) {
            sum[u] = b[l - 1];                                              // 注意下标从0开始
            d[u] = b[l - 1];
            return ;
        }
        int mid = l + r >> 1;
        build(u << 1, l, mid);
        build(u << 1 | 1, mid + 1, r);
        push_up(u);
    }
    // 将p位置的点，加上x
    public static void update(int u, int l, int r, int p, long x) {
        if (p < l || p > r) throw new NoSuchElementException("p越界了,p应该在区间中");
         if (l == r && l == p) {                                                // 找到对应的叶子结点, 每个值加上x
             d[u] += x;
             sum[u] += x;
             return ;
         }
         int mid = l + r >> 1;
         if (p <= mid) update(u << 1, l, mid, p, x);                         // 在左部分区间
         else update(u << 1 | 1, mid + 1, r, p, x);                       // 在右部分区间
         push_up(u);                                                            // 回溯，更新本结点
    }
    // 因为gcd不好初始化，所以把所有的情况都考虑一遍就行了
    public static Pair query(int u, int l, int r, int L, int R) {
        if (L > R) throw new NoSuchElementException("区间L 应该 小于R");
        if (L > r || R < l) throw new NoSuchElementException("区间[L, R]和区间[l, r]应该有交集![可能是l，r不能包含所有区间！]");
        if (L <= l && r <= R) return new Pair(sum[u], d[u]);                    // 包含本区间
        int mid = l + r >> 1;
        // 要么全在左，要么全在右，要么横跨两边。
        Pair ans;
        if (R <= mid) ans = query(u << 1, l, mid, L, R);
        else if (L > mid) ans = query(u << 1 | 1, mid + 1, r, L, R);
        else {
            Pair left = query(u << 1, l, mid, L, R);
            Pair right = query(u << 1 | 1, mid + 1, r, L, R);
            ans = new Pair(left.a + right.a, gcd(left.b, right.b));
        }
        ans.b = Math.abs(ans.b);                                            // gcd(a, b) = gcd(a, -b)
        return ans;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] input1 = in.readLine().split(" ");
        int n = Integer.valueOf(input1[0]), m = Integer.valueOf(input1[1]);
        sum = new long[n * 4 + 5];
        d = new long[n * 4 + 5];
        a = new long[n];
        b = new long[n];
        String[] input2 = in.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            a[i] = Long.valueOf(input2[i]);
        }
        // 求差分数组
        b[0] = a[0];
        for (int i = 1; i < n; i++) {
            b[i] = a[i] - a[i - 1];
        }
        build(1, 1, n);
        for (int i = 0; i < m; i++) {
            String[] input3 = in.readLine().split(" ");
            int l = Integer.valueOf(input3[1]);
            int r = Integer.valueOf(input3[2]);
            if (input3[0].equals("Q")) {
                // 如果就一个数字那么直接就是al,否则才是gcd(al, a(l + 1) - a(l)..a(r)-a(r-1))，也就是gcd(al ,gcd(b(l+ 1), ...b(r)))
                long al = query(1, 1, n, 1, l).a;           // 求[1, l]就是a[l]
                if (l < r) {
                    out.write(String.valueOf(gcd(al, query(1, 1, n, l + 1, r).b)) + "\n");
                }
                else out.write(String.valueOf(al) + "\n");
            } else {
                long x = Long.valueOf(input3[3]);
                // 在l的地方，增加x
                update(1, 1, n, l, x);
                // 在r + 1的地方，增加-x, 如果超过了就不需要了
                if (r + 1 < n)
                    update(1,1, n, r + 1, -x);
            }
        }
        out.flush();
        out.close();
        in.close();
    }
}
