package leetcode.categories.dp;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/6 21:31
 * @Description:
 * 换根dp
 *
 * dp[u]：自底向上，求所有子树的距离和
 * dp[u] = dp[v] + sz[v]
 * 如果(u, v)是父子关系
 * u和v交换之后，其他的dp值都不会变
 * 1. u的其他孩子不会变
 * 2. v的所有孩子不会变。
 * 因为只和孩子有关，所以，孩子不变，他就不会变。
 * 最终变的只有u和v两个结点。
 *
 * 变化的数量为
 * dp[u] -= (dp[v] + sz[v])
 * 然后此时sz[u]就变成了sz[u] - sz[v]
 * 这样sz和dp就已经完成计算
 * 然后此时, v作为父节点，就需要加上对应的v就行了。
 * dp[v] += (dp[u] + sz[u])
 *
 * 最后每一次循环别忘了回溯。
 *
 */
public class Leet_834_tree_dp {
    private int[]dp;
    private int[]sz;
    private List<Integer>[]g;
    private int[] ans;
    private void dfs(int u, int fa) {
        sz[u] = 1;
        for (Integer v : g[u]) {
            if (v == fa) continue;
            dfs(v, u);
            dp[u] += dp[v] + sz[v];
            sz[u] += sz[v];
        }
    }
    private void redfs(int u, int fa) {
        ans[u] = dp[u];
        int du = dp[u], su = sz[u];
        for (Integer v : g[u]) {
            if (v == fa) continue;
            // 交换u和v
            int dv = dp[v], sv = sz[v];

            dp[u] -= (dp[v] + sz[v]);
            sz[u] -= sz[v];
            dp[v] += (dp[u] + sz[u]);
            sz[v] += sz[u];
            // 递归，现在v仍旧是根，每一层的入口，u都是根
            redfs(v, u);
            // 回溯，为了同层可以使用
            dp[u] = du; sz[u] = su;
            dp[v] = dv; sz[v] = sv;
        }
    }
    public int[] sumOfDistancesInTree(int n, int[][] edges) {
        g = new ArrayList[n];
        ans = new int[n];
        dp = new int[n];
        sz = new int[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        redfs(0, -1);
        return ans;
    }

    public static void main(String[] args) {
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray("[[0,1],[0,2],[2,3],[2,4],[2,5]]");
        int[] ans = new Leet_834_tree_dp().sumOfDistancesInTree(6, numss);
        System.out.println(Arrays.toString(ans));
    }
}
