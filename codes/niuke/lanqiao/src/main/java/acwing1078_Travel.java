package lanqiao;

import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/6 10:48
 * @Description: acwing1078旅游规划
 */
public class acwing1078_Travel {

    private static Map<Integer, Integer>vis = new HashMap<>();
    private static int longway = 1;
    private static int ans = 1;
    private static int[] fat;
    private static PriorityQueue<Integer>que = new PriorityQueue<>();
    private static List[] maxs;
    private static List[] g;
    private static List<Integer>maxp = new ArrayList<>();
    private static int[] d1, d2;
    public static int getLongWay(int u, int fa) {
        int maxv = 0;
        int second = 0;
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != fa) {
                int dist = getLongWay(v, u);
                if (dist > maxv) {
                    second = maxv;
                    maxv = dist;
                } else if (dist > second) {
                    second = dist;
                }
            }
        }
//        d1[u] = 1;
//        for (int i = 0; i < g[u].size(); i++) {
//            int v = (int)g[u].get(i);
//            if (v != fa) {
//                getLongWay(v, u);
//                int dist = d1[v];
//                if (dist > d1[u]) {
//                    d2[u] = d1[u];
//                    d1[u] = dist;
//                } else if (dist > d2[u]) d2[u] = dist;
//            }
//        }
//        longway = Math.max(longway, d1[u] + d2[u] + 1);
        longway = Math.max(longway, maxv + second + 1);
        return maxv + 1;

    }
    public static int dfs(int u, int fa) {
        // 最长路和次长路都是有就加上，没有就是0喽。
        fat[u] = fa;
        int maxv = 0;
        int second = 0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int)g[u].get(i);
            if (v != fa) {
                int tmp = dfs(v, u);
                mp.putIfAbsent(v, tmp);
                if (maxv < tmp) {
                    second = maxv;
                    maxv = tmp;
                }
                else if (maxv == tmp) {
                    second = maxv;
                }
                else if (second < tmp) {
                    second = tmp;
                }
            }
        }
        for (int i = 0; i < g[u].size(); i++) {
            int v = (int) g[u].get(i);
            if (v != fa) {
                int val = mp.get(v);
                if (val == maxv || val == second) {
                    maxs[u].add(v);
                }
            }
        }
        int ret = 1;
        ret += maxv + second;
        if (ans < ret) {
            ans = ret;
            maxp.clear();
            maxp.add(u);
        }
        else if (ans == ret) {
            maxp.add(u);
        }
        // 注意返回值，应该是求的maxv,而不是最长路径，是最高高度。
        return maxv + 1;
    }
    public static void summary(int u) {
        for (int i = 0; i < maxs[u].size(); i++) {
            int v = (int)maxs[u].get(i);
            if (v != fat[u]) {
                que.add(v);
                if (!vis.containsKey(v)) {
                    summary(v);
                    vis.put(v, 1);
                } else {
                    System.out.println("w" + u);
                }
            }
        }
    }
//    @Test
//    public void test() throws Exception{
//        System.out.println(System.getProperty("user.dir"));
//        BufferedReader bf = new BufferedReader(new FileReader(new File("files/inputOfTravel.txt")));
//        int ans = 0;
//        String s;
//        while ((s = bf.readLine()) != null) {
//            ans += 1;
//            System.out.println(s);
//        }
//        System.out.println("总行数为" + ans);
//    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        g = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < n - 1; i++) { // 很明显的一个无根树
            int a = sc.nextInt();
            int b = sc.nextInt();
            g[a].add(b);
            g[b].add(a);
        }

        // 这样可以求出最长路径，但是没法求出最长路径的编号。
        // 可以返回最长路径的结点, 没必要
        // 只要在最长路径和次长路径上的点，就都是最长结点。
        fat = new int[n];
        maxp = new ArrayList<>(n);
        maxs = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            maxs[i] = new ArrayList();
        }
        dfs(0, 0);
        d1 = new int[n];
        d2 = new int[n];
//        getLongWay(0, 0);
//        System.out.println(longway);
        for (int i = 0; i < maxp.size(); i++) {
            int p = maxp.get(i);
            que.add(p);
            summary(p);
        }
        while (!que.isEmpty()) {
            System.out.println(que.poll());
        }
    }
}
