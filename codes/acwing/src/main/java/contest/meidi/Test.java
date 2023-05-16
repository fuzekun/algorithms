package contest.meidi;

/**
 * @author: Zekun Fu
 * @date: 2023/4/8 20:13
 * @Description:
 */

class A {
    public static void test(){
        System.out.println("A的test");
    }
}
class B extends A{

}
public class Test {




    public static void main(String[] args) {

        A a = new B();
        a.test();

        int x = 1, y = 2;
        // 强类型，int无法转化成boolean类型
        //        if (x = y) {
//            System.out.println("true");
//        }
//        else System.out.println("not");

    }
}
