package liu.hope.study.tree;

public class Node {
    public int data; //节点数据
    public Node leftChild; //左子节点的引用
    public Node rightChild; //右子节点的引用
    public boolean isDelete;//表示节点是否被删除

    public Node(int data) {
        this.data = data;
    }

    public Node() {
    }

    //打印节点内容
    public void display() {
        System.out.println(data);
    }
}