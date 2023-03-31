package lanqiao.chongci;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author: Zekun Fu
 * @date: 2023/3/27 10:49
 * @Description:
 */
public class Acwing799 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int ans = 0;
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0, j = 0; j < n; j++) {
            while (set.contains(nums[j])) set.remove(nums[i++]);
            set.add(nums[j]);
            ans = Math.max(ans, j - i + 1);
        }
        System.out.println(ans);
    }
}
