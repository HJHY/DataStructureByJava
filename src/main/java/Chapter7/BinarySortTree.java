package Chapter7;

import javafx.scene.Parent;

import java.util.ArrayList;
import java.util.List;

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
     * 插入二叉搜索树,返回值是为了维护父节点(参数传递进去的时候对对象参数的修改只是修改了指向没有修改对象,影响不能出函数外)
     *
     * @param root root
     * @param e    data
     * @return 子树的引用, 用于维护父节点
     */
    private Node insert(Node root, int e) {
        //当前节点为空,申请新节点然后挂上
        if (root == null) {
            return new Node(e);
        }
        //比当前节点大,插入右子树
        if (root.data < e) {
            root.right = insert(root.right, e);
        }
        //比当前节点小,插入左子树
        else if (root.data > e) {
            root.left = insert(root.left, e);
        }
        return root;
    }

    /**
     * 在插入的时候要注意维护父节点,否则会出现创建无法在函数外生效的情况
     *
     * @param e data
     */
    public void insert(int e) {
        this.root = insert(root, e);
    }

    /**
     * 获取排序序列
     *
     * @return list
     */
    public List<Integer> getSortedList() {
        ArrayList<Integer> list = new ArrayList<>();
        inOrderTraverse(root, list);
        return list;
    }

    private void inOrderTraverse(Node root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left, list);
        list.add(root.data);
        inOrderTraverse(root.right, list);
    }

    /**
     * 删除元素为e的节点
     * 这个方法实现很复杂对所有情况进行了详细的分析,其中也出现了修改引用阿德影响没有持续的情况,只是通过部分修改节点的值,部分修改引用实现,后续将简化
     *
     * @param key data
     */
    public void delete(int key) {
        /*采用替换删除节点的前驱节点的方法,删除的节点有3种可能性,叶子节点,只有一个孩字的节点,两个孩子的节点*/
        //删除节点
        Node deleteNode = root;
        //删除节点前驱节点
        Node preNode;
        //删除节点父节点
        Node deleteParentNode = root;
        //前驱节点父节点
        Node preParentNode;
        //寻找删除的节点
        while (deleteNode != null) {
            //找到退出循环停止搜索
            if (deleteNode.data == key) {
                break;
            }
            deleteParentNode = deleteNode;
            //key大,向右搜索
            if (deleteNode.data < key) {
                deleteNode = deleteNode.right;
            } else {
                deleteNode = deleteNode.left;
            }
        }
        if (deleteNode == null) {
            System.out.println("没有找到元素" + key);
            return;
        }
        /*分3种情况考虑*/
        //1.叶子节点
        if (deleteNode.left == null && deleteNode.right == null) {
            //只有根节点并且删除
            if (deleteParentNode == deleteNode) {
                this.root = null;
            }
            //父节点指向后继,同时判断是左子树还是右子树
            if (deleteParentNode.left == deleteNode) {
                deleteParentNode.left = null;
            } else {
                deleteParentNode.right = null;
            }
        }
        //2.有两个孩子
        else if (deleteNode.left != null && deleteNode.right != null) {
            //找到左孩子的最右节点以及其父节点
            preNode = deleteNode.left;
            preParentNode = deleteParentNode;
            while (preNode.right != null) {
                preParentNode = preNode;
                preNode = preNode.right;
            }
            //前驱为删除节点的左孩子,直接接上
            if (preNode == deleteNode.left) {
                deleteParentNode.left.data = preNode.data;
                deleteNode.left = null;
            } else {
                //将左孩子的最右节点的左孩子挂到其父节点的右边
                preParentNode.right = preNode.left;
                //前驱节点替换删除节点
                preNode.left = deleteNode.left;
                preNode.right = deleteNode.right;
                deleteNode.data = preNode.data;
            }
        }
        //只有左孩子或者右孩子
        else {
            //只有右孩子
            if (deleteNode.left == null) {
                if (deleteParentNode.left == deleteNode) {
                    deleteParentNode.left = deleteNode.right;
                } else {
                    deleteParentNode.right = deleteNode.right;
                }
            } else {
                if (deleteParentNode.left == deleteNode) {
                    deleteParentNode.left = deleteNode.left;
                } else {
                    deleteParentNode.right = deleteNode.left;
                }
            }
        }
    }

    public static void main(String[] args) {
        /*测试用例*/
        int[] array = new int[]{45, 12, 3, 37, 24, 53, 100, 61, 90, 78};
        BinarySortTree binarySortTree = new BinarySortTree();
        binarySortTree.createTree(array);
        System.out.println(binarySortTree.getSortedList());
        binarySortTree.delete(1000);
        System.out.println(binarySortTree.getSortedList());
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}