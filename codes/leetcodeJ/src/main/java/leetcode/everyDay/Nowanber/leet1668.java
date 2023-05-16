package leetcode.everyDay.Nowanber;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: Zekun Fu
 * @date: 2022/11/3 10:24
 * @Description:
 */
public class leet1668 {
    public int find(Integer[]a, int x, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (a[mid] == x) return mid;
            if (a[mid] > x) r = mid;
            else l = mid + 1;
        }
        return -1;
    }
    public int maxRepeating(String ss, String ww) {
        int n = ss.length(), m = ww.length();
        char[] sequence = ss.toCharArray();
        char[] word = ww.toCharArray();
        int i = 0, j = 0, k = -1;
        int[] nx = new int[m + 3];
        nx[0] = -1;
        while (j < m) {
            if (k == -1 || word[j] == word[k]) {
                j++; k++;
                if (j != m && word[j] == word[k]) nx[j] = nx[k];
                else nx[j] = k;
            } else k = nx[k];
        }
        j = 0;
        List<Integer>arr = new ArrayList<>();
        // O(n + m)
        while (i < n) {
            if (j == -1 || word[j] == sequence[i]) {
                j++; i++;
            } else j = nx[j];
            if (j == m) {
                arr.add(i - m);
                j = nx[j];
            }
        }
        Integer[] a =  arr.toArray(new Integer[arr.size()]);
        n = a.length;
        int ans = Math.min(1, n);
        int[] dp = new int[n + 1];
        Arrays.fill(dp, 1);
        // O(nlogn)
        for (i = 0; i < n; i++) {
            int nxi = find(a, a[i] + m, i + 1, n);
            if (nxi == -1) continue;
            dp[nxi] = dp[i] + 1;
            ans = Math.max(ans, dp[nxi]);
        }
        return ans;
    }

    public static void main(String[] args) {
        leet1668 l = new leet1668();
        int ans = l.maxRepeating("aa", "aaaaa");
        System.out.println(ans);
    }
}
