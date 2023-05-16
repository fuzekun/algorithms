package leetcode.sanuie;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/3/5 8:58
 * @Description: 99 场双周赛
 */
public class contetst_tow_99 {

    public int splitNum(int num) {
        List<Integer>list = new ArrayList<>();
        while (num != 0) {
            list.add(num % 10);
            num /= 10;
        }
        list.sort(Integer::compareTo);
        int n = list.size(), num1 = 0, num2 = 0;
        if (list.size() % 2 == 1) {
            num1 = list.get(0);
            list.remove(0);
            n--;
        }
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) num1 = num1 * 10 + list.get(i);
            else num2 = num2 * 10 + list.get(i);
        }
        return num1 + num2;
    }
    public long coloredCells(int n) {
        if (n == 1) return 1;
        long ans = 1;
//        int add = (n - 1) * 4 + 4;
        for (int i = 1; i < n; i++) {
            long add = (i - 1) * 4 + 4;
            ans += add;
        }
        return ans;
    }
    public int countWays(int[][] ranges) {
        Arrays.sort(ranges, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
        int cnt = 0;
        int pre = -1;
        for (int[] interval : ranges) {
            if (interval[0] <= pre) {
                pre = Math.max(pre, interval[1]);
            }
            else {
                pre = interval[1];
                cnt++;
            }
        }
//        System.out.println(cnt);
        return (int)pow_mod(2, cnt, (int)1e9 + 7);
    }
    private long pow_mod(long a, int b, int mod) {
        long res = 1;
        while (b != 0) {
            if ((b & 1) == 1) res = res * a  % mod;
            b >>= 1;
            a = a * a % mod;
        }
        return res;
    }
    private List<Integer> [] g;
    class Pair {
        public int a, b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;          // 类相同
            Pair pair = (Pair) o;
            return a == pair.a &&                                               // 值相同
                    b == pair.b;
        }
        public Pair(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }
    }
    private HashMap<Pair, Integer>mp = new HashMap<>();
    public int rootCount(int[][] edges, int[][] guesses, int k) {

        // 1. 无根树转化成有根树
        // 2. 任意选择一个作为树根
        // 3. 判断有几个满足guesses中的父子关系，如果大于等于k,答案加上1
        int n = edges.length + 1;
        g = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        for (int[] guess : guesses) {
            int a = guess[0], b = guess[1];
            mp.put(new Pair(a, b), 1);
        }
        dfs(0, -1);
        System.out.println(cnt0);
        redfs(0, -1, cnt0, k);
        return res;
    }
    private int cnt0;
    private void dfs(int u, int fa) {
        for (Integer v : g[u]) {
            if (v == fa) continue;
            dfs(v, u);
            cnt0 += mp.getOrDefault(new Pair(u, v), 0);
        }
    }
    private int res = 0;
    private void redfs(int u, int fa, int cnt, int k) {
        res += cnt >= k ? 1 : 0;
        for (Integer v: g[u]) {
            if (v == fa) continue;
            redfs(v, u, cnt - mp.getOrDefault(new Pair(u, v), 0) + mp.getOrDefault(new Pair(v, u), 0), k);
        }
    }

    public static void main(String[] args) throws Exception{
        contetst_tow_99 con = new contetst_tow_99();
        int[] nums = {};
        String[] ss = {};
//        int ans1 = con.splitNum(1001);
//        System.out.println(ans1);
//        System.out.println(con.coloredCells(69675));
        int[][] edges = ChangeToArrayOrList.changeTo2DIntArray("[[0,1],[1,2],[1,3],[4,2]]");
        int[][] guesses = ChangeToArrayOrList.changeTo2DIntArray("[[1,3],[0,1],[1,0],[2,4]]");
        int k = 3;
        System.out.println(con.rootCount(edges, guesses, k));
    }
}
