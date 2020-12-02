package liu.hope.study.tree;

public interface Tree {
    //查找节点
    Node find(int key);

    //插入新节点
    boolean insert(int key);

    //删除节点
    boolean delete(int key);

    //Other Method......
}