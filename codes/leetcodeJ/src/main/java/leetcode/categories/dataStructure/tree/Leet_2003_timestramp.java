package leetcode.categories.dataStructure.tree;

import leetcode.utils.ChangeToArrayOrList;

import javax.jws.Oneway;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

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
 * dp[i]：表示每棵子树内缺失的最小基因值       -> 本题应该是含有的最小值
 *
 * 换根dp。
 * 维护最小值和次小值。
 * 如果v是u的最小值，那么答案就是次小值
 * 如果v不是u的最小值，那么答案就是u的最小值
 *
 *
 * 1. 如果是排列的情况下，子树外缺失的最小值 = min(父节点的其他儿子结点的最小值，父节点的祖先结点最小值)
 * 2. 如果不是排列的，除了本棵树之外，还有nums中缺失的最小值。
 *
 *
 * 所以不管是否含有重复的值，那么都可以通过两次dfs，记录最小值和次小值解决。
 * 唯一需要注意的点就是要和nums中缺失的最小值比较。
 *
 *
 *
 * 本题目需要注意的性质是不存在重复。所以只要找到1所在的链，只有本条链中的答案不是1。
 * 那么对于链上每一个点，只需要遍历其子树，将已经有的基因值使用set保存。
 * 然后维护一个全局的Mex，每次遍历完子树后，看mex是否在里面，如果在，就让mex。
 *最后一步是因为缺失的最小基因值，在本条链上是递增的。因为父节点包含孩子结点所有的基因值
 */
public class Leet_2003_timestramp {
    private List<Integer>[]g ;
    int[] nums;
    private int[] dp1, dp2;         // 最大值和次大值
    private int[] p1, p2;           // p1和p2是对应的孩子结点
    private int[] ans;

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

    //
//    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
//        int n = parents.length;
//        this.nums = nums;
//        g = new ArrayList[n];
//        dp1 = new int[n];
//        dp2 = new int[n];
//        p1 = new int[n];
//        p2 = new int[n];
//        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
//        for (int i = 0; i < parents.length; i++) {
//            if (parents[i] != -1)
//                g[parents[i]].add(i);
//        }
//        Arrays.fill(dp1, Integer.MAX_VALUE);
//        Arrays.fill(dp2, Integer.MAX_VALUE);
//        dfs(0);
//
//        ans = new int[n];
//        ans[0] = n + 1;
//        redfs(0, Integer.MAX_VALUE);
//        Arrays.sort(nums);
//        int flag = 0;
//        for (int i = 1; i <= n; i++) {
//            if (i != nums[i - 1]) {
//                // 缺少的最小
//                flag = i;
//                break;
//            }
//        }
//        // 如果是[1, n]的全排列，那么直接就是答案，否则，得比较缺少的是不是比树中没有的更小。
//        if (flag == 0) return ans;
//        for (int i = 0; i < n; i++) {
//            if (ans[i] > flag) ans[i] = flag;
//        }
//        return ans;
//    }
    public int[] smallestMissingValueSubtree(int[] parents, int[] nums) {
        int bg = -1;
        int n = nums.length;
        g = new ArrayList[n];
        this.nums = nums;
        for (int i = 0; i < n; i++) g[i] = new ArrayList<>();
        for (int i = 0; i < parents.length; i++) {
            if (parents[i] != -1)
                g[parents[i]].add(i);
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] == 1) {
                bg = i;
            }
        }

        int[] ans = new int[n];
        Arrays.fill(ans, 1);
        if (bg == -1)
            return ans;
        HashSet<Integer>set = new HashSet<>();
        int mex = 1;
        for (int i = bg; i != -1; i = parents[i]) {
            f(i, set);
            while (set.contains(mex)) mex++;
            ans[i] = mex;
        }
        return ans;
    }
    private void f(int u, HashSet<Integer>set) {
        set.add(nums[u]);
        for (Integer v : g[u]) {
            if (!set.contains(nums[v])) {               // 这个也是因为基因值不同，所以遍历过就不用进行遍历的
                f(v, set);
            }
        }
    }


    public static void main(String[] args) {
        String s2 = " [1,2,3,4]";                             // nums
        String s1 = " [-1,0,0,2]";                          // parents

        int[] nums1 = ChangeToArrayOrList.changTo1DIntArray(s1);
        int[] nums2 = ChangeToArrayOrList.changTo1DIntArray(s2);
        Leet_2003_timestramp t = new Leet_2003_timestramp();
        int[] ans = t.smallestMissingValueSubtree(nums1, nums2);
        System.out.println(Arrays.toString(ans));
//
        List<List<List<Integer>>>list = new ArrayList<>();
        List<Integer> in1 = new ArrayList<>();
        in1.add(1);
        List<List<Integer>>in2 = new ArrayList<>();
        in2.add(in1);
        list.add(in2);
        int[][][] array = list.stream().
                map(
                        l -> l.stream().map(
                                x->x.stream().mapToInt(Integer::valueOf).toArray()               // 创建一维数组
                        ).toArray(int[][]::new)                                           // 创建二维数组
                ).toArray(int[][][]::new);                                                 // 创建三维数组

        System.out.println(array[0][0][0]);
    }

}
