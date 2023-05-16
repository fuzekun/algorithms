"""
@author:fuzekun
@file:leet911.py
@time:2022/09/24
@description:

1. 数学
2. 二分查找
"""

# [0, 5): 0
# [5, 10):1
# [10, 15):1
# [15, 20):0
# [20, 25):0
# [25, 30):1
# [30, inf):0
from collections import defaultdict, Counter
from typing import List


class TopVotedCandidate:
    # [0, 5): 0
    # [5, 10):1
    # [10, 15):1
    # [15, 20):0
    # [20, 25):0
    # [25, 30):1
    # [30, inf):0

    mp = defaultdict(int)
    inteval = []
    nums = []
    n = 0

    def __init__(self, persons: List[int], times: List[int]):
        cnt = Counter()
        pre = 0
        self.n = len(times)
        self.inteval = times
        self.inteval.append(float('inf'))
        for i, (p, t) in enumerate(zip(persons, times)):
            cnt[p] += 1
            if cnt[p] >= cnt[pre]:
                # print(pre, p, cnt[pre], cnt[p])
                pre = p
            self.nums.append(pre)

    def q(self, t: int) -> int:
        l, r = 1, self.n
        # 找到最后一个大于他的右边界
        while l < r:
            mid = l + r >> 1
            if self.inteval[mid] > t:
                r = mid
            else:
                l = mid + 1

        # print(l)
        return self.nums[l - 1]



if __name__ == '__main__':
    s = TopVotedCandidate([0,0,0,0,1], [0,6,39,52,75])
    print(s.q(45))
# Your TopVotedCandidate object will be instantiated and called as such:
# obj = TopVotedCandidate(persons, times)
# param_1 = obj.q(t)