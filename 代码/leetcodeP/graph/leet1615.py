import collections
from typing import List

"""
    给任意的两个点
    找出两个点中的不同边的个数，最大值就是
    可以进行剪枝，剪枝的策略就是如果已经到了2 * n - 1条边就停止计算。
"""
class Solution:
    def maximalNetworkRank(self, n: int, roads: List[List[int]]) -> int:
        G = [set() for i in range(n)]
        for x, y in roads:
            G[x].add(y)
            G[y].add(x)
        ans, p = 0, []
        for i in range(n) :
            for j in range(i) :
                sum = len(G[j]) + len(G[i])
                if ans < sum:
                    ans = sum
                    p = [(i, j)]
                elif ans == sum:
                    p.append((i, j))
        for i, j in p:
            if i not in G[j] and j not in G[i]:
                return ans
        return ans - 1
if __name__ == '__main__':
    s = Solution()
    ans = s.maximalNetworkRank(4, [[0,1],[0,3],[1,2],[1,3]])
    print(ans)