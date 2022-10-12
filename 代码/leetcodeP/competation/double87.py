"""
@author:fuzekun
@file:double87.py
@time:2022/09/17
@description:
"""
from _bisect import bisect_left
from typing import List


class Solution:
    def countDaysTogether(self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str) -> int:
        amonth = [arriveAlice.split('-')[0], leaveAlice.split('-')[0]]
        aday = [arriveAlice.split('-')[1], leaveAlice.split('-')[1]]
        bmonth = [arriveBob.split('-')[0], leaveBob.split('-')[0]]
        bday = [arriveBob.split('-')[1], leaveBob.split('-')[1]]

    def matchPlayersAndTrainers(self, players: List[int], trainers: List[int]) -> int:
        ans = 0
        trainers.sort()
        players.sort()
        i = 0
        n = len(trainers)
        for x in players:
            while i < n and trainers[i] < x: i += 1
            if i == n: break
            ans += 1
            i += 1
        return ans

    # 找到某一位1的最前位置，然后其中最后的就是最短的
    def smallestSubarrays(self, nums: List[int]) -> List[int]:
        pre = [-1] * 32
        for i, x in enumerate(nums) :
            t = 0
            while x:
                pre[t] = x % 2
                x /= 2
                t += 1
            

    # 求交易的最大值
    def minimumMoney(self, transactions: List[List[int]]) -> int:
        return 0
if __name__ == '__main__':
    s = Solution()
    print(s.matchPlayersAndTrainers([4,7,9], [8,2,5,8]))