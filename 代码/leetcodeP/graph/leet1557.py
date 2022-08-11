"""

最小点集就是入度为0的集合

"""
import collections
import string
from collections import Counter
from typing import List


class Solution:

    def findSmallestSetOfVertices(self, n: int, edges: List[List[int]]) -> List[int]:
        endSet = set(y for x, y in edges)
        ans = [i for i in range(n) if i not in endSet]
        return ans

    """
    相信链接一个有向边，指向被相信者。
    最后统计出度为0，并且入读不为0的边。
    """
    def findJudge(self, n: int, trust: List[List[int]]) -> int:
        beginSet = set(x for x, y in trust)
        num = Counter(y for x, y in trust)
        print(num)
        return next((i for i in range(1, n + 1) if i not in beginSet and num[i] == n - 1), -1) # 要么这个元组为空，要么这个元组大小为1，为空返回-1， 大小为1直接返回元组内容

    def longestIdealString(self, s: str, k: int) -> int:
        chars = list(ord(char) - 97 for char in s)
        cnt = [0] * 26
        ans = 0
        for ch in chars:
            maxv = 0
            for t in range(max(ch - k, 0), min(ch + k + 1, 26)): # 注意这个范围
                maxv = max(maxv, cnt[t])
            cnt[ch] = maxv + 1
            ans = max(ans, maxv + 1)
        return ans

    def arithmeticTriplets(self, nums: List[int], diff: int) -> int:
        n = len(nums)
        ans = 0
        for i in range(2, n) :
            for j in range(0, i):
                for k in range(0, j):
                    if nums[i] - nums[j] == diff and nums[j] - nums[k] == diff:
                        ans += 1
        return ans

    def reachableNodes(self, n: int, edges: List[List[int]], restricted: List[int]) -> int:
        G = collections.defaultdict(list)
        for edge in edges:                  # 建立无向图
            G[edge[0]].append(edge[1])
            G[edge[1]].append(edge[0])

        cnt = Counter({i: 1 for i in range(n)})
        def dfs(u, fa) :                    # 统计每个子树的结点数量
            for v in G[u]:
                if v != fa:
                    dfs(v, u)
                    cnt[u] += cnt[v]

        st = set(restricted)
        def dfs2(u, fa, ans) :              # 删除每个在限制中的结点
            for v in G[u] :
                if st.__contains__(v) :
                    ans[0] -= cnt[v]
                    continue
                if v != fa:
                    dfs2(v, u, ans)

        dfs(0, -1)
        # print(cnt)
        ans = [n]
        dfs2(0, -1, ans)
        return ans[0]

    def validPartition(self, nums: List[int]) -> bool:
        return True                                 # 简单dp不解释





if __name__ == '__main__':
    s = Solution()
    # ans = s.findSmallestSetOfVertices(5,  [[0,1],[2,1],[3,1],[1,4],[2,4]])
    # print(ans)
    # ans2 = s.findJudge(2,  [[1,2]])
    # print(ans2)
    #

    # ans1 = s.arithmeticTriplets([4,5,6,7,8,9], 2)
    # print(ans1)

    # ans4 = s.longestIdealString("acfgbd", 2)
    # print(ans2)

    ans2 = s.reachableNodes(7,   [[0,1],[1,2],[3,1],[4,0],[0,5],[5,6]], [4,5])
    print(ans2)