"""
@author:fuzekun
@file:leet2318.py
@time:2022/08/11
@description:

计数dp问题
"""
from math import gcd
from functools import cache         # 因为包是内置的，所以可以不用安装使用
import sys
sys.setrecursionlimit(10005)

class Solution:


    def distinctSequences(self, n: int) -> int:
        mod = int(1e9 + 7)
        @cache
        def dfs(self, cur, pre, ppre):
            if cur == n:
                return 1
            ans = 0
            for i in range(1, 7):
                if gcd(i, pre) != 1:
                    continue
                if ppre == i or pre == i:  # 注意等于1的情况
                    continue
                ans = (ans + dfs(self, cur + 1, i, pre)) % mod
            return ans

        return dfs(self, 0, 7, 7)

    def solve(self, n):
        mod, MX = 10 ** 9 + 7, 10 ** 4 + 1
        f = [[0] * 6 for _ in range(MX + 1)]
        f[1] = [1] * 6
        for i in range(2, MX):
            for j in range(6):
                for k in range(6):
                    if k != j and gcd(k + 1, j + 1) == 1:
                        f[i][j] += f[i - 1][k] - f[i - 2][j]
                if i > 3:
                    f[i][j] += f[i - 2][j]
                f[i][j] %= mod
        return f
    def digistSe(self, n:int) -> int :
        mod = int(1e9 + 7)
        f = self.solve(n)
        return sum(f[n]) % mod

if __name__ == '__main__':
    s = Solution()
    # ans = s.distinctSequences(10000)
    ans2 = s.digistSe(4)
    print(ans2)