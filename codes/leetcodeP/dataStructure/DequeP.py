"""
@author:fuzekun
@file:DequeP.py
@time:2022/08/16
@description: 用来实现deque
"""

class MyCircularDeque:

    n = 0
    que = []
    tail = 0
    head = 0
    def __init__(self, k: int):
        self.n = k
        self.que = [0] * (2 * k)
        self.head = k - 1
        self.tail = k

    def insertFront(self, value: int) -> bool:
        if self.isFull():
            return False

        self.que[self.head] = value
        self.head -= 1

        return True


    def insertLast(self, value: int) -> bool:
        if self.isFull():
            return False

        self.que[self.tail] = value
        self.tail += 1

        return True

    def deleteFront(self) -> bool:
        if self.isEmpty():
            return False
        self.head += 1
        return True

    def deleteLast(self) -> bool:
        if self.isEmpty():
            return False
        self.tail -= 1


    def getFront(self) -> int:
        if self.isEmpty():
            return -1
        return self.que[self.head + 1]

    def getRear(self) -> int:
        if self.isEmpty():
            return -1
        return self.que[self.tail - 1]


    def isEmpty(self) -> bool:
        return self.tail - self.head - 1 == 0

    def isFull(self) -> bool:
        return self.tail - self.head - 1 == self.n



