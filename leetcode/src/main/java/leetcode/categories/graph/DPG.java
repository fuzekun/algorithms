package leetcode.categories.graph;

import javax.lang.model.type.ArrayType;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/8/4 9:14
 * @Description:
 * Leetcode 847 货郎担问题
 * 动态规划 + 图论的问题
 *
 * dp[i] : 表示访问前i个结点所需要的最短路径
 *
 * 假设已经知道了访问所有结点需要的最小距离。
 * 那么又来了一个新的结点，最小距离等于什么呢？
 *
 *  对每一个状态进行一次放缩，也就是加上这个点之后
 *  需要的结点的数量。
 *
 *  dp[S]:表示访问集合S需要的边的数目。
 *  遍历所有不在集合中的点，用最短路径的思想。
 *  把这个点加入集合中，会引起什么变化呢？
 *  所有原来的状态都需要更新一个新的状态，然后加上1。
 *
 */
public class DPG {
    public int shortestPathLength(int[][] graph) {
        Queue<int[]>que = new LinkedList<>();
        int n = graph.length;
        boolean[][] vis = new boolean[n][1<<n];
        for (int i = 0; i < n; i++)
        {
            vis[i][1 << i] = true;
            que.add(new int[]{i, 1 << i, 0});
        }
        while (!que.isEmpty()) {
            int[] f = que.poll();
            int u = f[0], dist = f[2], mask = f[1];
            if (mask == (1 << n) - 1) return dist;
            for (int v : graph[u]) {
                int nmask = mask | (1 << v);
                if (!vis[v][nmask]) {
                    vis[v][nmask] = true;
                    que.offer(new int[]{v, nmask, dist + 1});
                }
            }
        }
        return -1;
    }
}
