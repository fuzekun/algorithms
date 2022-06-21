package leetcode.everyDay.May;

public class May_E_10 {


    public String[] grid;
    public int catJmp;
    public int mouseJump;
    public int[][] dp = new int[10][10];
    public int n, m;
    private boolean dfs(int x, int y) {
        return true;
    }
    public boolean canMouseWin() {

        return dfs(0, 0);
    }

    public static void main(String[] args) {
        May_E_10 t = new May_E_10();
        String[] s = {"M.C...F"};
        t.grid = s;
        t.catJmp = 1;
        t.mouseJump = 4;
        t.n = s.length;
        t.m = s[0].length();
        boolean ans = t.canMouseWin();
        System.out.println(ans);
    }
}
