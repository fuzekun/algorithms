
"""
@author:fuzekun
@file:leet233.py
@time:2022/08/28
@description: 划分k个相等的子集

状态压缩dp / 暴力回溯求解集合划分问题的两种方式
"""
from collections import defaultdict
from typing import List


class Solution:


    def canPartitionKSubsets(self, nums: List[int], k: int) -> bool:

        s = sum(nums)
        if s % k != 0: return False
        s = s // k

        # 性质1：其和一定是k的倍数。
        # 性质2：每个相等，那么每一个集合的和是sum / k
        # 性质3：分成k个集合，把每一个数字分配集合，属于状态压缩dp
        # 就是桶分配问题。
        # 性质4:那么：如果一个恰好能组成，那么可以直接从里面删除，另外的如果可以组成，那么也一定可以组成。

        # 转换成能否找到k个不同的集合，让他们的和是s？不行
        # 因为会有数字的重复问题。

        # 每遇到一个集合和为s的，直接去掉就行了。

        # 不可以直接去掉，因为[1,1,1,1,2,2,2,2]
        # 这种，

        # 还是状态压缩dp, dp[S]表示可以行的状态

        # 从大装,更容易装满
        n = len(nums)
        nums.sort()
        nums = nums[-1:-(n + 1):-1]
        # print(nums)
        mp = defaultdict(bool)

        def dfs(bucket, bid, cur, mask):
            if bid == k:
                return True

            if bucket[bid] == s:
                if dfs(bucket, bid + 1, 0, mask):
                    mp[mask] = True
                    return True

            if mp[mask]: return True

            for i in range(cur, n) :       # 把这些球放在当前桶中
                if mask >> i & 1: continue
                if bucket[bid] + nums[i] > s: continue
                bucket[bid] += nums[i]
                if dfs(bucket, bid, i + 1, mask | (1 << i)):
                    return True
                bucket[bid] -= nums[i]
                while i + 1 < n and nums[i] == nums[i + 1]: i += 1

            return False
        bucket = [0] * k
        return dfs(bucket, 0, 0, 0)

    def canPartitionKSubsets2(self, nums: List[int], k: int) -> bool:
        all = sum(nums)
        if all % k:
            return False
        per = all // k
        nums.sort()
        if nums[-1] > per:
            return False
        n = len(nums)
        dp = [False] * (1 << n)
        dp[0] = True
        cursum = [0] * (1 << n)
        for i in range(0, 1 << n):
            if not dp[i]:
                continue
            for j in range(n):
                if cursum[i] + nums[j] > per:
                    break
                if (i >> j & 1) == 0:
                    next = i | (1 << j)
                    if not dp[next]:
                        cursum[next] = (cursum[i] + nums[j]) % per
                        dp[next] = True
        return dp[(1 << n) - 1]

if __name__ == '__main__':
    s = Solution()
    print(s.canPartitionKSubsets2([4, 3, 2, 3, 5, 2, 1], 4))


