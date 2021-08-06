package Chapter7;

/**
 * @author HJHY
 * @date 2021/8/6 - 10:19
 */
public class BinarySortTree {
    Node root;

    public void createTree(int[] array) {
        for (int a : array) {
            insert(a);
        }
    }

    /**
     * 插入二叉搜索树
     */
    private void insert(Node root, int e) {
        //当前节点为空,申请新节点然后挂上
        if (root == null) {
            root = new Node();
            root.data = e;
            return;
        }
        //比当前节点大,插入右子树
        if (root.data < e) {
            insert(root.right, e);
        }
        //比当前节点小,插入左子树
        else if (root.data > e) {
            insert(root.left, e);
        }
    }

    public void insert(int e) {
        insert(root, e);
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{45, 12, 3, 37, 24, 53, 100, 61, 90, 78};
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.createTree(array);
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node() {
    }
}