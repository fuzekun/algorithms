### 解题思路
很简单的计数dp问题。
这里提供两种思路，第一种是dp,第二种是暴力。暴力超时。

$dp[i][j]$: 表示截止到i为止，以字符j结尾的不同子序列的个数。
$dp[i][j] += dp[i - 1][k]$ k 属于[0, 26)， 其余的$dp[i][j] = dp[i - 1][j]$

初始化$dp[i][j] = 1;$

> 虽然是自己做出来的，但是仍旧不知道这样做的为什么正确，只知道这么做正确。这算是做dp做多了做出的题感嘛？


### 代码
超时代码
```java
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

```

优化之后的代码
```java
class Solution {
    public int distinctSubseqII(String s) {

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
}
```

空间复杂度高的算法：



```java
class Solution {
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

}
```