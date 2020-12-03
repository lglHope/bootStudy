package liu.hope.study.heap;

public class Heap {
    private Node[] heapArray;
    private int maxSize;
    private int currentSize;

    public Heap(int mx) {
        maxSize = mx;
        currentSize = 0;
        heapArray = new Node[maxSize];
    }

    public boolean isEmpty() {
        return currentSize == 0 ;
    }

    public boolean isFull() {
        return currentSize == maxSize;
    }

    public boolean insert(int key) {
        if (isFull()) {
            return false;
        }
        Node newNode = new Node(key);
        heapArray[currentSize] = newNode;
        trickleUp(currentSize++);
        return true;
    }

    //向上调整   向上筛选和向下不同，向上筛选只用和一个父节点进行比较，比父节点小就停止筛选了
    public void trickleUp(int index) {
        int parent = (index -1) /2; //父节点的索引
        Node bottom = heapArray[index]; //将新加的尾节点存在bottom中
        while (index > 0 && heapArray[parent].getKey() < bottom.getKey()) {
            heapArray[index] = heapArray[parent];
            index = parent;
            parent = (parent -1) /2;
        }
        heapArray[index] = bottom;
    }

    public Node remove() {
        Node root = heapArray[0];
        heapArray[0] = heapArray[--currentSize];
        trickleDown(0);
        return root;
    }

    //向下调整   一直向下筛选这个节点，直到它在一个大于它的节点之下，小于它的节点之上为止
    public void trickleDown(int index) {
        Node top = heapArray[index];
        int largeChildIndex;
        while (index < currentSize / 2) { //while node has at least one child
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = leftChildIndex + 1;
            //find larger child
            if (rightChildIndex < currentSize && //rightChild exists?
                    heapArray[leftChildIndex].getKey() < heapArray[rightChildIndex].getKey()) {
                largeChildIndex = rightChildIndex;
            } else {
                largeChildIndex = leftChildIndex;
            }
            if (top.getKey() >= heapArray[largeChildIndex].getKey()) {
                break;
            }
            heapArray[index] = heapArray[largeChildIndex];
            index = largeChildIndex;
        }
        heapArray[index] = top;
    }

    //根据索引改变堆中某个数据
    public boolean change(int index, int newValue) {
        if (index < 0 || index >= currentSize) {
            return false;
        }
        int oldValue = heapArray[index].getKey();
        heapArray[index].setKey(newValue);
        if (oldValue < newValue) {
            trickleUp(index);
        } else {
            trickleDown(index);
        }
        return true;
    }

    public void displayHeap() {
        System.out.println("heapArray(array format): ");
        for (int i = 0; i < currentSize; i++) {
            if (heapArray[i] != null) {
                System.out.print(heapArray[i].getKey() + " ");
            } else {
                System.out.print("--");
            }
        }
    }
}

class Node {
    private int iData;

    public Node(int key) {
        iData = key;
    }

    public int getKey() {
        return iData;
    }

    public void setKey(int key) {
        iData = key;
    }
}