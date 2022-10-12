"""
@author:fuzekun
@file:leet1976.py
@time:2022/09/24
@description:
"""
from collections import defaultdict, deque
from functools import cache
from heapq import heappush, heappop
from typing import List


def countPaths(n: int, roads: List[List[int]]) -> int:

    G = defaultdict(list)
    for (a, b, c) in roads:
        G[a].append([b, c])
        G[b].append([a, c])

    que = []
    path = [0] * n
    dist = [float('inf')] * n
    heappush(que,[0, 0])
    dist[0] = 0
    path[0] = 1
    while que:
        (sm, u) = que[0]
        heappop(que)
        for (v, spend) in G[u]:
            if dist[u] + spend < dist[v]:
                dist[v] = dist[u] + spend
                heappush(que, [dist[v], v])

    # que = deque()
    # mod = int(1e9) + 7
    # que.append(0)
    # vis = [0] * n
    # while que:
    #     u = que.popleft()
    #
    #     for (v, spend) in G[u]:
    #         if dist[u] + spend == dist[v] and vis[v] == 0:
    #             vis[v] = 1
    #             path[v] = (path[u] + path[v]) % mod
    #             que.append(v)
    # return path[n - 1]
    @cache
    def dfs(u):

        if u == n - 1:
            return 1
        ans = 0
        for v, spend in G[u]:
            if (dist[v] == dist[u] + spend):
                ans += dfs(v)
        return ans
    return dfs(0)
    # return dfs(0, 0)


# 性质：经过的每一个点都是最短路，那么他就是最短路。反之，如果存在一个点不是最短路
# 那么，这条路径就不是最短路。
# 所以就变成了求到每一个点的最短路有多少条，然后求和。
#
#
# 根据多年的经验，应该是求bfs过程中，加入了一维信息，然后就可以进行统计了。


print(countPaths(7,[[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]))