package liu.hope.study.method;

import java.util.ArrayList;
import java.util.List;

public class HNTTest {

    /**
     * 汉诺塔问题
     *
     * @param dish 盘子个数(也表示名称)
     * @param from 初始塔座
     * @param temp 中介塔座
     * @param to   目标塔座
     */
    public static void move(int dish, String from, String temp, String to) {
        if (dish == 1) {
            System.out.println("将盘子" + dish + "从塔座" + from + "移动到目标塔座" + to);
        } else {
            move(dish - 1, from, to, temp);// A为初始塔座，B为目标塔座，C为中介塔座
            System.out.println("将盘子" + dish + "从塔座" + from + "移动到目标塔座" + to);
            move(dish - 1, temp, from, to);// B为初始塔座，C为目标塔座，A为中介塔座
        }
    }

    public static void main(String[] args) {
        move(3, "A", "B", "C");
    }
}
