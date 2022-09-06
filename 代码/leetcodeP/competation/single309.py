"""
@author:fuzekun
@file:single309.py
@time:2022/09/04
@description:
"""
from functools import cache
from typing import List


class Solution:
    def checkDistances(self, s: str, dis: List[int]) -> bool:
        dis = [x + 1 for x in dis]      # 下标间的距离
        pre = [-1] * 26                  # 上一个字符出现的位置，如果没有就是-1
        for i, ch in enumerate(s):
            c = ord(ch) - ord('a')
            if pre[c] == -1:
                pre[c] = i
                continue
            else :
                if i - pre[c] != dis[c]:
                    return False
        return True

    def numberOfWays(self, startPos: int, endPos: int, n: int) -> int:
        mod = int(1e9) + 7
        @cache
        def dfs(u:int, k:int)->int:
            if k == n:
                return u == endPos
            ans = 0
            ans = (ans + dfs(u + 1, k + 1)) % mod
            ans = (ans + dfs(u - 1, k + 1)) % mod
            return ans
        return dfs(startPos, 0)

    def longestNiceSubarray(self, nums: List[int]) -> int:
        i, j, n, ans = 0, 0, len(nums), 0
        cnt = [0] * 32
        while j < n:
            x = nums[j]
            # bigger = []
            flag = 0
            for pos in range(32):
                cnt[pos] += (x >> pos) & 1
                # if cnt[pos] == 2:
                #     bigger.append(pos)
                if cnt[pos] >= 2:
                    flag = 1
            while flag:
                flag = 0
                y = nums[i]
                i += 1
                for pos in range(32):
                    cnt[pos] -= (y >> pos) & 1
                for pos in range(32):       # 检验是否可以跳出循环了
                    if cnt[pos] >= 2:
                        flag = 1
            ans = max(ans, j - i + 1)       #
            j += 1
        return ans

if __name__ == '__main__':
    s = Solution()
    # print(s.checkDistances("abaccb", [1,3,0,5,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0]))\
    # print(s.numberOfWays(1,2,3))
    print(s.longestNiceSubarray( [1,3,8,48,10]))