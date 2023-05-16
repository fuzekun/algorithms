package lanqiao;

import sun.nio.cs.ext.MacHebrew;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2022/10/6 0:27
 * @Description: 生命之树
 * 给一棵树，找一个连通块，使得连通块的总和最大
 */
public class acwing_TreeOfLife {

    private static int[] val;
    private static List[] g;
    private static long ans = Long.MIN_VALUE;
    public static long dfs(int u, int fa) {
//        int maxv = Integer.MIN_VALUE;
//        int second = Integer.MIN_VALUE;
//        for (int i = 0; i < g[u].size(); i++) {
//            int v = (int)g[u].get(i);
//            if (v != fa) {
//                int tmp = dfs(v, u);
//                if (maxv < tmp) {
//                    second = maxv;
//                    maxv = tmp;
//                }
//                else if (maxv == tmp) {
//                    second = maxv;
//                }
//                else if (second < tmp) {
//                    second = tmp;
//                }
//            }
//        }
//        int ret = val[u];
//        if (maxv > 0) ret += maxv;
//        if (second > 0) ret += second;
//        ans = Math.max(ans, ret);
        long ret = val[u];
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int)g[u].get(i);
            if (v == fa) continue;
            ret += Math.max(0L, dfs(v, u));
        }
        ans = Math.max(ans, ret);
        return ret;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        val = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            val[i] = sc.nextInt();
        }
        g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) { // 很明显的一个无根树
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
            g[b].add(a);
        }
        dfs(1, 1);
        System.out.println(ans);

    }
}
