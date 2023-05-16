"""
@author:fuzekun
@file:single306.py
@time:2022/08/16
@description: 第306场周赛
"""
import itertools
from collections import Counter
from functools import cache
from typing import List


class Solution:
    def largestLocal(self, grid: List[List[int]]) -> List[List[int]]:
        ans = []
        n = len(grid)
        print(n)
        for i in range(n - 2):
            ei = i + 3
            tmp = []
            for j in range(n - 2):
                ej = j + 3
                num = 0
                for ti in range(i, ei):
                    for tj in range(j, ej) :
                        print(ti, tj)
                        num = max(num, grid[ti][tj])
                tmp.append(num)
            ans.append(tmp)
        return ans

    def edgeScore(self, edges: List[int]) -> int:
        score = Counter()
        ans = -1
        pos = -1
        for i, x in enumerate(edges):
            score[x] += i
        # print(score)
        for x, cnt in score.items():
            if cnt > ans :
                ans = cnt
                pos = x
            elif cnt == ans and pos > x :
                pos = x
        # print(ans)
        return pos

class Solution:
    def smallestNumber(self, pattern: str) -> str:
        num = 0
        minv = 0
        for x in pattern:
            if x == 'I':
                num += 1
            else :
                num -= 1
                minv = min(minv, num)
        cur = abs(minv) + 1
        print(cur)
        ans = []
        for x in pattern:
            ans.append(str(cur))
            if x == 'I' :
                cur += 1
            else :
                cur -= 1
        ans.append(str(cur))
        return "".join(ans)
    def check(self, ls, patten) -> bool:
        for i, x in enumerate(patten) :
            if x == 'I' :
                if ls[i] > ls[i + 1]:
                    return False
            else :
                if ls[i] < ls[i + 1]:
                    return False
        return True

    def smallestNumber2(self, pattern: str) -> str:
        n = len(pattern) + 1
        ls = list(range(1, n + 1))
        print(ls)
        ls = list(itertools.permutations(ls))
        for lst in ls:
            if self.check(lst, pattern) :
                ans = []
                for x in lst:
                    ans.append((str(x)))
                return "".join(ans)
        return []

    """
    
        特殊整数：
        1. 各个位不相同
        2. 计算含有重复位数的数字的个数
        
        1. 如果已经和前面有重复数字了比如21，那么直接就是22 * 10 ** (n - 2)
        2. 如果前面没有重复数字，比如21，那么就继续向下走,直到计算完成
        
    """

    def countSpecialNumbers(self, n: int) -> int:
        s = str(n)
        @cache
        def dfs(i, mask, is_limit, is_num) -> int:
            if i == len(s):
                return int(is_num)
            res = 0
            if not is_num :         # 仍旧不是数字
                res = dfs(i + 1, mask, False, False)
            up = int(s[i]) if is_limit else 9
            for d in range(1 - int(is_num), up + 1):
                if mask >> d & 1 == 0:
                    res += dfs(i + 1, mask | (1 << d), is_limit and d == up, True)
            return res
        return dfs(0, 0, True, False)




if __name__ == '__main__':
    s = Solution()
    # ans = s.largestLocal([[9,9,8,1],[5,6,2,6],[8,2,6,4],[6,2,2,2]])
    # print(ans)

    # ans2 = s.edgeScore( [1,0,0,0,0,7,7,5])
    # print(ans2)

    # ans3 = s.smallestNumber2("IIIDIDDD")
    # print(ans3)

    ans4 = s.countSpecialNumbers(20)
    print(ans4)