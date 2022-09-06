"""
@author:fuzekun
@file:test.py
@time:2022/08/16
@description:
"""
from typing import List


class OrderedStream:
    n = 0
    cur = 0
    que = [""]

    def __init__(self, n: int):
        self.n = n
        self.que = [""] * (self.n + 3)

    def insert(self, idKey: int, value: str) -> List[str]:
        ans = []
        self.que[idKey] = value
        if self.cur == idKey:
            while self.cur < len(self.que) and self.que[self.cur] != "":
                ans.append(self.que[self.cur])
                self.cur += 1
        return ans

# Your OrderedStream object will be instantiated and called as such:
# obj = OrderedStream(n)
# param_1 = obj.insert(idKey,value)