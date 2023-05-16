"""
@author:fuzekun
@file:treeArray.py
@time:2022/08/10
@description:树状数组模板
"""
class TreeArray:

    c = []
    n = 0
    def __init__(self, _n):
        global n, c
        n = _n
        c = [0] * (n + 5)

    def low_bit(self, x:int) :
        return x & -x

    def add(self, x:int, num:int):
        global n, c
        i = x
        while i <= n:
            c[i] += num
            i += self.low_bit(i)

    def sum(self, x:int) :
        global n, c
        i, res = x, 0
        while i > 0:
            res += c[i]
            i -= self.low_bit(i)
        return res