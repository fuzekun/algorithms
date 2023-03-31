package leetcode.everyDay.sanyue;

/**
 * @author: Zekun Fu
 * @date: 2023/3/22 10:55
 * @Description:
 * 简单模拟
 */
public class Leet1599 {
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int max = 0, ans = -1, n = customers.length;
        int cur_cus = 0, money = 0;
        for (int i = 0; i < n || cur_cus != 0; i++) {
            // 当前等待的人
            if (i < n) cur_cus += customers[i];
            // 上去的人
            int in_cus = Math.min(cur_cus, 4);
            // 更新当前等待的人
            cur_cus -= in_cus;
            // 每一趟 = 游客的花钱 - 转一次的花费
            money += boardingCost * in_cus - runningCost;
            if (money > max) {
                max = money;
                ans = i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,0,5,1};
        System.out.println(new Leet1599().minOperationsMaxProfit(nums, 1, 3));
    }
}
