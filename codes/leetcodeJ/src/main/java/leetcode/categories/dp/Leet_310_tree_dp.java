package leetcode.categories.dp;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/6 22:29
 * @Description: 换根dp
 */

/*
*
*   记录最大值和次大值对应的点
* 如果v是u的最大值，那么换根之后u的高度等于次大值，并且v的高度等于u的高度加上1
* 如果v不是u的最大值,那么换根之后u的高度不变，并且v的高度等于u现在的高度加上1
*
*
* */
public class Leet_310_tree_dp {
    private List<Integer>[]g ;
    private int[] dp1, dp2;         // 最大值和次大值
    private int[] p1, p2;           // p1和p2是对应的孩子结点
    private List<Integer> ans;
    private int height;
    private void dfs(int u, int fa) {
        for (Integer v : g[u]) {
            if (v == fa) continue;
            dfs(v, u);
            if (dp1[u] < dp1[v] + 1) {                  // 更新最大值
                dp2[u] = dp1[u];
                p2[u] = p1[u];                          // 最大值变成次大值
                dp1[u] = dp1[v] + 1;                    // 次大值更新为当前
                p1[u] = v;
            }
            else if (dp2[u] < dp1[v] + 1) {            // 小于最大值的情况下，如果大于次大值，更新次大值
                dp2[u] = dp1[v] + 1;
                p2[u] = v;
            }
        }
    }
    private void redfs(int u, int fa) {
        if (dp1[u] < height) {
            height = dp1[u];
            ans = new ArrayList<>();        // 不用手动释放，有GC
            ans.add(u);
        }
        else if (dp1[u] == height) {
            ans.add(u);
        }
        int predp1 = dp1[u], predp2 = dp2[u];
        int prep1 = p1[u], prep2 = p2[u];
        for (Integer v : g[u]) {
            if (v == fa) continue;
            int prevdp1 = dp1[v], prevdp2 = dp2[v];
            int prevp1 = p1[v], prevp2 = p2[v];

            if (p1[u] == v) {
                dp1[u] = dp2[u];                    // 最大值变成次大值
            }
            if (dp1[v] < dp1[u] + 1) {              // 更新此时的父亲
                dp2[v] = dp1[v];
                p2[v] = p1[v];
                dp1[v] = dp1[u] + 1;
                p1[v] = u;
            } else if (dp2[v] < dp1[u] + 1) {
                dp2[v] = dp1[u] + 1;
                p2[v] = u;
            }
            redfs(v, u);
            dp1[u] = predp1; dp2[u] = predp2;
            p1[u] = prep1; p2[u] = prep2;
            dp1[v] = prevdp1; dp2[v] = prevdp2;
            p1[v] = prevp1; p2[v] = prevp2;
        }
    }
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        g = new ArrayList[n];
        dp1 = new int[n];
        dp2 = new int[n];
        p1 = new int[n];
        p2 = new int[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);

        ans = new ArrayList<>();
        height = dp1[0];
        redfs(0, -1);
        return ans;
    }

    public static void main(String[] args) {
        int[][] numss = ChangeToArrayOrList.changeTo2DIntArray(" [[1,0],[1,2],[1,3]]");
        int n = 4;
        List<Integer>ans = new Leet_310_tree_dp().findMinHeightTrees(n, numss);
        System.out.println(Arrays.toString(ans.toArray()));
    }

}
