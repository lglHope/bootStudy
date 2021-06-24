package liu.hope.study.leecode;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        String[] strs = {"flower","flow","flight"};
        System.out.println(new LongestCommonPrefix().longestCommonPrefix(strs));
    }

    public String longestCommonPrefix(String[] strs) {
        Map<String, Boolean> map = new HashMap<>();
        String str = "";
        for (int i = 0; i < strs[0].length(); i++) {
            str = strs[0].substring(0, strs[0].length() - i);
            for (String s : strs) {
                if (!s.startsWith(str)) {
                    map.put(str, Boolean.FALSE);
                    break;
                }
                map.put(str, Boolean.TRUE);
            }
        }
        return map.keySet().stream().filter(map::get).max(Comparator.comparingInt(String::length)).orElse("");
    }


    /**
     * public String longestCommonPrefix(String[] strs) {
     *         if (strs == null || strs.length == 0) {
     *             return "";
     *         }
     *         String prefix = strs[0];
     *         int count = strs.length;
     *         for (int i = 1; i < count; i++) {
     *             prefix = longestCommonPrefix(prefix, strs[i]);
     *             if (prefix.length() == 0) {
     *                 break;
     *             }
     *         }
     *         return prefix;
     *     }
     *
     *     public String longestCommonPrefix(String str1, String str2) {
     *         int length = Math.min(str1.length(), str2.length());
     *         int index = 0;
     *         while (index < length && str1.charAt(index) == str2.charAt(index)) {
     *             index++;
     *         }
     *         return str1.substring(0, index);
     *     }
     */
}
