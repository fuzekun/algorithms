package leetcode.categories.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 *
 *  猫捉老鼠游戏的拓扑排序
 *
 */
public class Tuopu {

    static final int MOUSE_TURN = 0, CAT_TURN = 1;
    static final int DRAW = 0, MOUSE_WIN = 1, CAT_WIN = 2;
    int[][] graph;
    int[][][] degrees;
    int[][][] results;

    public int catMouseGame(int[][] graph) {
        int n = graph.length;
        this.graph = graph;
        this.degrees = new int[n][n][2];
        this.results = new int[n][n][2];
        Queue<int[]> queue = new ArrayDeque<int[]>();

        /*
        *
        *   统计每一个点的猫和老鼠的所有的度。
        *   猫统计除了0之外的所有的度
        *
        * */
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                degrees[i][j][MOUSE_TURN] = graph[i].length;
                degrees[i][j][CAT_TURN] = graph[j].length;
            }
        }
        for (int node : graph[0]) {
            for (int i = 0; i < n; i++) {
                degrees[i][node][CAT_TURN]--;
            }
        }

        /*
        *
        *   边界情况
        * 1. 老鼠在洞里面的时候，猫在哪都是输。
        * 2. 猫和老鼠在任意一个点（0除外）相遇的时候，猫赢
        * 3. 把边界情况，放入队列中去
        * */
        for (int j = 1; j < n; j++) {
            results[0][j][MOUSE_TURN] = MOUSE_WIN;
            results[0][j][CAT_TURN] = MOUSE_WIN;
            queue.offer(new int[]{0, j, MOUSE_TURN});
            queue.offer(new int[]{0, j, CAT_TURN});
        }
        for (int i = 1; i < n; i++) {
            results[i][i][MOUSE_TURN] = CAT_WIN;
            results[i][i][CAT_TURN] = CAT_WIN;
            queue.offer(new int[]{i, i, MOUSE_TURN});
            queue.offer(new int[]{i, i, CAT_TURN});
        }

        /*
        *
        *
        *   进行拓扑排序
        * */
        while (!queue.isEmpty()) {
            int[] state = queue.poll();
            int mouse = state[0], cat = state[1], turn = state[2];
            int result = results[mouse][cat][turn];
            List<int[]> prevStates = getPrevStates(mouse, cat, turn);

            // 获取前面的所有状态
            for (int[] prevState : prevStates) {

                int prevMouse = prevState[0], prevCat = prevState[1], prevTurn = prevState[2];
                // 如果点已经再队中了，就不需要再入队了。
                if (results[prevMouse][prevCat][prevTurn] == DRAW) {
                    // 情况1. 获胜方和移动方相同，直接判定前一个结点的状态
                    boolean canWin = (result == MOUSE_WIN && prevTurn == MOUSE_TURN) || (result == CAT_WIN && prevTurn == CAT_TURN);
                    if (canWin) {
                        results[prevMouse][prevCat][prevTurn] = result;
                        queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                    } else {
                        // 情况2：不同的时候，无法根据当前结点推出前一个结点的状态，需要配合其他的结点进行判断
                        degrees[prevMouse][prevCat][prevTurn]--;
                        // 所有的情况都判断完成了, 都是没法赢的状态，那走的那个就是必输
                        if (degrees[prevMouse][prevCat][prevTurn] == 0) {
                            int loseResult = prevTurn == MOUSE_TURN ? CAT_WIN : MOUSE_WIN;
                            results[prevMouse][prevCat][prevTurn] = loseResult;
                            queue.offer(new int[]{prevMouse, prevCat, prevTurn});
                        }
                    }
                }
            }
        }
        return results[1][2][MOUSE_TURN];
    }

    public List<int[]> getPrevStates(int mouse, int cat, int turn) {
        List<int[]> prevStates = new ArrayList<int[]>();
        int prevTurn = turn == MOUSE_TURN ? CAT_TURN : MOUSE_TURN;
        if (prevTurn == MOUSE_TURN) {
            for (int prev : graph[mouse]) {
                prevStates.add(new int[]{prev, cat, prevTurn});
            }
        } else {
            for (int prev : graph[cat]) if (prev != 0) prevStates.add(new int[]{mouse, prev, prevTurn});
        }
        return prevStates;
    }
}
