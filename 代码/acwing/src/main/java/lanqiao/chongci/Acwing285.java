package lanqiao.chongci;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/28 10:40
 * @Description: 没有上司的舞会
 */
public class Acwing285 {
    private static List<Integer>[]g;
    private static int[][] dp;
    private static int[] H;
    private static int dfs(int u, int re) {
        int ans = dp[u][re];
        if (ans > 0) return ans;
        int t1 = H[u] , t2 = 0;
        if (re == 0) {
            for (int v : g[u]) {
                t1 += dfs(v, 1);
            }
        }
        else t1 = 0;
        for (int v : g[u]) {
            t2 += dfs(v, 0);
        }
        ans = Math.max(t1, t2);
        dp[u][re] = ans;
        return ans;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        dp = new int[n][2];
        H = new int[n];
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) H[i] = sc.nextInt();
        int[] child = new int[n];
        for (int i = 0; i < n - 1; i++) {
            int v = sc.nextInt() - 1;
            int u = sc.nextInt() - 1;
            g[u].add(v);
            child[v] = 1;
        }
        int root = -1;
        for (int i = 0; i < n; i++)
            if (child[i] == 0) {
                root = i;
                break;
            }
        System.out.println(Math.max(dfs(root, 0), dfs(root, 1)));
    }
}
