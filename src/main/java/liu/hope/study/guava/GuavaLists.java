package liu.hope.study.guava;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

import java.util.Collection;

public class GuavaLists {
    public static void main(String[] args) {
        ImmutableList<Integer> immutableList = ImmutableList.of(1,2,0,4,5,3);
        System.out.println(Joiner.on(",").join(immutableList));
        Lists.asList("f", "abcd".split("")).forEach(System.out::println);
        Lists.charactersOf("123456").forEach(System.out::println);
        Lists.partition(immutableList, 2).stream().map(Collection::parallelStream).forEach(s -> s.forEach(System.out::println));
        Lists.partition(immutableList, 2).stream().flatMap(Collection::stream).forEach(System.out::println);
//        Lists.partition(immutableList, 2).stream().flatMap(Collection::stream).forEach(s -> System.out.println(10 / s));
        Lists.reverse(immutableList).forEach(System.out::print);

    }
}
