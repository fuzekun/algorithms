"""
@author:fuzekun
@file:single307.py
@time:2022/08/28
@description: 第307场单周赛
"""
from collections import deque
from typing import List


class Solution:
    def removeStars(self, s: str) -> str:
        n = len(s)
        s = [s[i] for i in range(n - 1, -1, -1)]
        ans = []
        i = 0
        while i < n:
            cnt = 0
            while s[i] == '*' :
                i += 1
                cnt += 1
            # print(cnt)
            while cnt != 0 :
                # print(cnt, s[i])
                if s[i] != '*':
                    cnt -= 1
                else :
                    cnt += 1
                i += 1
            if i < n and s[i] != '*':
                ans.append(s[i])
                i += 1
        ans = [ans[i] for i in range(len(ans) - 1, -1, -1)]
        return "".join(ans)
    def garbageCollection(self, garbage: List[str], travel: List[int]) -> int:
        end_p, end_m, end_g = 0, 0, 0
        for i in range(len(garbage) - 1, -1, -1):
            if 'P' in garbage[i] :
                end_p = i
                break
        for i in range(len(garbage) - 1, -1, -1):
            if 'G' in garbage[i] :
                end_g = i
                break
        for i in range(len(garbage) - 1, -1, -1):
            if 'M' in garbage[i] :
                end_m = i
                break
        g, m, p = 0, 0, 0
        for gar in garbage:
            for ch in gar:
                if ch == 'M' :
                    m += 1
                elif ch == 'G' :
                    g += 1
                else : p += 1
        for i in range(end_g) :
            g += travel[i]
        for i in range(end_p):
            p += travel[i]
        for i in range(end_m):
            m += travel[i]
        print(g, p, m)
        return p + g + m
    def buildMatrix(self, k: int, rowConditions: List[List[int]], colConditions: List[List[int]]) -> List[List[int]]:
        Gr, Gc = [], []
        dr, dc = [0] * k, [0] * k
        ans = []
        for _ in range(k):
            Gr.append([])
            Gc.append([])
            ans.append([0] * k)
        for a, b in rowConditions:
            Gr[a - 1].append(b - 1)
            dr[b - 1] += 1
        for a, b in colConditions:
            Gc[a - 1].append(b - 1)
            dc[b - 1] += 1
        rn, cn = [], []

        def torpu(d, G, ans):
            que = deque()
            for i in range(k):
                if d[i] == 0:
                    que.append(i)
                    ans.append(i)
            while que:
                u = que.popleft()
                for v in G[u]:
                    d[v] -= 1
                    if d[v] == 0:
                        que.append(v)
                        ans.append(v)
        torpu(dr, Gr, rn)
        torpu(dc, Gc, cn)
        print(rn, cn)
        if len(rn) != k or len(cn) != k:
            # print(len(rn), len(cn))
            return []
        dictr, dictc = dict(), dict()
        for i, x in enumerate(rn):
            dictr[x] = i
        for i, x in enumerate(cn):
            ans[dictr[x]][i] = x + 1
        return ans





if __name__ == '__main__':
    s = Solution()
    # print(s.removeStars("abb*cdfg*****x*"))
    # print(s.garbageCollection(["MMM","PGM","GP"],  [3,10]))
    print(s.buildMatrix(3, [[1,2],[2,3],[3,1],[2,3]],[[2,1]]))