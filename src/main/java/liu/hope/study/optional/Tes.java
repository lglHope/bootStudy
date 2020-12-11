package liu.hope.study.optional;

import com.google.common.collect.Maps;

import java.util.*;

public class Tes {
    public static void main(String[] args) {
        String s = "loveleetcode";
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            Integer va = map.get(aChar) != null ? map.get(aChar) : 0;
            map.put(aChar, va + 1);
        }
        for (int i = 0; i < s.length(); i++) {
            if (map.get(s.charAt(i)) == 1) {
                System.out.println(i);
            }
        }
    }
}
