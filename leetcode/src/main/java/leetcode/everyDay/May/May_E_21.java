package leetcode.everyDay.May;

import java.util.Random;

/**
 * @author: Zekun Fu
 * @date: 2022/5/21 8:20
 * @Description: 21号的简单题，使用了随机数
 */


//interface Solution {
//    public void solve();
//}
public class May_E_21 {

    static int[] nums;

    public int repeatedNTimes(int[] nums) {
        Random random = new Random();
        int n = nums.length;
        while(true) {
            int x = random.nextInt(n), y = random.nextInt(n);
            if (x != y && nums[x] == nums[y])
                return nums[x];
        }
    }


    public static void main(String[] args) {
        int[] arr = {1,2,3,3};
        nums = arr;
        May_E_21 sol = new May_E_21();
    }
}
