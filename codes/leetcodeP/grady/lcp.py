"""
@author:fuzekun
@file:lcp.py
@time:2022/10/01
@description:
"""
from typing import List


class Solution:
    def maxmiumScore(self, cards: List[int], cnt: int) -> int:
        cards.sort(reverse=True)
        nums1 = [x for x in cards[cnt:] if x % 2 == 0]
        nums2 = [x for x in cards[cnt:] if x % 2]
        s = sum(cards[0:cnt])
        if s % 2 == 0: return s

        ans = 0

        def replace(x):
            nonlocal s, ans
            if x & 1 and len(nums1) != 0:
                ans = max(ans, s - x + nums1[0])
            if x & 1 == 0 and len(nums2) != 0:
                ans = max(ans, s - x + nums2[0])

        x = cards[cnt - 1]
        replace(x)
        print(ans)
        for i in range(cnt - 2, -1, -1):
            if x & 1 != cards[i] & 1:
                replace(cards[i])
                break

        return ans

if __name__ == '__main__':
    s = Solution()
    print(s.maxmiumScore([3,3,1], 1))