"""

访问所有结点的最短路径

"""
from collections import deque
from typing import List


class Solution:
    def shortestPathLength(self, graph: List[List[int]]) -> int:
        n = len(graph)
        q = deque((i, 1 << i, 0) for i in range(n))
        seen = {(i, 1 << i) for i in range(n)}

        while q:
            u, mask, dist = q.popleft()
            if mask == (1 << n) - 1:
                return dist
            for v in graph[u]:
                nmask = mask | (1 << v)
                if (v, nmask) not in seen:
                    q.append((v, nmask, dist + 1))
                    seen.add((v, nmask))
        return -1

if __name__ == '__main__':
    s = Solution()
    ans = s.shortestPathLength([[1],[0,2,4],[1,3],[2],[1,5],[4]])
    print(ans)