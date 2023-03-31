package leetcode.everyDay.sanyue;

import java.util.Arrays;

/**
 * @author: Zekun Fu
 * @date: 2023/3/22 11:36
 * @Description: 需要好好思考边界条件
 * 跳出之后还需要一次求解
 *
 * 前缀和
 */
public class Leet1653 {

    public int minimumDeletions(String s) {
        int n = s.length();
        int[] sum = new int[n + 1];
        sum[n] = 0;

        // 前缀和
        for (int i = n - 1; i >= 0; i--) {
            sum[i] = sum[i + 1];
            if (s.charAt(i) == 'a') sum[i]++;
        }

        // 求解
        int ans = n + 5;
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            // 如果后面没有a了，已经满足条件了
            if (sum[i] == 0) break;
            if (s.charAt(i) == 'b') {
                // 删掉后面的a
                ans = Math.min(ans, sum[i] + cnt);
                // 删掉前面的b
                cnt++;
            }
        }

        // 删掉所有的b
        ans = Math.min(ans, cnt);
        return ans;
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(new Leet1653().minimumDeletions(s));
    }
}

