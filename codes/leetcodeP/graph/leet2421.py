"""
@author:fuzekun
@file:leet2421.py
@time:2022/10/01
@description: 好路径的数目


采用无根树->有根树 + 树上的启发式搜索来得到结果。
"""
from collections import defaultdict
from typing import List


class Solution:
    def numberOfGoodPaths(self, vals: List[int], edges: List[List[int]]) -> int:

        n = len(vals)
        g = [[] for i in range(n)]
        for a, b in edges:
            g[a].append(b)
            g[b].append(a)

        ans = 0
        def dfs(u, fa):
            ret = defaultdict(int)
            # 初始只包含根节点
            ret[vals[u]] = 1
            for v in g[u] :
                if v == fa:continue
                tmp = dfs(v, u)
                # 过滤掉不行的点
                keys = list(tmp.keys())
                for key in keys:
                    if key < vals[u]: del tmp[key]
                # 进行树上的启发式合并, tmp永远是长度小的, 把tmp合并到ret中去
                if len(tmp) > len(ret): tmp, ret = ret, tmp
                # 统计答案
                nonlocal ans
                keys = list(tmp.keys())
                for key in keys:
                    ans += tmp[key] * ret[key]
                # 更新ret
                keys = list(tmp.keys())
                for key in keys:
                    ret[key] += tmp[key]
            return ret
        ret = dfs(0, 0)
        print(ret)
        return ans + n


if __name__ == '__main__':
    s = Solution()
    print(s.numberOfGoodPaths([1,3,2,1,3],[[0,1],[0,2],[2,3],[2,4]]))