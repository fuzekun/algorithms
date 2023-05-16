package leetcode.everyDay.October;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/14 17:55
 * @Description: 字符串中不同子序列的个数
 *
 * dp[i][c] : 表示截止到i为止以c为结尾的的不同子序列的个数
 * dp[i][c] = dp[i - 1][c]
 * dp[S]表示截止到i为止为S的集合个数
 *
 * 对于每一个字符有两种选择，选或者不选。
 *
 * 如果改成子串应该怎么做呢?
 */
public class leet940 {
    public int distinctSubseq2(String s) {
        final long mod = (long)1e9 + 7;
        int n = s.length();
        if (n == 0) return 0;
        HashMap<String, Long> mp = new HashMap<>();
        char[] chars = s.toCharArray();
        mp.put("", 1L);
        for (int i = 0; i < n; i++) {
            Map<String, Long> add = new HashMap<>();
            for (Map.Entry<String, Long>entry : mp.entrySet()) {
                String key = entry.getKey();
                long val = entry.getValue();
                key += chars[i];                // 是唯一的，所以加上不会有问题
                add.put(key, val);
//                add.put(key, ((mp.getOrDefault(key, 0L) + val) % mod));
            }
            for (Map.Entry<String, Long>entry : add.entrySet()) {
                String key = entry.getKey();
                long val = entry.getValue();
                // 如果没有这个子序列，那么这个子序列就会多val个
                mp.putIfAbsent(key, val % mod);
            }
        }
        long ans = 0;
        for (Map.Entry<String, Long>entry: mp.entrySet()) {
            ans = (ans + entry.getValue()) % mod;
        }
        ans --;
        return (int)(ans % mod);
    }

    public int distinctSubseqII(String s) {
        char[] chars = s.toCharArray();
        int n = chars.length;
        final int mod = (int) 1e9 + 7;
        long[][] dp = new long[n][26];

        for (int i = 0; i < n; i++) {
            int c = chars[i] - 'a';
            Arrays.fill(dp[i], 0);
            dp[i][c] = 1;
            if (i == 0) {
                continue;
            }
            for (int j = 0; j < 26; j++) {
                dp[i][c] = (dp[i][c] + dp[i - 1][j]) % mod;
                if (j != c) dp[i][j] = dp[i - 1][j];
            }
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) ans = (ans + dp[n - 1][i]) % mod;
        return (int) (ans % mod);
    }

    // 优化之后的代码
    public int change(String s) {

        int n = s.length();
        final int mod = (int)1e9 + 7;
        char[] chars = s.toCharArray();
        long[] dp = new long[26];
        Arrays.fill(dp, 0);
        for (int i = 0; i < n; i++) {
            int c = chars[i] - 'a';
            for (int j = 0; j < 26; j++) {
                if (j != c)
                    dp[c] = (dp[c] + dp[j]) % mod;
            }
            dp[c] += 1;
        }
        long ans = 0;
        for (int i = 0; i < 26; i++) {
            ans = (ans + dp[i]) % mod;
        }
        return (int)ans;
    }

    public static void main(String[] args) {
        String s = "aba";
        System.out.println(new leet940().change(s));
    }
}

/*
*
*   aaa
* a : 2
* aa : 1
* aaa : 1
*
* abc
*
* "": 1
* a : 1
* ab : 2
* abc: 3
*
* "" = 1
* a:1
* aa:
*
* a:1
* b:2
* c:1 + 2 + 1 = 4
* ans = 7
* */
