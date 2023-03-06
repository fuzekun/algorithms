package leetcode.contest.October;

import leetcode.utils.ChangeToArrayOrList;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/16 10:32
 * @Description:
 */
public class Sec {

    public boolean sumOfNumberAndReverse(int num) {
        for (int i = 0; i <= num; i++) {
            List<Integer> nums = new ArrayList();
            int x = i;
            while (x != 0) {
                nums.add(x % 10);
                x /= 10;
            }
            int ans = 0;
            for (Integer y: nums) {
                ans = ans * 10 + y;
            }
            if (ans + i == num) {
                return true;
            }
        }
        return false;
    }
    public int findMaxK(int[] nums) {
        HashMap<Integer, Integer> mp = new HashMap<>();
        for (int x : nums) {
            mp.put(x, 1);
        }
        int ans = -1;
        for (int x : nums) {
            if (x > 0 && mp.containsKey(-x)) {
                ans = Math.max(ans, x);
            }
        }
        return ans;
    }
    public int countDistinctIntegers(int[] numss) {
        Set<Integer> st = new HashSet<>();
        for (int x : numss) {
            st.add(x);
            List<Integer> nums = new ArrayList();
            while (x != 0) {
                nums.add(x % 10);
                x /= 10;
            }
            int ans = 0;
            for (Integer y: nums) {
                ans = ans * 10 + y;
            }
            st.add(ans);
        }
        return st.size();
    }
    // 首先使用单点栈求解出来, 对于每一个minK作为最小值的上下边界
    //  对于每一个maxk找上下边界，相等的也算。[i, j]然后找[i, j]中最小值的数目
    //  问题转换成了，对于任意区间中[i, j], minK的数目
    // 可以使用前缀和进行解决。
    // 考虑maxK的左边有x个1，然后可以组成x * (right - i)个
    // 考虑maxK的右边有x个1，然后可以组成x * (i - left)个
    // 其中(left, right都是大于x的

    public long countSubarrays(int[] nums, int mink, int maxk) {
        int n = nums.length;
        int k1 = -1, k2 = -1, k3 = -1;
        int maxv = Integer.MIN_VALUE;
        int minv = Integer.MAX_VALUE;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == maxk)
                k1 = i;
            if (nums[i] == mink)
                k2 = i;
            if (nums[i] >= maxk || nums[i] <= mink) k3 = i;
            int sub = Math.min(k1, k2) - k3;
            if (sub < 0) sub = 0;
            ans += sub;
        }
        return ans;
    }

    public static void main(String[] args) {
        Sec sc = new Sec();
//        System.out.println(sc.sumOfNumberAndReverse(63));
        int []arr = {1};
//        System.out.println(sc.findMaxK(arr));
//        System.out.println(sc.countDistinctIntegers(arr));
    }


}
