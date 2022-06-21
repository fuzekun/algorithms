package leetcode.hardQ.may;


// 表驱动
public class ValidNumber3 {
    public int make(char c) {
        switch(c) {
            case '+':
            case '-': return 0;
            case 'E':
            case 'e': return 2;
            case '.': return 3;
            default:
                if(c >= 48 && c <= 57) return 1;
        }
        return 4;           // 其余字符返回4
    }

    public boolean isNumber(String s) {
        int state = 0;
        int finals = 0b010011100;       // 终止可以的状态有4种
        int[][] transfer = new int[][] {
                {1, 2, -1, 8, -1},
                {-1, 2, -1, 8, -1},
                {-1, 2, 5, 3, -1},
                {-1, 4, 5, -1, -1},
                {-1, 4, 5, -1, -1},
                {6, 7, -1, -1, -1},
                {-1, 7, -1, -1, -1},
                {-1, 7, -1, -1, -1},
                {-1, 4, -1, -1, -1},
        };
        char[] ss = s.toCharArray();
        for(int i=0; i < ss.length; ++i) {
            int id = make(ss[i]);
            state = transfer[state][id];
//            System.out.println(ss[i]);
            if (state < 0) return false;
        }
        return (finals & (1 << state)) > 0;
    }
    public static void main(String[] args) {
        String[] ss = {"2", "0089", "-0.1", "+3.14", "4.", "-.9", "2e10", "-90E3", "3e+7", "+6e-1", "53.5e93", "-123.456e789"};
        String[] ss2 = {"abc", "1a", "1e", "e3", "99e2.5", "--6", "-+3", "95a54e53"};
        ValidNumber3 first = new ValidNumber3();
        for (String s : ss) {
            System.out.println(first.isNumber(s));
        }
    }
}
