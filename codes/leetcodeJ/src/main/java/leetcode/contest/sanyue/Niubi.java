package leetcode.contest.sanyue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2023/3/28 19:01
 * @Description:周赛338
 */
public class Niubi {
    int[] primes;
    int[] notPrimes;
    /**
     *  普通的素数筛选, 返回素数的数量
     * */
    int init(int n) {
        primes = new int[n + 1];
        notPrimes = new int[n + 1];
        int cnt = 0;
        for (int i = 2; i <= n; i++) {
            if (notPrimes[i] == 0) {
                for (int j = i + i; j <= n; j += i) {
                    notPrimes[j] = 1;
                }
                primes[cnt++] = i;
            }
        }
        return cnt;
    }
    public boolean primeSubOperation(int[] nums) {
        int cnt = init(2005);                   // 这个不能太小
        int[] vis = new int[cnt];
        int pre = 0;
        for (int x : nums) {
            if (x <= pre) {
                return false;
            }
            int l = 0, r = cnt;
            while (l < r) {
                int mid = l + r >> 1;
                if (x >= primes[mid] + pre) l = mid + 1;
                else r = mid;
            }
            int bg;
            for (bg = l; bg >= 0; bg--) {
                if (vis[bg] == 0 && x > primes[bg] + pre) {
                    break;
                }
            }
            pre = x;                                    // 这个不能放在if里面
            if (bg >= 0)
                pre -= primes[bg];
        }
        return true;
    }
    public List<Long> minOperations(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        long[] sum = new long[n + 1];
        sum[0] = 0;
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        ArrayList<Long>ans = new ArrayList<>();
        for (int x : queries) {
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (x > nums[mid]) l = mid + 1;
                else r = mid;
            }
            ans.add((long)l * x - sum[l] + sum[n] - sum[l] - (long)x * (n - l));
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {2,9,6,3};
//        System.out.println(new Niubi().primeSubOperation(nums));
        int[] nums2 = {10};
        Niubi nui = new Niubi();
        System.out.println(nui.minOperations(nums, nums2));
    }
}
