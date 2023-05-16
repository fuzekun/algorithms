package leetcode.everyDay.June;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

/**
 * @author: Zekun Fu
 * @date: 2022/6/9 15:25
 * @Description: 6月9号的每日一题
 */
public class June_1 {

    Random random = new Random();
    int[][] rects;
    int[]sum;
    int total;
    int getSize(int i) {
        return (rects[i][2] - rects[i][0] + 1) * (rects[i][3] - rects[i][1] + 1);
    }
    public June_1(int[][] rects) {
        this.rects = rects;
        int n = rects.length;
        sum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = getSize(i) + sum[i];
        }
        total = sum[n];
    }

    public int lower_bound(int[] arr, int x) {
        int l = 0, r = arr.length;
        while(l < r) {
            int mid = (l + r) >> 1;
            if (arr[mid] >= x) r = mid;
            else l = mid + 1;
        }
        return l;
    }
    public int[] pick() {
        int t = random.nextInt(total) + 1;
        int i = lower_bound(sum, t);
        i--;
        t -= sum[i];
        int mod = rects[i][2] - rects[i][0] + 1;
        int x = rects[i][0] + t / mod;
        int y = rects[i][1] + t % mod;
        return new int[]{x, y};
    }

    public static void main(String[] args) {

    }
}
