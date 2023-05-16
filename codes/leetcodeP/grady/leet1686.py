"""
@author:fuzekun
@file:leet1686.py
@time:2022/09/23
@description:
石子游戏2：

两种思维：dp思维或者贪心思维
"""
from typing import List

# 定义alice比bob多得的分为ai - bi
#
# 选择i,
# s += ai
# 不选i,
# s -= bi


def stoneGameVI( aliceValues: List[int], bobValues: List[int]) -> int:
    nums = [(x + y, i) for i, (x, y) in enumerate(zip(aliceValues, bobValues))]
    nums.sort(key=lambda x: -x[0])
    # print(nums)
    total = 0
    for i, x in enumerate(nums):
        total += aliceValues[x[1]] if i % 2 == 0 else -bobValues[x[1]]
    return 0 if total == 0 else -1 if total < 0 else 1


print(stoneGameVI([1,2], [3, 1]))