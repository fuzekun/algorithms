"""
@author:fuzekun
@file:leet769.py
@time:2022/08/13
@description: n <= 10 典型的排列问题， 总共有9个可以划分的位置，
所以可以直接使用暴力进行解决9!,
也可以进行组合，需要保证组间的顺序，取组间的最大值，需要保证组间的最大值连续
同时需要保证组内的所有值连续，

每一个位置有两种选择:
1. 划分到上一组，开一个新的组


"""
from typing import List


"""
    需要保证前一个组的最大值，等于本组的最小值 - 1
"""

class Solution:
    def check(self, nums) -> bool:
        nums.sort()
        for i in range(len(nums) - 1):
            if (nums[i] + 1 != nums[i + 1]):
                return False
        return True

    def dfs(self, nums, cur, cnt, premax, n, arr, ans):
        if (cur == n) :
            # 保证组内连续
            if self.check(nums) :
                ans[0] = max(ans[0], cnt)
            return

        # 1 首先满足后面仍旧有比当前大的，否则没法开新的数组, 并且需要直接到
        pos = -1
        flag = 1
        for i in range(cur, n) :
            if arr[i] == premax + 1:
                pos = i
                break
        if pos == -1 or self.check(nums) == False:
            flag = 0
        if flag:
            tmp = arr[cur:pos + 1]
            self.dfs(tmp, pos + 1, cnt + 1, max(tmp), n, arr, ans)
        # 使用旧的组
        nums.append(arr[cur])
        # print(nums)
        self.dfs(nums, cur + 1, cnt, max(arr[cur], premax), n, arr, ans)

    def maxChunksToSorted(self, arr: List[int]) -> int:
        ans = [1]
        self.dfs([arr[0]], 1, 1, arr[0], len(arr), arr, ans)
        return ans[0]



if __name__ == '__main__':
    s = Solution()
    ans = s.maxChunksToSorted([0,2,1,4,3])
    print(ans)