"""
@author:fuzekun
@file:leet2193.py
@time:2022/08/10
@description:
得到回文串的最少操作次数
"""
import collections
from collections import Counter
from collections import defaultdict
from dataStructure import treeArray


class Solution:
    def minMovesToMakePalindrome(self, s: str) -> int:
        s = list(s)
        n = len(s)
        i, j, ans = 0, n - 1, 0
        while i < j:                    # 由于range生成的是不可变序列所以使用while循环
            k = j
            ch = s[i]
            while s[k] != ch :
                k -= 1
            if k != i:                  # 如果出现了偶数次
                ans += j - k
                while k < j:
                    s[k], s[k + 1] = s[k + 1], s[k]
                    k += 1
                j -= 1
            else :                      # 如果出现了奇数次
                ans += n // 2 - i
            i += 1
        return ans

    def minMove2(self, s: str) -> int :
        n = len(s)
        # 进行一个计数, 并且得到是奇数的情况
        freq = Counter(c for c in s)
        jich = [ch for ch in freq if freq[ch] % 2 == 1]
        # 获取左边编码，右边下标，以及组间间距
        lcnt, rcnt = 0, 0
        left, right = defaultdict(list), defaultdict(list)
        ans = 0
        for i, ch in enumerate(s) :
            if left[ch].__len__() < freq[ch] // 2:
                lcnt += 1
                left[ch].append(lcnt)
                ans += i - lcnt + 1
            else :
                rcnt += 1
                right[ch].append(rcnt)

        # 处理奇数的情况
        if len(jich) != 0:
            lcnt += 1
            left[jich[0]].append(lcnt)
        # 生成右边的排列
        perm = [0] * ((n - 1) // 2 + 1)
        for ch, rlst in right.items() :
            llst = left[ch]
            m = len(rlst)
            for i in range(m) :
                perm[rlst[m - 1 - i] - 1] = llst[i]
        print(perm)
        perm = reversed(perm)
        # 求解逆序对
        maxv = 1005
        ta = treeArray.TreeArray(maxv)
        for x in perm:
           ans += ta.sum(maxv) - ta.sum(x)
           ta.add(x, 1)

        return ans


if __name__ == '__main__':
    s = "aaabbbbcc"
    su = Solution()
    ans = su.minMove2(s)
    print("ans = " + str(ans))