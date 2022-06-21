package leetcode.contest.April;

import leetcode.utils.ChangeToArrayOrList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
*
*   双周赛
* */
public class A_T_2 {


    public int findClosestNumber(int[] nums) {
        int minv = Integer.MAX_VALUE;
        int ans = 0;
        for (int x: nums) {
            if (minv >= Math.abs(x))
            {
                if (minv == Math.abs(x)) {
                    if (x > 0) ans = x;
                }else {
                    minv = Math.abs(x);
                    ans = x;
                }
            }
        }
        return ans;
    }
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        long ans = 0;
        for (int i = 0; i <= total / cost1; i++) {
            ans += (total - i * cost1) / cost2 + 1;
        }
        return ans;
    }

    public int maximumScore(int[] scores, int[][] edges) {
        int n = scores.length;
        // 不可以创建泛型类型的数组，但是可以声明，也可以使用原生类型强制转换。
        List<Integer>[]g = new ArrayList[n];
        Arrays.setAll(g, e->new ArrayList<>()); // e是一个返回值。(e)是一个参数
        for (int[] edge: edges) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }

        for (int i = 0; i < n; i++) {
            if (g[i].size() <= 3) continue;
            int maxv = 0, scnd = 0, third = 0;
            int[] a = new int[3];           // 小于3已经全部放进去了。
            for (int v: g[i]) {
                if (maxv < scores[v]) {
                    third = scnd;
                    scnd = maxv;
                    maxv = scores[v];
                    a[2] = a[1];
                    a[1] = a[0];
                    a[0] = v;
                } else if (scnd < scores[v]) {
                    third = scnd;
                    scnd = scores[v];
                    a[2] = a[1];
                    a[1] = v;
                } else if (third < scores[v]){
                    third = scores[v];
                    a[2] = v;
                }
            }
            g[i] = new ArrayList<>();
            for(int j = 0; j < 3; j++) {
                g[i].add(a[j]);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.println("i = " + i);
            for (int v: g[i]) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
        int ans = 0;
        for (int[] edge: edges) {
            int x = edge[0], y = edge[1];
            for (int a: g[x]) {
                if (a == y)  continue;
                for (int b : g[y]) {
                    if (a == b || b == x) continue;
                    ans = Math.max(scores[a] + scores[b] +scores[x] + scores[y], ans);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        A_T_2 t = new A_T_2();
//        int[] nums = {2,-1};
//        int ans = t.findClosestNumber(nums);
//        System.out.println(ans);

//        System.out.println(t.waysToBuyPensPencils(5,10,10));

        int[] scores = {14,12,10,8,1,2,3,1};
        String s = "[[1,0],[2,0],[3,0],[4,0],[5,1],[6,1],[7,1],[2,1]]";
        int[][] edges = ChangeToArrayOrList.changeTo2DIntArray(s);
        int ans = t.maximumScore(scores, edges);
        System.out.println(ans);
    }



}
