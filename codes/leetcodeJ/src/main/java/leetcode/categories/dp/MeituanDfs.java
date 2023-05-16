package leetcode.categories.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/19 21:43
 * @Description: 美团dfs题目
 */
public class MeituanDfs {

    private static int[] ans;
    private static List<Integer>g[];
    private static void dfs(int u, int fa, int dist) {
        ans[u]++;
        if (dist == 0) return ;
        for (int v : g[u]) {
            if (v == fa) continue;
            dfs(v, u, dist - 1);
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dist = new int[n];
        ans = new int[n];
        for (int i = 0; i < n; i++) {
            dist[i] = sc.nextInt();
        }
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < n - 1; i++) {
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            g[a - 1].add(b - 1);
            g[b - 1].add(a - 1);
        }
        for (int i = 0; i < n; i++)
            dfs(i, -1, dist[i]);
        for (int i = 0; i < n; i++) {
            System.out.print(ans[i] + " ");
        }
        System.out.println();
    }
}
