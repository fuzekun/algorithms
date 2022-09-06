"""
@author:fuzekun
@file:leet600.py
@time:2022/08/16
@description: 数位dp的问题
"""
from functools import cache


class Solution:
    def findIntegers(self, n: int) -> int:
        s = []
        while n :
            s.append(n & 1)
            n >>= 1
        s = list(s[i] for i in range(len(s) - 1, -1, -1))
        print(s)
        @cache
        def f(i, pre, is_limit, is_num) -> int:
            if i == len(s) :
                return int(is_num)

            res = 0
            if not is_num:      # 前面是0
                res = f(i + 1, pre, False, False)

            up = int(s[i]) if is_limit else 1
            if is_num:
                res += f(i + 1, 0, is_limit and up == 0, True)
            if pre == 0 and up == 1:
                res += f(i + 1, 1, is_limit and up == 1, True)
            return res

        return f(0, 0, True, False) + 1

if __name__ == '__main__':
    s = Solution()
    ans = s.findIntegers(5)
    print(ans )