package leetcode.categories.dp;

import leetcode.utils.ReadData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/10/7 15:42
 * @Description:
 * leetcode 1254树的直径问题
 */
public class leet1254_TreeZhiJing {

    private List<Integer>[] g;
    private int ans;
    private int dfs(int u, int fa) {
        int maxv = 0, second = 0;
        for (int v:g[u]) {
            if (v != fa) {
                int val = dfs(v, u) + 1;
                if (maxv < val) {
                    second = maxv;
                    maxv = val;
                } else if (second < val) {
                    second = val;
                }
            }
        }
        ans = Math.max(maxv + second, ans);
        return maxv;
    }
    public int treeDiameter(int[][] edges) {
        int n = edges.length + 1;
        g = new ArrayList[n];       // 为什么泛型数组不能被定义
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        dfs(0, -1);
        return ans;
    }
    public static void main(String[] args) throws Exception{
        System.out.println(new leet1254_TreeZhiJing().treeDiameter(ReadData.get2DArray()));

    }
}
