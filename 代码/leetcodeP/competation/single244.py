"""
@author:fuzekun
@file:single244.py
@time:2022/09/06
@description:
"""
from _bisect import bisect_left, bisect_right
from typing import List


class Solution:
    def minWastedSpace(self, packages: List[int], boxes: List[List[int]]) -> int:
        # print(bisect_right(packages, 4))
        ans = 0x3f3f3f3f
        packages.sort()
        s = sum(packages)
        print(s)
        for box in boxes:
            box.sort()      # 贪心找，从小到大
            if box[-1] < packages[-1]:
                continue
            pre = 0
            curs = 0
            for x in box:
                i = bisect_right(packages, x)
                print(x, i)
                curs += (i - pre) * x
                pre = i
                print(curs)
            ans = min(ans, curs - s)
        return ans
    def minFlips(self, s: str) -> int:
        s = s * 2
        n = len(s)
        s1, s2 = [], []
        for i in range(n):
            if i & 1:
                s1.append('0')
                s2.append('1')
            else :
                s1.append('1')
                s2.append('0')
        print("".join(s1), "".join(s2), s)
        m = n // 2
        minv1 = minv2 = 0
        for i in range(m) :
            if s[i] != s1[i]: minv1 += 1
            if s[i] != s2[i]: minv2 += 1
        print(minv1, minv2)
        i, j = 0, m
        ans = min(minv1, minv2)
        while j < n:
            first = s[i]
            if first != s1[i]:      #本来不相等
                if first == s1[j] :
                    minv1 -= 1      # 现在相等了，直接减少了两个
            else :
                if first != s1[j]:
                    minv1 += 1
            if first != s2[i]:      #本来不相等
                if first == s2[j]:
                    minv2 -= 1
            else :                  # 本来相等
                if first != s2[j]:  # 多了一个不相等的
                    minv2 += 1
            ans = min(ans, min(minv1, minv2))
            j += 1
            i += 1
        return ans




if __name__ == '__main__':
    s = Solution()
    # print(s.minWastedSpace([2,3,5],[[4,8],[2,8]]))
    print(s.minFlips("111000"))