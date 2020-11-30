package liu.hope.study.completableFuture;

import liu.hope.study.mp.entity.User;
import liu.hope.study.mp.serivce.IUserService;
import liu.hope.study.mp.serivce.impl.UserServiceImpl;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CompletableFutureTest {
    public static void main(String[] args) {
        IUserService userService = new UserServiceImpl();
        CompletableFuture<List<User>> listCompletableFuture1 = CompletableFuture.supplyAsync(userService::list, new ForkJoinPool(10));
        CompletableFuture<List<User>> listCompletableFuture2 = CompletableFuture.supplyAsync(userService::list, new ForkJoinPool(10));
        List<User> join = listCompletableFuture1.join();
        CompletableFuture.allOf(listCompletableFuture1, listCompletableFuture2).join();
        CompletableFuture<Object> objectCompletableFuture = CompletableFuture.anyOf(listCompletableFuture1, listCompletableFuture2);
        listCompletableFuture1.thenApply(f -> f.stream().collect(Collectors.toMap( User::getId, Function.identity(), (a, b) -> a)));
    }
}
