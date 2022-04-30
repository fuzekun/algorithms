package leetcode;


import leetcode.everyDay.April.Main;

/*
*
*   测试单例模式
* */
public class TestSigle {

    public static void main(String[] args) {
        Main main;
        for (int i = 0; i < 10; i++) {
            main = Main.getInstance();
            System.out.println(main.hashCode());
        }
    }
}
