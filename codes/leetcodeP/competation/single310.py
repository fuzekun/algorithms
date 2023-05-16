"""
@author:fuzekun
@file:single310.py
@time:2022/09/11
@description: 310场周赛

"""
from collections import defaultdict
from typing import List


class Solution:
    def lengthOfLIS(self, nums: List[int], k: int) -> int:
        mp = defaultdict(int)
        print(mp[0])



if __name__ == '__main__':
    s = Solution()
    lst = [12,2,34,4]
    print(lst[1:-1])
    # 如果想让列表逆序，应该怎么做呢?
    # 倒数第一个是-1，..最后一个就是-n了，因为要包含最后一个，所以使用-n - 1
    print(lst[-1:-len(lst) - 1:-1])