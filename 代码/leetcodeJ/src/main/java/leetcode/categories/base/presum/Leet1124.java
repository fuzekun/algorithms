package leetcode.categories.base.presum;

import leetcode.utils.ChangeToArrayOrList;

import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/3/11 16:19
 * @Description:
 * 前缀和 + 单调栈 or
 * 前缀和 + hash
 */
public class Leet1124 {
    /**
     *
     * 因为只有-1和1。
     * 如果需要计算比s[i] - 1更小的数字，那么一定会经过s[i] - 1，也就是id一定会比s[i] - 1对应的j更大。、
     *
     *
     * */
    public int longestWPI2(int[] hours) {
        int n = hours.length;
        int sum = 0;
        HashMap<Integer, Integer>mp = new HashMap<>();
        int ans = 0;
        mp.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += hours[i] >= 9 ? 1 : -1;
            if (mp.containsKey(sum - 1)) {
                ans = Math.max(ans, i - mp.get(sum - 1));
            }
            if (!mp.containsKey(sum)) mp.put(sum, i);
        }
        // 如果全部都大于的情况下，直接就是数组的长度
        return sum > 0 ? n : ans;
    }
    public int longestWPI(int[] hours) {
        int n = hours.length;
        int sum = 0;
        HashMap<Integer, Integer>mp = new HashMap<>();
        int ans = 0;
        mp.put(0, -1);
        for (int i = 0; i < n; i++) {
            sum += hours[i] >= 9 ? 1 : -1;
            if (mp.containsKey(sum - 1)) {
                ans = Math.max(ans, i - mp.get(sum - 1));
            }
            if (!mp.containsKey(sum)) mp.put(sum, i);
        }
        // 如果全部都大于的情况下，直接就是数组的长度
        return sum > 0 ? n : ans;
    }


    public static void main(String[] args) {
        String s = "[9,9,9]";
        int[] nums = ChangeToArrayOrList.changTo1DIntArray(s);
        Leet1124 tmp = new Leet1124();
        int ans = tmp.longestWPI(nums);
        System.out.println(ans);
    }
}
