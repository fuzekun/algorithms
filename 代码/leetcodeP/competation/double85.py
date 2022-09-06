"""
@author:fuzekun
@file:double85.py
@time:2022/08/21
@description: 85 双周赛
"""
import string
from typing import List


class Solution:
    def shiftingLetters(self, s: str, shifts: List[List[int]]) -> str:
        n = len(s)
        b = [0] * (n + 1)
        for st, ed, d in shifts:
            b[st] += 1 if d == 1 else -1
            b[ed + 1] -= 1 if d == 1 else -1
        s = list(s)
        for i in range(0, n) :
            if i >= 1 : b[i] += b[i - 1]
            if b[i] > 0 :
                b[i] %= 26
            else :
                b[i] = -(abs(b[i]) % 26)
            t = ord(s[i]) + b[i]
            if t > ord('z'):
                t = ord('a') + t - ord('z') - 1
            if t < ord('a') :
                t = ord('z') - (ord('a') - t - 1)
            print(t)
            s[i] = chr(t)
        return "".join(s)




    def minimumRecolors(self, blocks: str, k: int) -> int:
        i, j = 0, 0
        n = len(blocks)
        ans = 0x3f3f3f3f
        cnt = 0
        k -= 1
        while j < n:
            if blocks[j] == 'W' :
               cnt += 1
            if j >= k:
                if j > k:
                    if blocks[i] == 'W' :
                        cnt -= 1
                    i += 1
                # print(j, i,  cnt)
                ans = min(ans, cnt)
            j += 1
        return ans
    def secondsToRemoveOccurrences(self, s: str) -> int:
        s = list(s)
        n = len(s)
        ans = 0
        flag = 1
        while flag :
            flag = 0
            lst = []
            for i in range(n) :
                if i < n - 1 and s[i] == '0' and s[i + 1] == '1':
                    lst.append(i)
                    flag = 1
            for i in lst:
                s[i], s[i + 1] = s[i + 1], s[i]
            print(s)
            if flag : ans += 1
        return ans

if __name__ == '__main__':
    s = Solution()
    # print(s.minimumRecolors("WBWBBBW", 2))
    # print(s.shiftingLetters("xuwdbdqik",[[4,8,0],[4,4,0],[2,4,0],[2,4,0],[6,7,1],[2,2,1],[0,2,1],[8,8,0],[1,3,1]]))
    print(s.secondsToRemoveOccurrences("0110101"))