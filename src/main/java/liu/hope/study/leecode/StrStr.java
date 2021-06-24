package liu.hope.study.leecode;

/**
 * 给你两个字符串haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串出现的第一个位置（下标从 0 开始）。如果不存在，则返回 -1 。
 *
 */
public class StrStr {
    public static void main(String[] args) {
        System.out.println(new StrStr().strStr("abc","c"));
    }

    public int strStr(String haystack, String needle) {
        if (needle == null || "".equals(needle)) {
            return 0;
        }
        if (needle.length() > haystack.length()) {
            return -1;
        }

        char[] nc = needle.toCharArray();
        char[] hc = haystack.toCharArray();
        int last = haystack.length() - needle.length();
        for (int i = 0; i <= last; i++) {
            boolean flag = true;
            int nl = needle.length();
            int no = 0;
            int ho = i;
            while (--nl >=0) {
                if (hc[ho++] != nc[no++]) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                return i;
        }
        return -1;
    }
}
