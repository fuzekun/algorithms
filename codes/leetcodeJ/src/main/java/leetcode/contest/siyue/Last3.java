package leetcode.contest.siyue;

/**
 * @author: Zekun Fu
 * @date: 2023/4/30 10:34
 * @Description: 343周赛
 */
import leetcode.everyDay.sanyue.Leet1487;
import leetcode.utils.ChangeToArrayOrList;
import org.jetbrains.annotations.NotNull;

import java.util.*;

public class Last3 {

    class PR<T, P extends Integer> implements Comparable<PR>{
        T a;
        P b;
        public PR(T a, P b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(PR o) {
            return Integer.compare(b, o.b);
        }
    }
//    public int firstCompleteIndex(int[] arr, int[][] mat) {
//        int m = mat.length, n = mat[0].length;
//        int[] lsum = new int[n], rsum = new int[m];
//        Map<Integer, PR>mp = new HashMap<>();
//        for (int i = 0; i < m; i++) {
//            for (int j = 0; j < n; j++) {
//                mp.put(mat[i][j], new PR(i, j));
//            }
//        }
//        for (int i = 0; i < n * m; i++) {
//            PR tmp = mp.get(arr[i]);
//            int x = tmp.a, y = tmp.b;
//            rsum[x]++;
//            lsum[y]++;
//            if (rsum[x] == n || lsum[y] == m) return i;
//        }
//        return -1;
//    }

    public int minimumCost(int[] start, int[] target, int[][] specialRoads) {
        Map<String, List<PR<String, Integer>>>g = new HashMap<>();
        String bg = start[0] + "," + start[1];
        String ed = target[0] + "," + target[1];
        List<PR<String, Integer>>tmp = g.getOrDefault(bg, new LinkedList<>());
        tmp.add(new PR(ed, abs(target[0] - start[0]) + abs(target[1] - start[1])));
        g.put(bg, tmp);
        Set<String>point = new HashSet<>();
        // 1. start需要连接所有的源点
        // 2. 所有的终点，需要连接所有的源点
        // 3. 所有的终点，需要连接所有汇点
        point.add(bg);
        point.add(ed);
        for (int[] vec : specialRoads) {
            String u = vec[0] + "," + vec[1];
            String v = vec[2] + "," + vec[3];
            point.add(u);
            point.add(v);
            tmp = g.getOrDefault(bg, new LinkedList<>());
            tmp.add(new PR(u, abs(vec[0] - start[0]) + abs(vec[1] - start[1])));
            tmp.add(new PR(v, abs(vec[2] - start[0]) + abs(vec[3] - start[1])));
            g.put(bg, tmp);

            tmp = g.getOrDefault(v, new LinkedList<>());
            tmp.add(new PR(ed, abs(vec[2] - target[0]) + abs(vec[3] - target[1])));
            g.put(v, tmp);
            tmp = g.getOrDefault(u, new LinkedList<>());
            tmp.add(new PR(ed, abs(vec[0] - target[0]) + abs(vec[1] - target[1])));

            // 两个哪一个小，就走哪一个,大了没必要走了。一定放在上面语句的后面，因为会覆盖。
            tmp.add(new PR(v, Math.min(vec[4], abs(vec[0] - vec[2]) + abs(vec[1] - vec[3]))));
            g.put(u, tmp);
        }


        // 经典dijstra算法
        HashMap<String, Integer>dist = new HashMap<>();
        PriorityQueue<PR<String, Integer>> que = new PriorityQueue<>();
        que.add(new PR(bg, 0));
        dist.put(bg, 0);

        while (!que.isEmpty()) {
            PR<String, Integer> t = que.poll();
            String u = t.a;
            if (u.equals(ed)) return dist.get(u);
            // 每一个点可以连接所有的点
            for (String v : point) {
                String[] p = v.split(",");
                int vx = Integer.parseInt(p[0]);
                int vy = Integer.parseInt(p[1]);
                p = u.split(",");
                int ux = Integer.parseInt(p[0]);
                int uy = Integer.parseInt(p[1]);
                int cost = abs(vx - ux) + abs(vy - uy);
                if (dist.getOrDefault(v, 0x3f3f3f3f) > dist.get(u) + cost) {
                    dist.put(v, cost + dist.get(u));
                    que.add(new PR(v, dist.get(v)));
                }
            }
            for (PR<String, Integer> t2 : g.getOrDefault(u, new LinkedList<>())) {
                String v = t2.a;
                int cost = t2.b;
                if (dist.getOrDefault(v, 0x3f3f3f3f) > cost + dist.get(u)) {
                    dist.put(v, cost + dist.get(u));
                    que.add(new PR(v, dist.get(v)));
                }
            }
        }
        return -1;
    }
    private int abs(int a) {
        return Math.abs(a);
    }

    public static void main(String[] args) {
        Last3 t = new Last3();
        int ans = t.minimumCost(new int[]{3, 2}, new int[]{5, 7}, ChangeToArrayOrList.changeTo2DIntArray("[[3,2,3,4,4],[3,3,5,5,5],[3,4,5,6,6]]"));
        System.out.println(ans);
    }
}
