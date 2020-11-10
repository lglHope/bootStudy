package liu.hope.study.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;

public class GuavaLists {
    public static void main(String[] args) {
        ImmutableList<Integer> immutableList = ImmutableList.of(1,2,3,4,5);
        System.out.println(Joiner.on(",").join(immutableList));
    }
}
