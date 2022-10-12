package leetcode.contest.October;


import leetcode.utils.PrintArrays;
import leetcode.utils.ReadData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Stack;

/**
 * @author: Zekun Fu
 * @date: 2022/10/9 10:34
 * @Description: 第314场周赛
 */
public class firstC {
    public int hardestWorker(int n, int[][] logs) {
        int maxv = logs[0][1], id = logs[0][0];
        for (int i = 1; i < logs.length; i++) {
            int spned = logs[i][1] - logs[i - 1][1];
            if (maxv < spned) {
                maxv = spned;
                id = logs[i][0];
            } else if (maxv == spned && id > logs[i][0])
                id = logs[i][0];
        }
        return id;
    }
    public int[] findArray(int[] pref) {
        int pre = 0;
        for (int i = 0; i < pref.length; i++) {
            int tmp = pref[i];
            pref[i] = pref[i] ^ pre;
            pre = tmp;
        }
        return pref;
    }
    public String robotWithString(String s) {
        // 从左到右找到最小的字符串，输出，然后前面的逆序输出
        // 找(i, n)的最小字符,可以使用树状数组求，逆序dp也可以
//        baca 和 caba
//        abac 和 abac
//        字符串要么逆序输出，要么正序输出。
//        从某一点开始逆序输出，得到的字典序
//        单调栈
//         abac
//        如果右边等于他的，该怎么处理呢？
//        首先逆序，然后问题就变成了,从某一个点输出，后面的全部
//        按照26个字符进行划分，字符a划分，字符b划分，...
        int n = s.length();
        char[] chars = s.toCharArray();
        StringBuilder ans = new StringBuilder();
        Stack<Character>st = new Stack<>();
        char[] dp = new char[n + 1];
        dp[n] = 'z' + 1;
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = (char)Math.min(dp[i + 1], chars[i]);
        }
        for (int i = 0; i < n; i++) {
            st.push(chars[i]);
            while (!st.isEmpty() && st.peek() <= dp[i + 1])
                ans.append(st.pop());
        }
        return ans.toString();
    }
    public int numberOfPaths(int[][] grid, int t) {
        int n = grid.length, m = grid[0].length;
        long [][][] dp = new long[n][m][t];
        final int mod = (int)1e9 + 7;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Arrays.fill(dp[i][j], 0);
            }
        }
        dp[0][0][grid[0][0] % t] = 1;
        for (int j = 1; j < m; j++) {
            for (int k = 0; k < t; k++) {
                int nx = (grid[0][j] + k) % t;
                dp[0][j][nx] = (dp[0][j - 1][k] + dp[0][j][nx]) % mod;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int k = 0; k < t; k++) {
                int nx = (grid[i][0] + k) % t; // 上一个等于k的加上本格
                dp[i][0][nx] = (dp[i - 1][0][k] + dp[i][0][nx]) % mod;
            }
        }
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                for (int k = 0; k < t; k++) {
                    int nx = (k + grid[i][j]) % t;
                    dp[i][j][nx] = ((dp[i - 1][j][k] + dp[i][j - 1][k]) % mod + dp[i][j][nx]) % mod;
                }
            }
        }
        return (int)dp[n - 1][m - 1][0];
    }
    public static void main(String[] args)throws Exception {
        firstC l = new firstC();
        int[][] arr2 = ReadData.get2DArray();
//        System.out.println(l.hardestWorker(26,  arr2));
//        int [] arr1 = ReadData.getArray();
//        PrintArrays.print1DIntArray(l.findArray(arr1));
        String s = "bdda";
        System.out.println(l.robotWithString(s));
//        System.out.println(l.numberOfPaths(arr2,1));
    }
}
