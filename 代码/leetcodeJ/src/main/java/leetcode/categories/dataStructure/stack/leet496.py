"""
@author:fuzekun
@file:leet496.py
@time:2022/09/28
@description:
"""
from typing import List


def nextGreaterElement( nums1: List[int], nums2: List[int]) -> List[int]:
    n = len(nums2)
    st = [-1] * n
    d = dict((nums2[i], i) for i in range(n))
    print(d)
    st = []
    greater = [-1] * n
    for i in range(n) :
        while st and nums2[st[-1]] <= nums2[i]:
            greater[st[-1]] = i
            st.pop()
        st.append(i)
    ans = []
    for x in nums1:
        idx = greater[d[x]]
        ans.append(nums2[idx] if idx != -1 else idx)
    return ans



print(nextGreaterElement([2,4],  [1,2,3,4]))