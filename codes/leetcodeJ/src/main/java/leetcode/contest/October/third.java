package leetcode.contest.October;

import leetcode.utils.ReadData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/10/23 10:46
 * @Description:
 */
public class third {
    public long makeSimilar(int[] nums, int[] target) {
        Arrays.sort(nums);
        Arrays.sort(target);
        List<Integer>numsj = new ArrayList<>();
        List<Integer>numso = new ArrayList<>();
        List<Integer>to = new ArrayList<>();
        List<Integer>tj = new ArrayList<>();
        for (int x : nums) {
            if (x % 2 == 0) numso.add(x);
            else numsj.add(x);
        }
        for (int x : target) {
            if (x % 2 == 0) to.add(x);
            else tj.add(x);
        }
        int n = numso.size();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (to.get(i) > numso.get(i)) ans += (to.get(i) - numso.get(i)) / 2;
        }
        n = numsj.size();
        for (int i = 0; i < n; i++) {
            if (tj.get(i) > numsj.get(i)) ans += (tj.get(i) - numsj.get(i)) / 2;
        }
        return ans;
    }
    public boolean haveConflict(String[] event1, String[] event2) {
        String[] e1bg = event1[0].split(":");
        String[] e1ed = event1[1].split(":");
        String[] e2bg = event2[0].split(":");
        String[] e2ed = event2[1].split(":");
        int e1st = Integer.valueOf(e1bg[0]) * 60 + Integer.valueOf(e1bg[1]);
        int e1sp = Integer.valueOf(e1ed[0]) * 60 + Integer.valueOf(e1ed[1]);
        int e2st = Integer.valueOf(e2bg[0]) * 60 + Integer.valueOf(e2bg[1]);
        int e2sp = Integer.valueOf(e2ed[0]) * 60 + Integer.valueOf(e2ed[1]);
        if (e1st >= e2st && e1st <= e2sp) return true;
        if (e2st >= e1st && e2st <= e1sp) return true;
        return true;
    }
    public long minCost(int[] nums, int[] cost) {
        List<int[]> arrs = new ArrayList<>();
        int n = cost.length;
        for (int i = 0; i < n; i++) {
            arrs.add(new int[] {nums[i], cost[i]});
        }
        // 按照nums进行排序
        arrs.sort((x, y)->Integer.compare(x[0], y[0]));
        Arrays.sort(nums);
        long[] sumb = new long[n + 1];
        long[] suma = new long[n + 1];
        for (int i = 0; i < n; i++) {
            int c = arrs.get(i)[1];
            suma[i + 1] = suma[i] + c;
            sumb[i + 1] = sumb[i] + (long)nums[i] * c;
        }
        long ans = Long.MAX_VALUE;
        for (int i = nums[0]; i <= nums[n - 1]; i++) {
            int l = 0, r = n;
            while (l < r) {
                int mid = l + r >> 1;
                if (nums[mid] <= i) l = mid + 1;
                else r = mid;
            }
            long a1 = suma[l];
            long a2 = suma[n] - suma[l];
            long b1 = sumb[l];               // 小于，xa - b
            long b2 = sumb[n] - sumb[l];     // 大于，b - xa
            ans = Math.min(ans, (long)i * a1 - b1 + b2 - (long)i * a2);
        }
        return ans;
    }
    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    public static int subarrayGCD(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; i++) { // 以i开头，以j结尾子数组
            int d = nums[i];
            for (int j = i; j < n; j++) {
                d = gcd(nums[j], d);
                if (d == k) ans++;
                if (d < k) break;       // 小于就不可能再大了
            }
        }
        return ans;
    }
    public static void main(String[] args) throws Exception {
//        System.out.println(haveConflict(new String[]{"01:00","02:00"}, new String[]{"01:20","03:00"}));
//        System.out.println(subarrayGCD(new int[]{9,3,1,2,6,3}, 3));
        third t = new third();
        int[] arr1 = ReadData.getArray();
        int[] arr2 = ReadData.getArray("int1d_");
        System.out.println(t.minCost(arr1, arr2));
    }
}
