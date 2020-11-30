package liu.hope.study.visitor;

public class Client {
    public static void main(String[] args) {
//定义一个演员
        AbsActor actor = new OldActor();
//定义一个角色
        Role role = new KungFuRole();
//开始演戏
        actor.act(role);
        actor.act(new KungFuRole());
    }
}