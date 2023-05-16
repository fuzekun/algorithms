"""
@author:fuzekun
@file:leet793.py
@time:2022/08/28
@description: 阶乘函数后k个零

要么有5个，要么有0个

1. 2和5中小的那一个。
2. 5的个数一定是小于2的个数的。

所以直接统计含有k个5的个数。就只有5个数字。要么是0个数字。
什么时候是0个数字呢？
遇见25, 50的时候。
所以等于5的有
0，1，2，3，4，
5,
6, 7，8，9，10，
11
12, 13, 14, 15, 16,
17,
23

还存在3个5的情况125这种。
还有4个5的情况125 * 5这种
79 - 23 = 56
23 + 54 =77

直接找等于5的数量即可。

"""
from _bisect import bisect_left


def preimageSizeFZF(k: int) -> int:
    cnt, cnt2, cnt5 = 0, 0, 0
    i = 0
    ans = 0

    while cnt <= k:
        x = i
        while x and x % 2 == 0:
            x /= 2
            cnt2 += 1
        while x and x % 5 == 0:
            x /= 5
            cnt5 += 1
        cnt = min(cnt2, cnt5)
        i += 1
        if cnt == k:
            ans += 1
    print(i)
    return ans
def preimageSizeFZF2(k: int) -> int:
    def zeta(n:int)->int:

        res = 0
        while n:
            n //= 5
            res += n
        return res
    return 5 if zeta(bisect_left(range(5 * k) ,k, key=zeta)) == k else 0

if __name__ == '__main__':
    print(preimageSizeFZF(77))