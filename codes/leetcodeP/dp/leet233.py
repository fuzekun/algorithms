"""
@author:fuzekun
@file:leet233.py
@time:2022/08/28
@description: 数字1的个数
数位dp
"""
from functools import cache


def countDigitOne(n: int) -> int:
    s = str(n)

    @cache
    def f(i: int, cnt1: int, is_limit: bool, is_num: bool) -> int:
        if i == len(s):
            return cnt1
        res = 0
        if not is_num:  # 仍旧不是数字
            res = f(i + 1, cnt1, False, False)
        up = int(s[i]) if is_limit else 9
        for d in range(1 - int(is_num), up + 1):  # 枚举要填入的数字 d
            res += f(i + 1, cnt1 + (d == 1), is_limit and d == up, True)
        return res

    return f(0, 0, True, False)

if __name__ == '__main__':
    print(countDigitOne(10000000))