"""
@author:fuzekun
@file:KMP.py
@time:2022/09/01
@description:

KMP算法模板
"""
from collections import deque


def KMP(s:str, p:str) -> list:          # 返回可以匹配到的所有字符串
    n, m = len(p), len(s)
    nx = [-1] * (n + 1)
    j, k = 0, -1
    while j < n:
        if k == -1 or p[j] == p[k]:
            j += 1
            k += 1
            if j != n and p[j] == p[k]:  # 加上这两行就是修正后的KMP蒜贩
                nx[j] = nx[k]
            else : nx[j] = k
        else :
            k = nx[k]
    print(nx)
    i, j = 0, 0
    ans = []
    while i < m:
        if j == -1 or s[i] == p[j] :
            i += 1
            j += 1
        else : j = nx[j]
        if (j == n) :
            ans.append(i - j)
            j = nx[j]

    return ans


if __name__ == '__main__':
    KMP('aaaabbc', 'aaaabbc')
    n = int(input())
    p = input()
    m = int(input())
    s = input()
    ans = KMP(s, p)
    print(ans)

    que = deque()
