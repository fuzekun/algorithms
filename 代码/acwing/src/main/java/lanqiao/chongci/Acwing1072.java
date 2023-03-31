package lanqiao.chongci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/28 15:15
 * @Description: 树的最长直径
 * 1. 换根dp
 * 2. 两次bfs
 */
public class Acwing1072 {
    private static class Pair {
        int a, b;
        Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }
    private static int n;
    private static List<Pair>[]g;
    private static int[][] dp;
    private static int[][] child;
    private static int ans = 0;

    private static void change(int u, int c, int v) {
        if (dp[u][0] <= c) {
            dp[u][1] = dp[u][0];
            child[u][1] = child[u][0];
            dp[u][0] = c;
            child[u][0] = v;
        } else if (dp[u][1] <= c) {
            dp[u][1] = c;
            child[u][1] = v;
        }
    }
    private static void dfs(int u, int fa) {
        for (Pair p : g[u]) {
            int v = p.a, w = p.b;
            if (v == fa) continue;
            dfs(v, u);
            change(u, dp[v][0] + w, v);
        }
    }
    private static void redfs(int u, int fa) {
        ans = Math.max(ans, dp[u][0] + dp[u][1]);
        for (Pair p : g[u]) {
            int v = p.a, w = p.b;
            if (v == fa) continue;
            int preu_max = dp[u][0];
            int prev_max = dp[v][0];
            if (child[u][0] == v) dp[u][0] = dp[u][1];
            dp[v][0] = Math.max(dp[v][0], dp[u][0] + w);
            redfs(v, u);
            dp[u][0] = preu_max;
            dp[v][0] = prev_max;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        g = new ArrayList[n];
        dp = new int[n][2];
        child = new int[n][2];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int a, b, w;
            a = sc.nextInt() - 1;
            b = sc.nextInt() - 1;
            w = sc.nextInt();
            g[a].add(new Pair(b, w));
            g[b].add(new Pair(a, w));
        }
        dfs(0, -1);
        redfs(0, -1);
        System.out.println(ans);
    }
}

