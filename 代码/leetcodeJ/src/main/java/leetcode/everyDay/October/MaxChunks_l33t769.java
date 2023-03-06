package leetcode.everyDay.October;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * @author: Zekun Fu
 * @date: 2022/10/13 12:59
 * @Description: 最多能完成排序的块
 */
public class MaxChunks_l33t769 {

//    int n;
//    public int dfs (List[] buckets, int cur) {
//        if (cur == n) return buckets.length;
//        for (int i)
//    }
    public int maxChunksToSorted(int[] arr) {
        int n = arr.length;
        int[]cp = Arrays.copyOf(arr, n);
        Arrays.sort(cp);
        int s1 = 0, s2 = 0;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            s1 |= 1 << arr[i];
            s2 |= 1 << cp[i];
            if (s1 == s2) ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
    }
}
