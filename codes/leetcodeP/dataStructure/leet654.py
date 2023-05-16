"""
@author:fuzekun
@file:leet654.py
@time:2022/08/20
@description:
"""
from typing import List, Optional


class TreeNode:
    def __init__(self, val=0, left=None, right=None):
        self.val = val
        self.left = left
        self.right = right
class Solution:
    def constructMaximumBinaryTree(self, nums: List[int]) -> Optional[TreeNode]:
        def dfs(l:int, r:int)->TreeNode :
            if l == r :
                return TreeNode(nums[l])
            s = max(nums)
            p = nums.index(s)
