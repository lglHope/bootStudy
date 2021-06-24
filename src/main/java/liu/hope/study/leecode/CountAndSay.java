package liu.hope.study.leecode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 *
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 *
 * 你可以将其视作是由递归公式定义的数字字符串序列：
 *
 * countAndSay(1) = "1"
 * countAndSay(n) 是对 countAndSay(n-1) 的描述，然后转换成另一个数字字符串。
 */
public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(new CountAndSay().countAndSay(6));
    }

    public String countAndSay(int n) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "1");
        map.put(2, "11");
        for (int i = 3; i <= n; i++) {
            map.put(i, "");
            char[] chars = map.get(i - 1).toCharArray();
            int last = chars.length;
            for (int j = 0; j < last; j++) {
                char cc = chars[j];
                if (cc == '0') continue;
                int count = 0;
                for (int k = j; k < last; k++) {
                    if (cc == chars[k]) {
                        count++;
                        chars[k] = '0';
                    } else {
                        break;
                    }
                }
                if (count != 0)
                    map.put(i, map.get(i) + count + cc);
            }
        }
        return map.get(n);
    }
}
