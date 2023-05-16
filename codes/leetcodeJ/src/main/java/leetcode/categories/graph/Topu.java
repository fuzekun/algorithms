package leetcode.categories.graph;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/8/29 8:12
 * @Description: 拓扑排序的模板
 */
public class Topu {

    private int n;
    private int[] c;
    private List[] G;
    private int t;
    private int[] topu;
    public Topu(int _n, int[][] nums) {
        n = _n;
        G = new List[n];
        System.out.println(_n);
        for (int i = 0; i < _n; i++) {
            G[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < nums.length; i++) {
            int a = nums[i][0], b = nums[i][1];
            G[a].add(b);
        }
    }
    @SuppressWarnings("unchecked")
    private boolean dfs(int u) {
        c[u] = -1;
        for (Integer v : (List<Integer>) G[u]) {
            if (c[v] < 0) return false;
            if (c[v] == 0 && !dfs(v)) return false;
        }
        topu[--t] = u;
        c[u] = 1;
        return true;
    }

    public int[] topuSort() {
        c = new int[n];
        Arrays.fill(c, 0);
        t = n;
        topu = new int[n];
        for (int i = 0; i < n; i++) {
            if (c[i] == 0) {
                if (!dfs(i))
                    return new int[0];
            }
        }
        return topu;
    }

    public static void preSolve(int[][] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[i].length; j++) {
                nums[i][j] -= 1;
            }
        }
    }
    public static void main(String[] args) {
        int[][] nums1 = {{1,2},{3,2}};
        int[][] nums2 = {{2,1},{3,2}};
        preSolve(nums1);
        preSolve(nums2);
        int k = 3;
        Topu t = new Topu(k, nums1);
        Topu t2 = new Topu(k, nums2);
        int[] rn = t.topuSort();
        int[] cn = t2.topuSort();
        for (int x :rn) {
            System.out.print ((x + 1) + " ");
        }
        System.out.println();
        for (int x :cn) {
            System.out.print ((x + 1) + " ");
        }
        System.out.println();
        HashMap<Integer, Integer> mp = new HashMap<>();
        int[][] ans = new int[k][k];
        for (int i = 0; i < k; i++) {
            mp.put(rn[i], i);
        }
        for (int j = 0; j < k ; j++) {
            ans[mp.get(cn[j])][j] = cn[j] + 1;
        }
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < k; j++) {
                System.out.print(ans[i][j] + " ");
            }
            System.out.println();
        }
    }
}
