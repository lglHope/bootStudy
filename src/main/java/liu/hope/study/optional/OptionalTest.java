package liu.hope.study.optional;

import liu.hope.study.mp.entity.User;

import java.util.Optional;

public class OptionalTest {

    public static void main(String[] args) {
        User user = new User();
        user.setName("xiaoming");
        Optional<String> s = Optional.of("123");
        System.out.println(s.get());
        System.out.println(s.isPresent());
        Optional.ofNullable(null);
        Optional.ofNullable(user).map(User::getName).ifPresent(System.out::println);
        Optional.ofNullable(user).map(User::getEmail).ifPresent(System.out::println);
        String ss = Optional.ofNullable(new User()).map(User::getName).orElse("123");
        System.out.println(ss);
    }
}
