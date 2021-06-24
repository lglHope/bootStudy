package liu.hope.study.lambda;

import com.google.common.collect.Lists;
import liu.hope.study.mp.entity.User;

import java.util.*;
import java.util.stream.Collectors;

public class SortTest {
    public static void main(String[] args) {
        List<User> list = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            User user = new User((long)i, "", i/2, "");
            list.add(user);
        }
        list.add(new User(8L, "", 5, ""));
        list.stream().sorted(Comparator.comparingInt(User::getAge).reversed().thenComparing(User::getId).reversed()).forEach(System.out::println);

        String CLOUDAM_INPUT_PREFIX = "$123";
        String CLOUDAM_INPUT_DIR = "456";

        String ss = "$123abc";


        System.out.println(Arrays.stream(ss.split(","))
                .filter(path -> path.startsWith(CLOUDAM_INPUT_PREFIX))
                .map(path -> path.replace(CLOUDAM_INPUT_PREFIX, CLOUDAM_INPUT_DIR))
                .collect(Collectors.joining(" ")));

        System.out.println(Optional.ofNullable(null).orElse("123"));

    }
}
