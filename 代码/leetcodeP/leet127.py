"""
单词接龙:
图论的题目
1. 如果两个有边，那么一定是无向边。
2. 找到一条从头到尾的路径就是有答案。求的是答案的最小值。DAG上的DP
3. 直接bfs

双向广度bfs优化
1. 两个队列，两个vis数组
2. 两边各自扩展一层结点，当发现两边都访问过同一结点的时候停止搜索。

"""
import collections
import string
from typing import List


class Solution:
    def ladderLength(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        mp = dict()
        G = collections.defaultdict(list)
        wordList.append(beginWord)
        for i, s in enumerate(wordList):
            mp[s] = i
        if not mp.__contains__(endWord):
            return 0
        for i, s in enumerate(wordList):
            word = list(s)
            for j, pre in enumerate(s):
                for ch in string.ascii_lowercase:
                    if (ch == pre):         # 必须有一个字母不同
                        continue
                    word[j] = ch
                    tmp = "".join(word)
                    if (mp.__contains__(tmp)):
                        k = mp[tmp]
                        G[i].append(k)
                        G[k].append(i)
                word[j] = pre               # 只能有一个字母不同
        inf = 0x3f3f3f3f
        dist = dict((i, inf) for i in range(len(wordList)))
        bg = mp[beginWord]
        ed = mp[endWord]
        que = collections.deque()
        que.append(bg)
        dist[bg] = 1
        while que:
            u = que.popleft()
            for v in G[u]:
                if dist[v] == inf:
                    dist[v] = min(dist[u] + 1, dist[v])
                    if v == ed:
                        return dist[v]
                    que.append(v)
        return -1

    def m2(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        word_dict = set(wordList)           # 建立字典
        visited = set([beginWord])          # vis
        que = collections.deque([(beginWord, 1)]) # dist
        while que:
            u, step = que.popleft()
            if u == endWord:
                return step
            for i in range(len(u)):
                for ch in string.ascii_lowercase:   # 建立图
                    v = u[:i] + ch + u[i + 1:]
                    if v not in visited and v in word_dict:
                        que.append((v, step + 1))
                        visited.add(v)
        return 0

    def m3(self, beginWord: str, endWord: str, wordList: List[str]) -> int:
        wordList.append(beginWord)
        word_dict = set(wordList)
        if endWord not in word_dict :
            return 0
        dist1 = {s : (float('inf') if s != beginWord else 1) for s in wordList}
        dist2 = {s : (float('inf') if s != endWord else 1)  for s in wordList}
        que1 = collections.deque([beginWord])
        que2 = collections.deque([endWord])

        def check(u, dist1, dist2, que) -> bool:
            if dist2[u] != float('inf'):
                return True
            for i in range(len(u)):
                for ch in string.ascii_lowercase:  # 建立图
                    v = u[:i] + ch + u[i + 1:]
                    if v in word_dict and dist1[v] == float('inf'):
                        que.append(v)
                        dist1[v] = dist1[u] + 1
            return False
        while que1 and que2:            # 双向bfs
            u1 = que1.popleft()
            u2 = que2.popleft()
            if check(u1, dist1, dist2, que1):
                return dist1[u1] + dist2[u1] - 1
            if (check(u2, dist2, dist1, que2)) :
                return dist1[u2] + dist2[u2] - 1
        return 0


if __name__ == '__main__':
    s = Solution()
    ans = s.m3(
        "hit",
"cog",
["hot","dot","dog","lot","log","cog"])
    print(ans)