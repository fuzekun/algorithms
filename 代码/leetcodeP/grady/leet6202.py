"""
@author:fuzekun
@file:leet6202.py
@time:2022/10/09
@description:
"""
from collections import deque
from typing import List


class Solution:
    def robotWithString(self, s: str) -> str:
        n = len(s)
        dp = [ord('z') + 1] * (n + 1)
        s = list(s)
        for i in range(n - 1, -1, -1):
            dp[i] = min(dp[i + 1], ord(s[i]))

        st = []
        ans = []
        for i in range(n):
            st.append(ord(s[i]))
            while st and st[-1] <= dp[i + 1]:  # 如果栈顶的元素是从右向左最小的话，直接出栈
                ans.append(chr(st[-1]))
                st.pop()
        return ''.join(ans)


    def numberOfPaths(self, grid: List[List[int]], k: int) -> int:
        n = len(grid)
        m = len(grid[0])
        mod = int(1e9 + 7)

        dp = [[[0] * k for j in range(m + 1)]for i in range(n + 1)]
        dp[1][1][grid[0][0] % k] = 1

        for i in range(1, n + 1):
            for j in range(1, m + 1):
                for t in range(k):
                    nx = (grid[i - 1][j - 1] + t) % k
                    dp[i][j][nx] += dp[i - 1][j][t] + dp[i][j - 1][t]
                    dp[i][j][nx] %= mod
        return dp[n][m][0]

if __name__ == '__main__':
    s = Solution()
    # x = s.robotWithString("dbba")
    # print(x)
    print(s.numberOfPaths([[7,3,4,9],[2,3,6,2],[2,3,7,0]], 1))