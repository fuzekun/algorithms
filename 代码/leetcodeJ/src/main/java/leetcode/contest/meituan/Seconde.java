package leetcode.contest.meituan;

import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2023/3/18 10:31
 * @Description: 滑动窗口模板题目
 */
public class Seconde {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int ans = 0;
        Set<Integer>set = new HashSet<>();
        for (int i = 0, j = 0; j < n; j++) {
            set.add(nums[j]);
            while (set.size() > k) {           // 超过限制, set中一定包含nums[i]
                set.remove(nums[i]);
                i++;
            }
            ans = Math.max(ans, j - i + 1);
        }
        System.out.println(ans);
    }
}
