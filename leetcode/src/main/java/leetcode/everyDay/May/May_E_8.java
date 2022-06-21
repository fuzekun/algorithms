package leetcode.everyDay.May;

import leetcode.contest.May.MayTest_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class May_E_8 {
    /*
        - 原数组进行标记
    - 改成负数，最后正数的就是两次的。
            - 需要进行减1处理
    - 直接改成相反数，不用进行判断原来是否是正的，因为
    - 对于没有出现过的数字，改成0

    1. 首先每个数字都加上n
    2. 其次,计算P时候减去n + 1
    3. 然后，改的时候，改变符号和数字的大小

    方法二


*/

    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;

        for (int i = 0; i < n; i++) nums[i] += n;
        for (int i = 0; i < n; i++) {
            int p;
            if (nums[i] > n) p = nums[i] - n - 1;
            else p = Math.abs(nums[i]) - 1;
            if (nums[p] > n) nums[p] -= n;  // 出现过
            nums[p] = -nums[p];             // 看第几次出现
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0 && nums[i] <= n) ans.add(i + 1);
        }
        return ans;
    }
    // 官方题解
    public List<Integer> findDuplicates2(int[] nums) {
        List<Integer>ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int p = Math.abs(nums[i]) - 1;
            if (nums[p] > 0) nums[p] = -nums[p];
            else ans.add(p + 1);
        }
        return ans;
    }

    public List<Integer> findDuplicates3(int[] nums) {
        List<Integer>ans = new ArrayList<>();
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while(nums[i] != nums[nums[i] - 1]) {
                int p = nums[i] - 1;
                int tmp = nums[i];
                nums[i] = nums[p];
                nums[p] = tmp;
            }
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1)
                ans.add(nums[i]);
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums = {1,1};
        System.out.println(new May_E_8().findDuplicates3(nums));
    }
}
