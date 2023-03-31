package leetcode.everyDay.sanyue;

import leetcode.contest.sanyue.Leet_6318;

/**
 * @author: Zekun Fu
 * @date: 2023/3/27 20:46
 * @Description:
 */
public class Leet1638 {
    public int countSubstrings(String s, String t) {
        int n = s.length(), m = t.length();
        int ans = 0;
        int[][] dpl = new int[n + 1][m + 1];
        int[][] dpr = new int[n + 1][m + 1];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s.charAt(i) == t.charAt(j)) {
                    dpl[i + 1][j + 1] = dpl[i][j] + 1;
                }
            }
        }
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j))
                    dpr[i][j] = dpr[i + 1][j + 1] + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    ans += (dpl[i][j] + 1) * (dpr[i + 1][j + 1] + 1);
                }
            }
        }
        return ans;
    }
    public int countSubstrings2(String s, String t) {
        int n = s.length(), m = t.length();
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int k1 = i, k2 = j;
                int cnt = 0;
                // 双指针，同时移动，遇见一个不同的就统计
                while (k1 < n && k2 < m) {
                    if (s.charAt(k1) != t.charAt(k2)) cnt++;
                    if (cnt == 1) ans++;
                    if (cnt >= 2) break;
                    k1++; k2++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "aba", t = "baba";
        System.out.println(new Leet1638().countSubstrings(s, t));
    }
}
