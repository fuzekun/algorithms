package lanqiao.chongci;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author: Zekun Fu
 * @date: 2023/3/29 9:45
 * @Description: 简单的树形dp
 *
 *  每一个点孩子结点大于0等于0，必选，小于等于0，必不选。
 *  如果父亲结点想要自己和孩子，那么自己必选。
 *
 *  还是注意long就行了
 */
public class Acwing1220 {

    private static int n;
    private static int[] weight;
    private static ArrayList<Integer>[]g;
    private static long ans = (int)(-1e6) - 5;
    private static long dfs(int u, int fa) {
        long res = weight[u];
        for (int v: g[u]) {
            if (v == fa) continue;
            res += Math.max(dfs(v, u), 0);
        }
        ans = Math.max(ans, res);
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        weight = new int[n];
        g = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            weight[i] = sc.nextInt();
            g[i] = new ArrayList<>();
        }
        for (int i = 0; i < n - 1; i++) {
            int a, b;
            a = sc.nextInt() - 1;
            b = sc.nextInt() - 1;
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        System.out.println(ans);
    }
}
