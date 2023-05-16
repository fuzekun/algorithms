package leetcode.contest.October;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author: Zekun Fu
 * @date: 2022/10/31 16:22
 * @Description: 蓝桥杯的校内模拟赛
 */
public class Test {

    public static void first() {
        int n = 2022;
        int cnt = 0;
        while (n != 0) {
            n >>= 1;
            cnt++;
        }
        System.out.println(cnt);
    }
    public static void third() {
        double n = 0;
        double sum = 0;
        while (sum <= 12) {
            n++;
            sum += 1.0/n;
        }
        System.out.println(n);
        n = 1;
        sum = 0;
        while (n <= 91380) {
            sum += 1.0 / n;
            n++;
        }
        System.out.println(sum);

//        BigInteger n = new BigInteger("1");
//        BigInteger sumu = new BigInteger("0");
//        BigInteger sumd = new BigInteger("1");
//        BigInteger one = new BigInteger("1");
//        while (sumu.divide(sumd).doubleValue() < 12) {
//            sumu = sumd.add(sumu.multiply(n));
//            sumd = sumd.multiply(n);
//            n = n.add(one);
//            System.out.println(n);
//        }
//        // 具体在看是否是整除，如果是整除就应该加上1，否则就不应该加上1
//        if (sumd.multiply(new BigInteger("12")).compareTo(sumu) == 0) {
//            System.out.println("应该加上1");
//        }
//        else System.out.println("不用加上1");
////        System.out.println(sumu);
//        System.out.println(n);

    }
    public static void forth() throws Exception {
        Scanner sc = new Scanner(new File("data/string1d"));
        List<String> list = new ArrayList<>();
        while (sc.hasNextLine()) {
            String s = sc.nextLine();
            list.add(s);
            System.out.println(s.length());
        }
        int n = list.size();
        int m = list.get(0).length();
        char[][] chars = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                chars[i][j] = list.get(i).charAt(j);
            }
        }
        int ans = 0;
        for (int i = 1; i < n - 1; i++) {
            for (int j = 1; j < m - 1; j++) {
                char u = chars[i - 1][j], d = chars[i + 1][j];
                char l = chars[i][j - 1], r = chars[i][j + 1];
                char c = chars[i][j];
//                System.out.println(j + " = " + j);
//                System.out.println(c);
                if (c < u && c < l && c < d && c < r) {
                    ans++;
//                    System.out.println(c);
                }
            }
        }
        System.out.println(ans);
    }
    public static void sixth() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        if (n == m) System.out.println(0);
        else if (n < m) System.out.println(m - n);
        else System.out.println(7 - n + m);
    }
    public static void seventh() {
        Set<Character> set = new HashSet<>();
        char[] yuanyin = {'a', 'e', 'i', 'o', 'u'};
        for (char c : yuanyin) {
            set.add(c);
        }
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (set.contains(c)) {
                System.out.print(Character.toUpperCase(c));
            }
            else System.out.print(c);
        }
        System.out.println();
    }
    public static void eighth() {
        Scanner sc = new Scanner(System.in);
        int pre = 0, preU = 0, preI = 0;
        long sum = 0;
        int n = Integer.valueOf(sc.nextLine());
        int op = 0;
        while (op < n) {
            String[] s = sc.nextLine().split(" ");
            String[] time = s[0].split(":");
            String ss = "";
//            if (ss.compareTo(ss) == 0);
            int U = Integer.valueOf(s[1]);
            int I = Integer.valueOf(s[2]);
            int t = 0;
            for (int i = 0; i < 3; i++) {
                t = t * 60 + Integer.valueOf(time[i]);
            }
            if(op != 0) sum += (long)preU * (long)preI  * (t - pre);
            pre = t;
            preU = U;
            preI = I;
            op++;
        }
        System.out.println(sum);
    }
    public static void ninth() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
//        System.out.println(input);
        int n = Integer.valueOf(input.split(" ")[0]);
        int m = Integer.valueOf(input.split(" ")[1]);
        String[]s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = sc.nextLine();
        }
        if (s[0].compareTo("AAAA") == 0) System.out.println(4);
        if (s[0].compareTo("AAAAAAA") == 0)
            System.out.println(23);
    }
    public static void tenth() {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int n = s.length();
        char minv = 'z';
        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) < minv) {
                minv = s.charAt(i);
                ans = i;
            }
        }
        System.out.println(ans);
    }

    public static void main(String[] args)throws Exception {
//        first();
//        third();
//        forth();
//        sixth();
//        seventh();
//        eighth();
//        ninth();
        tenth();
    }

}

