package liu.hope.study.leecode;

public class LuoMa2Int {

    public static void main(String[] args) {
        System.out.println(new LuoMa2Int().romanToInt("IV"));
    }

    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i < chars.length; i++) {
            if (i == (chars.length -1)) {
                sum += char2Value(chars[i]);
                return sum;
            }
            int current = char2Value(chars[i]);
            int next = char2Value(chars[i + 1]);
            if (current < next) {
                sum -= current;
            }else {
                sum += current;
            }
        }
        return sum;
    }

    private int char2Value(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
        }
        return 0;
    }

}
