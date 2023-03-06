package leetcode.contest.October;

import leetcode.utils.ChangeToArrayOrList;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/10/18 10:21
 * @Description: 双周赛
 */
public class SecondS {

    int limit;
    int[] nums;
    List[] g;
    public int check(int u, int fa) {
        int sz = g[u].size();
        int sum = nums[u];
        for (int i = 0; i < sz; i++) {
            int v = (Integer)g[u].get(i);
            if (v == fa) continue;
            if (check(v, u) == -1) return -1;
            sum += check(v, u);
        }
        if (sum > limit) return -1;
        if (sum == limit) return 0;
        return sum;
    }
    public int componentValue(int[] nums, int[][] edges) {
        this.nums = nums;
        int n = nums.length;
        g = new List[n];
        for (int i = 0; i < n; i++) g[i] = new ArrayList();
        for (int[] edge : edges) {
            int a = edge[0], b = edge[1];
            g[a].add(b);
            g[b].add(a);
        }
        int sum = 0;
        int min_div = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            min_div = Math.max(min_div, nums[i]);
        }
        int ans = 0;
        for (int i = 1; i <= sum / min_div; i++) {
            if (sum % i != 0) continue;
            limit = sum / i;
            if (check(0, -1) == 0)
                ans = i - 1;
        }
        return ans;
    }

    public static void main(String[] args) {
//        [1,2,1,1,1]
//[[0,1],[1,3],[3,4],[4,2]]
        int[] nums = {1,2,1,1,1};
        int[][] edges = ChangeToArrayOrList.changeTo2DIntArray("[[0,1],[1,3],[3,4],[4,2]]");
        System.out.println(new SecondS().componentValue(nums, edges));
    }
}
