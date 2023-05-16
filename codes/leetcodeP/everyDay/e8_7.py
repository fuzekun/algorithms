
"""

8.7的每日一题
直接使用栈进行模拟就行了
入栈的时候，不需要跟新旧栈顶的内容，只计算栈顶函数运行时间就行了，
因为
1. 在下一个函数出栈之前它不会得到运行,也就是旧栈顶内容不会使用到。
2. 而下一个函数出栈之后一定会更新旧栈顶内容，所以只有出栈的时候更新才有意义。

"""
from typing import List


class Solution:
    def exclusiveTime(self, n: int, logs: List[str]) -> List[int]:
        ans = [0] * n
        st = []             # (idx, 开始时间)
        for log in logs:
            idx, tp, timestrap = log.split(':')
            idx, timestrap = int(idx), int(timestrap)
            if tp[0] == 's':
                if st:
                    ans[st[-1][0]] += timestrap - st[-1][1] # 不用加上1，因为在开始的时候就已经结束了[start, timestrap)
                st.append([idx, timestrap])                 # 调用了新的方法
            else :
                i, t = st.pop()
                ans[i] += timestrap - t + 1     # 结束的话，需要多加上1, 因为在末尾结束[start, timestrap]
                if st:
                    st[-1][1] = timestrap + 1   # 下一个待执行的任务从下一秒开始执行
        return ans

if __name__ == '__main__':
    s = Solution()
    ans = s.exclusiveTime(2, ["0:start:0","1:start:2","1:end:5","0:end:6"])
    print(ans)