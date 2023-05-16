"""
@author:fuzekun
@file:single311.py
@time:2022/09/18
@description:
"""
from collections import deque
from typing import List, Optional


class Solution:
    # 1. 构建trie树，2.使用Bfs求解, 3.遍历trie树中单词结点
    def sumPrefixScores(self, words: List[str]) -> List[int]:
        maxn = sum(len(word) for word in words) + 5
        tr = [] * maxn
        cnt = [0] * maxn
        wordId = [] * maxn
        idx = 0
        for i in range(maxn):
            tr.append([0] * 26)
            wordId.append([])
        root = 0
        for i, word in enumerate(words):
            cur = root
            for ch in word:
                c = ord(ch) - ord('a')
                if tr[cur][c] == 0 :
                    idx += 1
                    tr[cur][c] = idx
                cur = tr[cur][c]
                cnt[cur] += 1
            wordId.append(i)
            print(cur, i)

        que = deque()
        que.append(0)
        ans = [0] * len(words)
        while que:
            u = que.popleft()
            if wordId[u]:
                for id in wordId:
                    ans[id] = cnt[u]
            for v in tr[u]:
                if v :
                    cnt[v] += cnt[u]
                    que.append(v)
        return ans

    # 1. 遍历得到每一层的结点 ， 2. 对于每一个奇数层直接进行reverse 3. 重新写入
    # def reverseOddLevels(self, root: Optional[TreeNode]) -> Optional[TreeNode]:
    #     que = []
    #     que.append([root])
    #     curl = 0
    #     while 1:
    #         flag = 0
    #         level = []
    #         for node in que[-1]:
    #             if node.left or node.right:
    #                 flag = 1
    #             if node.left: level.append(node.left)
    #             if node.right: level.append(node.right)
    #         que.append(level)
    #         if flag == 0 : break
    #     for i, level in enumerate(que):
    #         reversed(que[i])
    #     nque = []
    #     nque.append(root)
    #     curl = 0
    #     while 1:
    #         flag = 0
    #         level = []
    #         for node in nque[-1]:
    #             if node.left or node.right:
    #                 flag = 1
    #             if node.left: level.append(node.left)
    #             if node.right: level.append(node.right)
    #         if curl % 2 == 0:           # 偶数层的下一层翻转
    #             for i, node in enumerate(level):
    #                 node.val = que[curl + 1][i]
    #         nque.append(level)
    #         if flag == 0: break
    #
    #     return root




if __name__ == '__main__':
    s = Solution()
    print(s.sumPrefixScores( ["abcd"]))

