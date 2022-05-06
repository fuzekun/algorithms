package leetcode.everyDay.May;

/*
*
*
*   找出游戏的胜利者。
*   经典的约瑟夫环的模拟题。
* */
public class May_E_5 {
    public int findTheWinner(int n, int k) {
        k--;
        boolean[] vis = new boolean[n + 1];
        for (int i = 0, j = 0, cnt = 0; i < n - 1; i++) {   // n - 1轮
            while(cnt < k) {
                if (!vis[j]) cnt++;
                j = (j + 1) % n;
            }
            while(vis[j]) j = (j + 1) % n;      // 找下一个需要喊的人
            cnt = 0;
            vis[j] = true;                      // 出局
            System.out.println(j + 1);
        }
        for (int i = 0; i < n; i++) {
            if (!vis[i]) return i + 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(new May_E_5().findTheWinner(6, 5));
    }
}
