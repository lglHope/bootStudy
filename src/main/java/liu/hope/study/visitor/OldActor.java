package liu.hope.study.visitor;

public class OldActor extends AbsActor {
    //不演功夫角色
    public void act(KungFuRole role) {
        System.out.println("年龄大了， 不能演功夫角色");
    }
}