from typing import List
from collections import Counter

class Solution:
    def countBadPairs(self, nums: List[int]) -> int:
        sum, cnt = len(nums) * (len(nums) - 1) // 2, Counter()
        for i, num in enumerate(nums) :
            sum -= cnt[i - num]
            cnt[i - num] += 1
        return sum


if __name__ == '__main__':
    s = Solution()
    ans = s.countBadPairs([4,1,3,3])
    print(ans)