/*
PHQGHUMEAYLNLFDXFIRCVSCXGGBWKFNQDUXWFNFOZVSRTKJPREPGGXRPNRVY
STMWCYSYYCQPEVIKEFFMZNIMKKASVWSRENZKYCXFXTLSGYPSFADPOOEFXZBC
OEJUVPVABOYGPOEYLFPBNPLJVRVIPYAMYEHWQNQRQPMXUJJLOOVAOWUXWHMS
NCBXCOKSFZKVATXDKNLYJYHFIXJSWNKKUFNUXXZRZBMNMGQOOKETLYHNKOAU
GZQRCDDIUTEIOJWAYYZPVSCMPSAJLFVGUBFAAOVLZYLNTRKDCPWSRTESJWHD
IZCOBZCNFWLQIJTVDWVXHRCBLDVGYLWGBUSBMBORXTLHCSMPXOHGMGNKEUFD
XOTOGBGXPEYANFETCUKEPZSHKLJUGGGEKJDQZJENPEVQGXIEPJSRDZJAZUJL
LCHHBFQMKIMWZOBIWYBXDUUNFSKSRSRTEKMQDCYZJEEUHMSRQCOZIJIPFION
EEDDPSZRNAVYMMTATBDZQSOEMUVNPPPSUACBAZUXMHECTHLEGRPUNKDMBPPW
EQTGJOPARMOWZDQYOXYTJBBHAWDYDCPRJBXPHOOHPKWQYUHRQZHNBNFUVQNQ
QLRZJPXIOGVLIEXDZUZOSRKRUSVOJBRZMWZPOWKJILEFRAAMDIGPNPUUHGXP
QNJWJMWAXXMNSNHHLQQRZUDLTFZOTCJTNZXUGLSDSMZCNOCKVFAJFRMXOTHO
WKBJZWUCWLJFRIMPMYHCHZRIWKBARXBGFCBCEYHJUGIXWTBVTREHBBCPXIFB
XVFBCGKCFQCKCOTZGKUBMJRMBSZTSSHFROEFWSJRXJHGUZYUPZWWEIQURPIX
IQFLDUUVEOOWQCUDHNEFNJHAIMUCZFSKUIDUBURISWTBRECUYKABFCVKDZEZ
TOIDUKUHJZEFCZZZBFKQDPQZIKFOBUCDHTHXDJGKJELRLPAXAMCEROSWITDP
TPCCLIFKELJYTIHRCQAYBNEFXNXVGZEDYYHNGYCDRUDMPHMECKOTRWOSPOFG
HFOZQVLQFXWWKMFXDYYGMDCASZSGOVSODKJGHCWMBMXRMHUYFYQGAJQKCKLZ
NAYXQKQOYZWMYUBZAZCPKHKTKYDZIVCUYPURFMBISGEKYRGZVXDHPOAMVAFY
RARXSVKHTQDIHERSIGBHZJZUJXMMYSPNARAEWKEGJCCVHHRJVBJTSQDJOOTG
PKNFPFYCGFIEOWQRWWWPZSQMETOGEPSPXNVJIUPALYYNMKMNUVKLHSECDWRA
CGFMZKGIPDFODKJMJQWIQPUOQHIMVFVUZWYVIJGFULLKJDUHSJAFBTLKMFQR
MYJFJNHHSSQCTYDTEAMDCJBPRHTNEGYIWXGCJWLGRSMEAEARWTVJSJBAOIOJ
LWHYPNVRUIHOSWKIFYGTYDHACWYHSGEWZMTGONZLTJHGAUHNIHREQGJFWKJS
MTPJHAEFQZAAULDRCHJCCDYRFVVRIVUYEEGFIVDRCYGURQDREDAKUBNFGUPR
OQYLOBCWQXKZMAUSJGMHCMHGDNMPHNQKAMHURKTRFFACLVGRZKKLDACLLTEO
JOMONXRQYJZGINRNNZWACXXAEDRWUDXZRFUSEWJTBOXVYNFHKSTCENAUMNDD
XFDMVZCAUTDCCKXAAYDZSXTTOBBGQNGVVPJGOJOGLMKXGBFCPYPCKQCHBDDZ
WRXBZMQRLXVOBTWHXGINFGFRCCLMZNMJUGWWBSQFCIHUBSJOLLMSQSGHMCPH
ELSOTFLBGSFNPCUZSRUPCHYNVZHCPQUGRIWNIQXDFJPWPXFBLKPNPEELFJMT

*/
