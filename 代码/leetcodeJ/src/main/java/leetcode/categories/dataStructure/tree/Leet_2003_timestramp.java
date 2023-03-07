package leetcode.categories.dataStructure.tree;

import leetcode.utils.ChangeToArrayOrList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/7 19:20
 * @Description: 每棵子树内丢失的最小基因值
 * 时间戳
 *
 * 1. 无根树
 * 2.
 * 如果子树中不包含1，那么就是1
 *
 * dp[i]：表示每棵子树内缺失的最小基因值
 *
 * 换根dp。
 * 维护最小值和次小值。
 * 如果v是u的最小值，那么答案就是次小值
 * 如果v不是u的最小值，那么答案就是u的最小值
 */
public class Leet_2003_timestramp {
    private List<Integer>[]g ;
    private int[] dp1, dp2;         // 最大值和次大值
    private int[] p1, p2;           // p1和p2是对应的孩子结点
    private int[] ans;
    int[] nums;
    private void dfs(int u) {
        dp1[u] = nums[u];
        for (Integer v : g[u]) {
            dfs(v);
            if (dp1[u] > dp1[v]) {                     // 更新最小值
                dp2[u] = dp1[u];
                p2[u] = p1[u];                          // 次小值使用最小值更新
                dp1[u] = dp1[v];                        // 最小值更新为当前
                p1[u] = v;
            }
            else if (dp2[u] > dp1[v]) {                 // 小于等于于最小值的情况下，如果大于次小值，更新次小值
                dp2[u] = dp1[v];
                p2[u] = v;
            }
        }
    }
    private void redfs(int u, int premin) {
        // 不能使用换根dp,但是求最大值和次大值的逻辑是一样的。
        for (Integer v : g[u]) {
            int otherMin;
            if (v == p1[u]) {                           //  如果v是最小值，前面的最小值是次小值
                otherMin = dp2[u];
            }
            else {                                      // 否则，前面的最小值是最小值
                otherMin = dp1[u];
            }
            ans[v] = Math.min(premin, otherMin);
            redfs(v, ans[v]);
        }
    }

    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int n = parents.length;
        this.nums = nums;
        g = new ArrayList[n];
        dp1 = new int[n];
        dp2 = new int[n];
        p1 = new int[n];
        p2 = new int[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != -1)
                g[parents[i]].add(i);
        }
        Arrays.fill(dp1, Integer.MAX_VALUE);
        Arrays.fill(dp2, Integer.MAX_VALUE);
        dfs(0);

        ans = new int[n];
        ans[0] = n + 1;
        redfs(0, Integer.MAX_VALUE);
        Arrays.sort(nums);
        int flag = 0;
        for (int i = 1; i <= n; i++) {
            if (i != nums[i - 1]) {
                // 缺少的最小
                flag = i;
                break;
            }
        }
        // 如果是[1, n]的全排列，那么直接就是答案，否则，得比较缺少的是不是比树中没有的更小。
        if (flag == 0) return ans;
        for (int i = 0; i < n; i++) {
            if (ans[i] > flag) ans[i] = flag;
        }
        return ans;
    }

    public static void main(String[] args) {
//        String s1 = "";
//        String s2 = "";
//[-1,2,3,0,2,4,1]
//[2,3,4,5,6,7,8]
//        [-1,0,0,2]
//[5,3,2,1]
//        [-1,0,0,0,2]
//[6,4,3,2,1]
        String s2 = "[6,4,3,2,1]";

        int[] nums1 = new int[]{-1,0,0,0,2};
        int[] nums2 = ChangeToArrayOrList.changTo1DIntArray(s2);
        Leet_2003_timestramp t = new Leet_2003_timestramp();
        int[] ans = t.smallestMissingValueSubtree(nums1, nums2);
        System.out.println(Arrays.toString(ans));
    }
}
