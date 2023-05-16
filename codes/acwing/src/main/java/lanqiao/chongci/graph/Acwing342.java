package lanqiao.chongci.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

import utils.InAndOutUitl;

/**
 *
 * 1. 负数
 * 2. 没有负环
 * 3. 单个源点求最短路
 * 4. 有重边
 * spfs
 * */
public class Acwing342{

    static class PR implements Comparable<PR> {
        int x, y;
        public PR(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(PR o) {
            return Integer.compare(x, o.x);
        }
    }

    public static void main(String[] args) throws Exception {
        InAndOutUitl util = new InAndOutUitl();
        int n = util.nextInt();
        int p = util.nextInt();
        int r = util.nextInt();
        int s = util.nextInt();
        List<PR>[] g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < p + r; i++) {
            int a = util.nextInt(), b = util.nextInt(), w = util.nextInt();
            if (i < p) {
                g[a].add(new PR(b, w));
                g[b].add(new PR(a, w));
            } else {
                g[a].add(new PR(b, w));
            }
        }
        

        Deque<Integer>que = new ArrayDeque();
        int[] dist = new int[n + 1];
        int[] st = new int[n + 1];

        Arrays.fill(dist, 0x3f3f3f3f);
        dist[s] = 0;
        st[s] = 1;
        que.addLast(s);

        while (!que.isEmpty()) {
            // 头存储着距离最小的结点
            int u = que.pollFirst();
            st[u] = 0;
            for (PR nx : g[u]) {
                int v = nx.x, w = nx.y;
                if (dist[v] > dist[u] + w) {
                    dist[v] = dist[u] + w;
                    if (st[v] == 0) {
                        st[v] = 1;
                        // 如果小于头，就放头,否则放在尾
                        if (que.isEmpty() || dist[que.peekFirst()] > dist[v]) {
                            que.addFirst(v);
                        }
                        else que.addLast(v);
                    }
                }
            }
        }
        for (int i = 1; i <= n; i++) {
            if (dist[i] == 0x3f3f3f3f) util.write("NO PATH\n");
            else util.write(dist[i] + "\n");
        }
        util.flush();
    }
}