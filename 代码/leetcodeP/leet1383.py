"""
@author:fuzekun
@file:leet1383.py
@time:2022/08/10
@description:
"""
import queue
from typing import List


class Solution:
    def maxPerformance(self, n: int, speed: List[int], efficiency: List[int], k: int) -> int:
        ls = [[efficiency[i], speed[i]] for i in range(n)]
        ls.sort(key = lambda x: x[0], reverse = True)
        print(ls)
        que = queue.PriorityQueue()
        sum, ans = 0, 0
        for i, [ef, sp] in enumerate(ls):
            if i < k :
                que.put(sp)
                sum += sp
            else :
                t = que.get()
                if t < sp :
                    sum -= t
                    sum += sp
                    t = sp          # 放入较大的值
                que.put(t)
            ans = max(ans, ef * sum)
        return ans % int(1e9 + 7)



if __name__ == '__main__':
    s = Solution()
    ans = s.maxPerformance(6, [2,10,3,1,5,8], [5,4,3,9,7,2], 2)
    print(ans)