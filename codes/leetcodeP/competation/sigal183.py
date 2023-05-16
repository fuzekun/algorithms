"""
@author:fuzekun
@file:sigal183.py
@time:2022/08/11
@description: 183场周赛
"""
from typing import List

class Solution:

    def minSubsequence(self, nums: List[int]) -> List[int]:
        nums.sort(reverse=True)
        s = sum(nums)
        ans = []
        cur = 0
        for x in nums:
            ans.append(x)
            cur += x
            if s < 2 * cur:
                return ans
        return ans
    def numSteps(self, s: str) -> int:
        nums = 0
        for x in s :
            nums = nums * 2 + ord(x) - ord('0')
        # print(nums)
        ans = 0
        while nums != 1 :
            if (nums & 1) == 0 :
                nums >>= 1
            else : nums += 1
            ans += 1
        return ans

    def longestDiverseString(self, a: int, b: int, c: int) -> str:
        """
            a = 4 b = 4 c = 5
            abcabcabcabcabcc
            cab
            a = 1 b = 1 c = 7
            ccaccbcc
        """
        ans = ""
        s = 0
        while 1:
            if s != 2:
                if a == 0 and b == 0 and c == 0:
                    break
                if a > 0 and a >= b and a >= c :
                    ans += 'a'
                    a -= 1
                if b > 0 and b >= a and b >= c :
                    ans += 'b'
                    b -= 1
                if c > 0 and c >= a and c >= b :
                    ans += 'c'
                    c -= 1
            elif s == 2:
                if ans[-1] == 'a' :
                    if b == 0 and c == 0:
                        break
                    if b > c :
                        ans += 'b'
                        b -= 1
                    else :
                        ans += 'c'
                        c -= 1
                elif ans[-1] == 'b' :
                    if a == 0 and c == 0:
                        break
                    if a > c :
                        ans += 'a'
                        a -= 1
                    else  :
                        ans += 'c'
                        c -= 1
                else :
                    if a == 0 and b == 0:
                        break
                    if a > b :
                        ans += 'a'
                        a -= 1
                    else :
                        ans  += 'b'
                        b -= 1
            if len(ans) >= 2 and ans[-1] == ans[-2]:
                s = 2
            else :
                s = 1
        return ans
    def dfs(self, nums, curA, curB, cur, dp) -> int:
        if dp[cur] != 0:
            return dp[cur]

        a, b, c = 0, 0, 0   # 0 不确定， -1 输， 1 赢, 2 平局
        n = len(nums)
        if n == 0:
            if curA == curB:
                dp[cur] = 2
                return 2
            elif curA > curB:
                dp[cur] = 1
                return 1
            else :
                dp[cur] = -1
                return -1
        if n >= 1:
            a = self.dfs(nums[1:], curB, curA + nums[0], cur + 1, dp)
            if a == -1 :    # 返回b是否能够赢得比赛。如果b有一种输的情况，那么A就一定胜利
                dp[cur] = 1
                return 1
        if n >= 2:
            b = self.dfs(nums[2:], curB, curA + nums[1] + nums[0], cur + 2, dp)
            if b == -1:
                dp[cur] = 1
                return 1
        if n >= 3:
            c = self.dfs(nums[3:], curB, curA + nums[0] + nums[1] + nums[2], cur + 3, dp)
            if c == -1:
                dp[cur] = 1
                return 1
        if a == 2 or b == 2 or c == 2:  # 有一个平局就是平局
            dp[cur] = 2
            return 2
        else :
            dp[cur] = -1
            return -1                   # 否则就是输了


    def stoneGameIII(self, stoneValue: List[int]) -> str:
        n = len(stoneValue)
        dp = list(0 for i in range(n + 1))
        ans = self.dfs(stoneValue, 0, 0, 0, dp)
        if ans == 2:
            return "Tie"
        elif ans == 1:
            return "Alice"
        else :
            return "Bob"

if __name__ == '__main__':
    s = Solution()
    # ans = s.minSubsequence([4,3,10,9,8])
    # print(ans)
    #
    # ans = s.numSteps("1101")
    # print(ans)

    # ans = s.longestDiverseString(4, 4, 5)
    # print(ans)
    ans = s.stoneGameIII([1,2,3,6])
    print(ans)