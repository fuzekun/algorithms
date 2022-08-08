package leetcode.categories.graph;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/8/3 20:25
 * @Description: 迷宫中距离入口最近的出口
 */
public class BFS {


    public int nearestExit(char[][] maze, int[] entrance) {
        int[][] dirs = {{-1, 0},{1, 0},{0, -1},{0, 1}};
        int m = maze.length;
        int n = maze[0].length;
        int hh = 0, tt = -1;
        int[] que = new int[n * m * 2]; // 多开空间
        boolean[] vis = new boolean[n * m + 5];
        int[] dist = new int[n * m + 5];
        Arrays.fill(dist, 0x3f3f3f3f);
        int bg = entrance[0] * n + entrance[1];
        dist[bg] = 0;
        que[++tt] = bg;
        while (hh <= tt) {
            int u = que[hh++];
            int x = u / n, y = u % n;
            if (u != bg) {
                if (x == 0 || y == 0 || x == m - 1 || y == n - 1)
                    return dist[u];
            }
            if (vis[u]) continue;
            vis[u] = true;
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i][0], ny = y + dirs[i][1];
                int v = nx * n  + ny;
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && !vis[v] && maze[nx][ny] == '.') {
                    que[++tt] = v;
                    dist[v] = Math.min(dist[v], dist[u] + 1);
                }
            }
        }
        return -1;
    }
    public void bfs(int bg, int[] dist, List<Integer>[]G) {
        Queue<Integer>que = new ArrayDeque<>();
        boolean[] vis = new boolean[G.length];
        Arrays.fill(dist, 0x3f3f3f3f);
        dist[bg] = 0;
        que.add(bg);
        vis[bg] = true;
        while (!que.isEmpty()) {
            int u = que.poll();
            for (int v : G[u]) {
                if (!vis[v]) {
                    vis[v] = true;
                    que.add(v);
                    dist[v] = Math.min(dist[v], dist[u] + 1);
                }
            }
        }
    }
    public int closestMeetingNode(int[] edges, int node1, int node2) {

        int n = edges.length;
        List<Integer>[]G = new List[n];
        for (int i = 0; i < n; i++) G[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            if (edges[i] != -1)
                G[i].add(edges[i]);
        }
        int[] dist1 = new int[n + 1];
        int[] dist2 = new int[n + 1];
        bfs(node1, dist1, G);
        bfs(node2, dist2, G);
        int ans = Integer.MAX_VALUE;
        for (int i= 0; i < n; i++) ans = Math.min(ans,Math.max(dist1[i], dist2[i]));
        return ans < Integer.MAX_VALUE ? ans : -1;
    }



    public static void main(String[] args) {

    }
}
