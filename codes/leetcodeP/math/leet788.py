"""
@author:fuzekun
@file:leet788.py
@time:2022/09/25
@description:
"""


class Solution:
    def rotatedDigits(self, n: int) -> int:
        invalid = [1,3,4,7,8,0]
        lst = [0] * 10
        for x in invalid:
            lst[x] = 1
        ans = 0
        for i in range(1, n + 1) :
            if i in invalid: continue
            flag = 1
            while i :
                if lst[i % 10] :
                    flag = 0
                    break
                i //= 10
            if flag: ans += 1
        return ans

if __name__ == '__main__':
    s = Solution()
    print(s.rotatedDigits(10))