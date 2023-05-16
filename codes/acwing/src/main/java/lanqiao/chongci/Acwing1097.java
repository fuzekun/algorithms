package lanqiao.chongci;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/27 17:57
 * @Description: 树形dp
 * 换根dp
 */
public class Acwing1097 {
    private static int n, m;
    private static int[] leaf;
    private static List<Integer>[] g;
    private static int[][] dp;
    private static int[][][] f;
    private static int ans;
    // 最多涂多少个结点
//    private static final int INF = (int)2e5;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        m = sc.nextInt();
        n = sc.nextInt();
        leaf = new int[n];
        for (int i = 0; i < n; i++) leaf[i] = sc.nextInt() + 1; // 颜色加上1
        g = new ArrayList[m];
        for (int i = 0; i < m; i++) g[i] = new ArrayList<>(); // 注意初始化
        for (int i = 0; i < m - 1; i++) {
            int a = sc.nextInt() - 1;               // 注意这里结点从0开始变化
            int b = sc.nextInt() - 1;
            g[a].add(b);
            g[b].add(a);
        }
        f = new int[m][3][3];

        int t1 = dfs3(n, -1, 0, 0);
        int t2 = dfs3(n, -1, 1, 0);
        int t3 = dfs3(n, -1, 2, 0);

        System.out.println(Math.min(Math.min(t2, t1), t3));
    }
    /**
     *
     * @param c 当前结点涂的颜色
     * @param re 前一个结点涂的颜色
     */
    private static int dfs3(int u, int fa, int c, int re) {

        int res = f[u][c][re];
        if (res != 0) return res;

        res = c > 0 ? 1 : 0;
        int pre = c > 0 ? c : re;          // 更新上一个涂色的点
        for (int v : g[u]) {
            if (v == fa) continue;
            // 对于叶子结点
            if (v < n) {
                // 如果当前结点没有染色，看前一个结点。否则看当前结点
                if (c == 0) {
                    res += re == leaf[v] ? 0 : 1;
                } else  res += c == leaf[v] ? 0 : 1;
                continue;
            }
            int t1 = dfs3(v, u, 0, pre); // 不涂色
            int t2 = dfs3(v, u, 1, pre);// 白
            int t3 = dfs3(v, u, 2, pre);// 黑
            res += Math.min(t1, Math.min(t2, t3));
        }
        f[u][c][re] = res;
        return res;
    }

    private static void dfs(int u, int fa) {
        if (u < n) {                   // 叶子结点
            dp[u][leaf[u]] = 1;
            return ;
        }
        dp[u][0] = dp[u][1] = 1;       // 给自己上色
        for (int v : g[u]) {
            if (v != fa) {
                dfs(v, u);
                dp[u][0] += dp[v][1];  // 和自己颜色相同的就不用上色了
                dp[u][1] += dp[v][0];
            }
        }
    }
    /**
     * 换根dp
     * */
    private static void dfs2(int u, int fa) {
        int preu0 = dp[u][0], preu1 = dp[u][1];
        for (int v : g[u]) {
            if (v != fa) {

                if (v < n) continue;            // 对于非叶子结点进行换根

                // 换根
                int prev0 = dp[v][0], prev1 = dp[v][1];
                dp[u][0] -= dp[v][1];
                dp[u][1] -= dp[v][0];
                dp[v][0] += dp[u][1];
                dp[v][1] += dp[u][0];
                dfs(v, u);
                // 回溯
                dp[u][0] = preu0; dp[u][1] = preu1;
                dp[v][0] = prev0; dp[v][1] = prev1;
            }
        }
    }
}
