# 题目列表
- [最小高度树](https://leetcode.cn/problems/minimum-height-trees/submissions/)
- [统计可能的树根树](https://leetcode.cn/problems/count-number-of-possible-root-nodes/)
- [树中距离之和](https://leetcode.cn/problems/sum-of-distances-in-tree/submissions/)


# 基本思路

1. 首先dfs(0, -1)，得到每一个结点的子节点的信息dp，比如高度最大值，次大值，子节点的数量。
2. 再次dfs,每次dfs的过程中，将(u, v)交换，**这样每次进来redfs的时候u都是根结点**，**所以把v换到根节点，就只有u和v之间的dp不会变。**
   1. u的子节点，除了v没有动。
   2. v的子节点，多了u，其他的没有动。
3. 所以只要先更新u，然后把u当作v的子节点，更新v，就可以得到一棵新的以v为根的树了。注意每一次for循环的时候，都需要先把u和v复原。实在不行，也得把u复原。v和不会遍历第二次，所以复原不复原都行。
