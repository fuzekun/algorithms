package leetcode.everyDay.October;

import leetcode.utils.ChangeToArrayOrList;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/22 8:49
 * @Description: 规划兼职工作
 *
 * 离散化 + 树状数组
 */
public class leet1235 {

    // 方法1：最长上升子序列模型
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<int[]>nums = new ArrayList<>();
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            nums.add(new int[]{endTime[i], startTime[i], profit[i]});
        }
        nums.sort((x, y)->Integer.compare(x[0], y[0]));
        int[][]arrs = nums.toArray(new int[nums.size()][]);
        int[] dp = new int[n + 2];
        dp[0] = 0;
        for (int i = 0; i < n; i++) {
            int start = arrs[i][1];
            int profile = arrs[i][2];
            int l = 0, r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (arrs[mid][0] <= start) l = mid + 1;
                else r = mid;
            }
            dp[i + 1] = Math.max(dp[l] + profile, dp[i]);
        }
        return dp[n];
    }
    private static final int maxn = (int)20 + 5;
    private int[]c = new int[maxn];
    private int lowbit(int x) {
        return x & -x;
    }
    private void update(int x, int num) {
        for (int i = x; i < maxn ; i += lowbit(i))
            c[i] = Math.max(c[i], num);
    }
    private int getMax(int x) {
        int ans = 0;
        for (int i = x; i != 0; i -= lowbit(i)) {
            ans = Math.max(ans, c[i]);
        }
        return ans;
    }
    // 方法2：离散化 + 树状数组
    int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        HashSet<Integer>set = new HashSet<>();
        int n = startTime.length;
        for (int i = 0; i < n; i++) {
            set.add(startTime[i]);
            set.add(endTime[i]);
        }
        List<Integer>list = new ArrayList<>(set);
        list.sort(Integer::compareTo);
        int cnt = 0;
        HashMap<Integer, Integer>mp = new HashMap<>();
        for (int x: list) {
            mp.put(x, ++cnt);
        }
        List<int[]>nums = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            nums.add(new int[]{endTime[i], startTime[i], profit[i]});
        }
        nums.sort((x, y)->Integer.compare(x[0], y[0]));
        int[][]arrs = nums.toArray(new int[nums.size()][]);
        // 计算从[1,startTime]的最大值，使用树状数组
        int ans = 0;
        for (int i = 0; i < n; i++) {
            int end = mp.get(arrs[i][0]), start = mp.get(arrs[i][1]), money = arrs[i][2];
            int maxv = getMax(start) + money;
            ans = Math.max(ans, maxv);
            update(end, maxv);
        }
        return ans;
    }


    public static void main(String[] args) {

        int[] arr1 = ChangeToArrayOrList.changTo1DIntArray("[6,15,7,11,1,3,16,2]");
        int[] arr2 = ChangeToArrayOrList.changTo1DIntArray("[19,18,19,16,10,8,19,8]");
        int[] arr3 = ChangeToArrayOrList.changTo1DIntArray("[2,9,1,19,5,7,3,19]");
        leet1235 lt = new leet1235();
        System.out.println(lt.jobScheduling2(arr1, arr2, arr3));
    }
}
