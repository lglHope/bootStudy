package liu.hope.study.guava;

import com.google.common.base.Strings;

import javax.annotation.Nullable;

public class GuavaString {
    public static void main(String[] args) {
        System.out.println(isNullOrEmpty(""));
        System.out.println(isNullOrEmpty("1"));
        System.out.println(isNullOrEmpty(null));

        System.out.println(commonPrefix("abc","abcd"));
        System.out.println(commonSuffix("abc","dbc"));
    }

    /**
     * 判断字符串是否为空或者null
     * @param str
     * @return
     */
    public static boolean isNullOrEmpty(String str) {
        return Strings.isNullOrEmpty(str);
    }

    /**
     * 取公共的前缀
     * @param a
     * @param b
     * @return
     */
    public static String commonPrefix(CharSequence a, CharSequence b) {
        return Strings.commonPrefix(a, b);
    }

    /**
     * 取公共的后缀
     * @param a
     * @param b
     * @return
     */
    public static String commonSuffix(CharSequence a, CharSequence b) {
        return Strings.commonSuffix(a, b);
    }

}
