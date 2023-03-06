package leetcode.everyDay.October;

import leetcode.utils.ChangeToArrayOrList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/10/16 9:21
 * @Description:
 */
public class leet866_dislike {
    // 二分图匹配

    private List[]g;
    int[] color;
    private boolean match(int u, int c) {
        color[u] = c;
        // 所有的点都没有矛盾
        for (int v : (Integer[])g[u].toArray(new Integer[g[u].size()])) {
            if (color[v] == 0) {
                if (!match(v, 3 - c))
                    return false;
            } else if (c == color[v])
                return false;
        }
        return true;
    }
    public boolean possibleBipartition(int n, int[][] dislikes) {
        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            g[i] = new ArrayList();

        for (int[] edge : dislikes) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        color = new int[n + 1];
        Arrays.fill(color, 0);
        for (int i = 1; i <= n; i++)
            if (color[i] == 0)            // 初始的颜色随便选
                if (!match(i, 1))
                    return false;

        return true;
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] arr = ChangeToArrayOrList.changeTo2DIntArray("[[1,2],[1,3],[2,4]]");

        boolean ans = new leet866_dislike().possibleBipartition(n, arr);
        System.out.println(ans);
    }
}
