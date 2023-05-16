package lanqiao.chongci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/28 15:52
 * @Description:
 */
public class Acwing323 {
    private static int n;
    private static List<Integer>[]g;
    private static int[][] dp;
    private static int[][][]f;
    private static int ans;
    private static void dfs(int u, int fa) {
        dp[u][1] = 1;
        for (int v : g[u]) {
            if (v == fa) continue;
            dfs(v, u);
            dp[u][1] += dp[v][0];
            dp[u][0] += dp[v][1];
        }
    }

    private static void redfs(int u, int fa) {
        ans = Math.min(dp[u][0], dp[u][1]);
        for (int v : g[u]) {
            if (v == fa) continue;
            int preu0 = dp[u][0], preu1 = dp[u][1];
            int prev0 = dp[v][0], prev1 = dp[v][1];
            dp[u][0] -= dp[v][1]; dp[u][1] -= dp[v][0];
            dp[v][0] += dp[u][1]; dp[v][1] += dp[u][0];

            redfs(v, u);

            dp[u][0] = preu0; dp[u][1] = preu1;
            dp[v][0] = prev0; dp[v][1] = prev1;
        }
    }
    /**
     *
     * @param u 结点
     * @param re  前一个结点的颜色
     * */
    private static int dfs3(int u, int re) {
        if (g[u].size() == 0)
            return re == 1 ? 0 : 1;
        int ans = dp[u][re];
        if (ans > 0) return ans;
        ans = 0;
        // 如果父亲涂色，自己可以不涂色，可以涂色
        if (re == 1) {
            int t1 = 0, t2 = 1;
            for (int v : g[u]) {
                t1 += dfs3(v, 0);
                t2 += dfs3(v, 1);
            }
            ans = Math.min(t1, t2);
        }
        // 如果父亲不涂色，自己只能涂色
        else {
            for (int v : g[u]) {
                ans += dfs3(v, 1);
            }
            ans += 1;
        }
        dp[u][re] = ans;
        return ans;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            n = Integer.parseInt(sc.nextLine());
            dp = new int[n][2];
            g = new ArrayList[n];
            for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
            int[] notRoot = new int[n];
            for (int i = 0; i < n; i++) {
                String[] input = sc.nextLine().split(" ");
                int u = Integer.parseInt(input[0].split(":")[0]);
                for (int j = 1; j < input.length; j++) {
                    int v = Integer.parseInt(input[j]);
                    g[u].add(v);
                    notRoot[v] = 1;
                }
            }
            int root = 0;
            for (int i = 0; i < n; i++) if (notRoot[i] == 0) { root = i; break; }
            System.out.println(Math.min(dfs3(root, 0), dfs3(root, 1)));
            //            dfs3(root);
//            System.out.println(Math.min(dp[root][0], dp[root][1]));
        }
    }
}
