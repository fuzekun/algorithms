package leetcode.everyDay.siyue;

import leetcode.utils.ChangeToArrayOrList;

import java.util.Map;
import java.util.HashMap;

/**
 * @author: Zekun Fu
 * @date: 2023/5/4 12:33
 * @Description:
 */
public class Leet2106 {
    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        Map<Integer, Integer>mp = new HashMap<>();
        for (int[] fruit : fruits) mp.put(fruit[0], fruit[1]);
        int sum = 0, ans;
        for (int i = startPos; i <= startPos + k; i++) {
            sum += mp.getOrDefault(i, 0);
        }
        ans = sum;                  // 全部前面
        for (int j = startPos + k, i = startPos - 1; j > startPos; j -= 2, i--) {
            sum -= mp.getOrDefault(j, 0);
            sum -= mp.getOrDefault(j - 1, 0);
            sum += mp.getOrDefault(i, 0);
            ans = Math.max(ans, sum);// 前面 + 后面
        }
        sum = 0;
        for (int i = startPos; i >= startPos - k; i--) {
            sum += mp.getOrDefault(i, 0);
        }
        ans = Math.max(ans, sum);   // 全部后面
        for (int j = startPos - k, i = startPos + 1; j <= startPos; j += 2, i++) {
            sum -= mp.getOrDefault(j, 0);
            sum -= mp.getOrDefault(j + 1, 0);
            sum += mp.getOrDefault(i, 0);
            ans = Math.max(ans, sum);   // 后面 + 前面
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = ChangeToArrayOrList.changeTo2DIntArray("[[0,9],[4,1],[5,7],[6,2],[7,4],[10,9]]");
        int ans = new Leet2106().maxTotalFruits(nums, 5, 4);
        System.out.println(ans);
    }
}
