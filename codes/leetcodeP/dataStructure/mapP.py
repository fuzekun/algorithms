"""
@author:fuzekun
@file:mapP.py
@time:2022/08/12
@description:
"""
import collections
from typing import List


class Solution:
    def groupThePeople(self, groupSizes: List[int]) -> List[List[int]]:
        dic = collections.defaultdict(list)
        for i, x in enumerate(groupSizes) :
            dic[x].append(i)
        ans = list()
        for i, ls in dic.items() :
            tmp = list()
            for j, x in enumerate(ls) :
                if j != 0 and j % i == 0 :
                    ans.append(tmp)
                    tmp = list()
                tmp.append(x)
            ans.append(tmp)
        return ans

if __name__ == '__main__':
    s = Solution()
    ans = s.groupThePeople([2,1,3,3,3,2])
    print(ans)