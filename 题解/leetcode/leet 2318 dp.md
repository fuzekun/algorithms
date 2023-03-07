

# [2318. 不同骰子序列的数目](https://leetcode.cn/problems/number-of-distinct-roll-sequences/)

### 解题思路


1. 注意调用的时候dfs(0, 7, 7)，使用-1是不行的。因为7和[1, 6]互素且不相等。
2. 简单的计数dp问题。



二维优化的话，就是f[i][j] + f[i - 1][k] - f[i - 2][j] * (nums_k - 1)。  i > 3的时候在减去。

为什么还需要加上一个，因为每一个多减去了f[i - 3][k]，而f[i - 2][j] 包含了全部的f[i - 3][k]的情况。
另外注意i是从1，开始的，所以 i > 3的时候 i - 3才有意义。否则i <= 3， i-3就不存


### 代码1

```python3 []
class Solution:



    def distinctSequences(self, n: int) -> int:
        mod = int(1e9 + 7)
        @cache
        def dfs(self, cur, pre, ppre):
            if cur == n:
                return 1
            ans = 0
            for i in range(1, 7) :
                if math.gcd(i, pre) != 1:
                    continue
                if ppre == i or pre == i:         # 注意等于1的情况
                    continue
                ans = (ans + dfs(self, cur + 1, i, pre)) % mod
            return ans
        return dfs(self, 0, 7, 7)
class Solution {
public:

    int n;
    int MOD = 1e9 + 7;
    vector<vector<vector<int>>>dp;
    int gcd(int a, int b) {
        return b ? gcd(b, a % b) : a;
    }
    int dfs(int cur, int pre, int pree) {
        if (cur == n) {
            return 1;
        } 
        int &ans = dp[cur][pre][pree];
        if (ans != -1) return ans;
        ans = 0; 
        for (int i = 1; i <= 6; i++) {
            if (i != pre && gcd(i, pre) == 1 && i != pree) {
                ans = (ans + dfs(cur + 1, i, pre)) % MOD;
            }
        }
        return ans;
    }
    
    int distinctSequences(int n) {
        this->n = n;
        dp = vector<vector<vector<int>>>(n, vector<vector<int>>(8, vector<int>(8, -1)));
        int ans = dfs(0, 7, 7);
        return ans;
    }
};
```


### 代码2

```python
class Solution:
    def distinctSequences(self, n: int) -> int:
        mod = int(1e9 + 7)
        f = [[0] * 6 for _ in range(n + 1)] 
        f[1] = [1] * 6
        for i in range(2, n + 1):               # 编号是从1开始的
            for j in range(6) :
                for k in range(6) :
                    if k != j and gcd(k + 1, j + 1) == 1:
                        f[i][j] += f[i- 1][k] - f[i - 2][j]
                if i > 3:
                    f[i][j] += f[i - 2][j]
                f[i][j] %= mod
        return sum(f[n]) % modv
```