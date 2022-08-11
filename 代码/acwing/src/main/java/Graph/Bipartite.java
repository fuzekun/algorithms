package Graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Queue;

/**
 * @author: Zekun Fu
 * @date: 2022/8/8 17:05
 * @Description: 判断是否是二分图，使用二分图匹配算法
 *
 * 1. 使用策略模式
 * 2. 保证没有重边和自环
 */

@FunctionalInterface
interface JudgeMethod {
    public boolean isBipartite(int[][] grapth);
}

class JudegeBfs implements JudgeMethod{
    private int[] color;
    private int[][] gapth;
    private int n;

    private boolean bfs(int bg) {
        Queue<Integer> que = new ArrayDeque<>();
        color[bg] = 1;
        que.add(bg);

        while (!que.isEmpty()) {
            int u = que.poll();

            for (int v : gapth[u]) {
                if (color[v] == color[u]) return false;
                if (color[v] != 0) continue; // 颜色和它一样不用染色
                color[v] = 3 - color[u];
                que.add(v);
            }
        }
        return true;
    }
    @Override
    public boolean isBipartite(int[][] grapth) {
        this.gapth = grapth;
        for (int i = 0; i < n; i++) {
            if (color[i] == 0 && !bfs(i)) {
                return false;
            }
        }
        return true;
    }
}

class JudegeDfs implements JudgeMethod{
    private int[] color;
    private int[][] gapth;
    private int n;

    private boolean dfs(int u) {
        for (int v :gapth[u]) {
            if (color[u] == color[v]) return false;
            if (color[v] != 0) continue;
            color[v] = 3 - color[u];
            if (!dfs(v))
                return false;
        }
        return true;
    }
    @Override
    public boolean isBipartite(int[][] grapth) {
        this.gapth = grapth;
        for (int i = 0; i < n; i++) {
            if (color[i] == 0) {
                color[i] = 1;
                if (!dfs(i))
                    return false;
            }
        }
        return true;
    }
}

public class Bipartite {

    public static boolean isBipartite(int[][] graph, JudgeMethod m) {
        return m.isBipartite(graph);
    }

}

class Test {
    public static void main(String[] args) {
        // 保证图没有重边，没有自环，无向图， 不保证连通。
        int[][] G = {
                {1,2,3},
                {4,5,6}
        };

        boolean ans1 = Bipartite.isBipartite(G, new JudegeDfs());  // 使用bfs
        boolean ans2 = Bipartite.isBipartite(G, new JudegeBfs());  // 使用dfs
        boolean ans3 = Bipartite.isBipartite(G, new JudgeMethod() {
            @Override
            public boolean isBipartite(int[][] grapth) {
                return false;
            }
        });                                         // 用户自定义
        System.out.println(ans1);
        System.out.println(ans2);
        System.out.println(ans3);
    }
}
