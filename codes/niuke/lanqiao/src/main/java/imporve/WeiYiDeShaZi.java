package lanqiao.imporve;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/27 23:31
 * @Description: 唯一的傻子
 */
public class WeiYiDeShaZi {
    private static int maxn = (int)1e6 + 5;
    private static int[] c = new int[maxn];
    private static int[] vis = new int[maxn];
    private static Scanner sc = new Scanner(System.in);

    private static int lowbit(int x) {
        return x & - x;
    }
    public static void add(int x, int num) {
        for (int i = x; i < maxn; i += lowbit(i)) {
            c[i] += num;
        }
    }
    public static int sum(int x) {
        int res = 0;
        for (int i = x; i != 0; i -= lowbit(i)) {
            res += c[i];
        }
        return res;
    }
    public static void main(String[] args) {
        int n = sc.nextInt();
        Arrays.fill(vis, 0);
        for (int i = 1; i <= n; i++) add(i, 1);
        for (int i = 0; i < n - 1; i++) {
            int x = sc.nextInt() + 1;
            int k = sc.nextInt();
            int pre = sum(x);
            int behind = sum(n) - pre;
            int l, r;
            if (behind >= k) {
                k += pre;
                l = x + 1;
                r = n + 1; // [x + 1, n]
            } else {
                k -= behind;
                l = 1;
                r = x + 1;   // [1, x]中
            }
            while (l < r) {
                int mid = l + r >> 1;
                if (sum(mid) >= k) { // 一定是左边界
                    r = mid;
                } else l = mid + 1;
            }
            add(l, -1);         // 别忘了更新
            vis[l] = 1;
        }
        for (int i = 1; i <= n; i++) {
            if (vis[i] == 0) {
                System.out.println(i - 1);
                break;
            }
        }
    }
}
