"""

到家的最少跳跃次数
1. 队列中 ： 记录上一次跳跃的方向； 上一次的位置，以及跳跃到上一次的最短时间。
2. 使用bfs进行搜索
3. 时间复杂度是O(x),但是应该判断跳跃多少次停止。
一直向后跳，跳到什么时候停止呢

a * x - b * y = target
线性同余方程。有解，然后找到(x + y)的最小值。

"""
from collections import deque
from typing import List


class Solution:

    # def exgcd(self, a:int, b:int, ans:List[int]) ->int:
    #     if (b == 0):
    #         ans[0] = 1          # x = 1
    #         ans[1] = 0          # y = 0
    #         return a
    #     ans[0], ans[1] = ans[1], ans[0] # 交换x, y
    #     d = self.exgcd(b, a % b, ans)
    #     ans[1] -= (a // b) * ans[0]
    #     return d
    def exgcd(self, a, b, x, y):  # x,y是用来确定其中的系数，d是用来确定其中的最小公倍数
        if not b:
            return a, 1, 0
        d, x, y = self.exgcd(b, a % b, y, x)
        return d, y, x - (a // b) * y

    def minimumJumps(self, forbidden: List[int], a: int, b: int, x: int) -> int:
        bound = max(max(forbidden) + a + b, x + b)
        forbidden = set(forbidden)
        self.key = -1

        que = deque((i, i, False) for i in range(1))                   # 可以使用迭代初始化，但是不可以使用一个具体的值初始化

        while que:
            u, cnt, flag = que.popleft()
            if u == x:
                return cnt
            if u + a <= bound and u + a not in forbidden:
                forbidden.add(u + a)
                que.append((u + a, cnt + 1, False))
            if not flag and u - b >= 0 and u - b not in forbidden:
                que.append((u - b, cnt + 1, True))
        return -1
if __name__ == '__main__':
    s = Solution()
    ans = s.minimumJumps([162,118,178,152,167,100,40,74,199,186,26,73,200,127,30,124,193,84,184,36,103,149,153,9,54,154,133,95,45,198,79,157,64,122,59,71,48,177,82,35,14,176,16,108,111,6,168,31,134,164,136,72,98],29,98,80)
    print(ans)
