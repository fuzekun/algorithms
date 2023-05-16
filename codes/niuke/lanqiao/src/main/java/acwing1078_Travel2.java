package lanqiao;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 0:19
 * @Description:
 *
 * 如果不是定义d1[i]为子树的最长结点的话，那么就不好表示最长。
 * 因为没法使用d1[i] + d2[i] + 1或者-1，需要分情况
 * 1. 如果是树叶结点lognway = 1 = d1[i] + d2[i], 且d2[i] = 0
 * 2. 如果不是树叶结点
 *      2.1 如果只有一个孩子 longway = d1[i] + d2[i], 且d2[i] = 0
 *      2.2 如果有多个孩子 longway = d1[i] + d2[i] - 1 且d2[i] += 1
 *
 * 1. 如果本结点是最长路径结点
 *      1.1 如果有多个孩子结点 d2[u] != 0的情况, up[i]= d2[i] + 1
 *      1.2 如果只有一个孩子节点 d2[i] = 0的情况, up[i] = d2[i] + 1
 *
 *     综上所数：直接给d1[i] += 1, d2[i] += 1，然后ans = d1[i] + d2[i] - 1；
 *     up[i] = d2[i] + 1 or d1[i] + 1就行了
 *
 *
 *
 *     如果都是孩子的话就简单了
 *     d1[u] = max(d1[v] + 1)
 *     d2[u] = second(d1[v] + 1)
 *
 *     up[u] = Math.max(d1[u] + 1 or d2[u] + 1, up[u] + 1);
 */
public class acwing1078_Travel2 {

//    private static int[] fat;
    private static List[] g;
    private static int[] d1;
    private static int[] d2;
    private static int[] up;
    private static int[] p;
    private static int ans;
    public static void dfs_down(int u, int fa) {


        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != fa) {
                dfs_down(v, u);
                int dist = d1[v];       // 儿子加上自己，就是子树的最长长度
                if (dist > d1[u]) {
                    d2[u] = d1[u];
                    d1[u] = dist;
                    p[u] = v;
                } else if (d2[u] < dist) {
                    d2[u] = dist;
                }
            }
        }
        d1[u] += 1;
        d2[u] += 1;
        // 返回的是路径的最长长度，d1[u] 和 d2[u]是子树的最长长度
        ans = Math.max(ans, d1[u] + d2[u] - 1);
    }

    public static void dfs_up(int u, int fa) {
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int)g[u].get(i);
            if (v != fa) {
                if (p[u] == v) {
                    up[v] = Math.max(up[u] + 1, d2[u] + 1);
                } else {
                    up[v] = Math.max(up[u] + 1, d1[u] + 1);
                }
                dfs_up(v, u);
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
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
        d1 = new int[n];
        d2 = new int[n];
        p = new int[n];
        up = new int[n];
        dfs_down(0, -1);
        dfs_up(0, -1);
        for (int i = 0; i < n; i++) {
            int[] d = {d1[i], d2[i], up[i]};
            Arrays.sort(d);
            // 加上1就是子树的最长长度 + 自己这个结点就等于ans
            if (d[2] + d[1] - 1 == ans) {
                System.out.println(i);
            }
        }
    }
}
