package Chapter5;

import java.util.LinkedList;

/**
 * @author HJHY
 * @date 2021/8/4 - 10:46
 */
public class BinaryTree {
    /**
     * 根节点
     */
    BiTreeNode root;

    /**
     * 默认构造器使用默认的初始化方法
     */
    public BinaryTree() {
        init();
    }

    public BinaryTree(BiTreeNode root) {
        this.root = root;
    }

    /**
     * 层序初始化
     * 使用课本P115 图5.5的例子,在课本P112三种遍历顺序的答案
     */
    private void init() {
        String str = "-+/a*ef##b-######cd####";
        createTree(str);
    }

    /**
     * 层序创建二叉树
     *
     * @param str 输入字符串
     */
    private void createTree(String str) {
        //空串对应空树
        if (str == null || str.length() == 0) {
            return;
        }
        //根节点入队
        root = new BiTreeNode();
        root.data = str.charAt(0);
        //创建队列
        LinkedList<BiTreeNode> queue = new LinkedList<>();
        queue.add(root);
        //指示字符串索引值
        int index = 1;
        while (!queue.isEmpty()) {
            BiTreeNode tmp = queue.poll();
            //添加左子树
            if (str.charAt(index) != '#') {
                tmp.left = new BiTreeNode(str.charAt(index++));
                //左子树进队
                queue.add(tmp.left);
            }
            //当前字符为#,index也要自增
            else {
                ++index;
            }
            //添加右子树
            if (str.charAt(index) != '#') {
                tmp.right = new BiTreeNode(str.charAt(index++));
                //右子树进队
                queue.add(tmp.right);
            }
            //当前字符为#,index也要自增
            else {
                ++index;
            }
        }
    }

    /**
     * 前序遍历递归实现
     */
    public static void preOrderTraverse(BiTreeNode root) {
        if (root == null) {
            return;
        }
        //访问当前节点
        System.out.print(root.data + " ");
        //遍历左子树
        preOrderTraverse(root.left);
        //遍历右子树
        preOrderTraverse(root.right);
    }

    /**
     * 前序遍历非递归实现
     * 使用一个栈,采用先访问节点,右孩子进栈,左孩子进栈的顺序
     *
     * @param root 根节点
     */
    public static void preOrderTraverseNonRecursion(BiTreeNode root) {
        if (root == null) {
            return;
        }
        //申请栈
        LinkedList<BiTreeNode> stack = new LinkedList<>();
        //根节点进栈
        stack.push(root);
        while (!stack.isEmpty()) {
            //出栈并访问当前节点
            BiTreeNode cur = stack.pop();
            System.out.print(cur.data + " ");
            //右孩子进栈
            if (cur.right != null) {
                stack.push(cur.right);
            }
            //左孩子进栈
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历递归实现
     */
    public static void inOrderTraverse(BiTreeNode root) {
        if (root == null) {
            return;
        }
        //遍历左子树
        inOrderTraverse(root.left);
        //访问当前节点
        System.out.print(root.data + " ");
        //遍历右子树
        inOrderTraverse(root.right);
    }

    /**
     * 采用栈结构,先遍历左子树,访当前节点,遍历右子树
     * 此过程和递归实现的过程一模一样
     * 中序非遍历递归实现
     */
    public static void inOrderTraverseNonRecursion(BiTreeNode root) {
        if (root == null) {
            return;
        }
        //申请栈
        LinkedList<BiTreeNode> stack = new LinkedList<>();
        BiTreeNode cur = root;
        do {
            //遍历左子树
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            if (!stack.isEmpty()) {
                cur = stack.pop();
                System.out.print(cur.data + " ");
                cur = cur.right;
            }
            //循环终止的条件不只是栈空,当左边所有的节点和当前节点访问完成,会出现栈空的情况,但是此时还有右子树没有访问
        } while (!stack.isEmpty() || cur != null);
    }

    /**
     * 后序遍历递归实现
     */
    public static void postOrderTraverse(BiTreeNode root) {
        if (root == null) {
            return;
        }
        //遍历左子树
        postOrderTraverse(root.left);
        //遍历右子树
        postOrderTraverse(root.right);
        //访问当前节点
        System.out.print(root.data + " ");
    }

    /**
     * 后序遍历非递归实现
     * <p>
     * 后序遍历相对比较难理解
     * 先往左边搜索,搜索到尽头,对于这个尽头的节点不能直接访问,还要注意这个节点有无右孩子,如果有,需要等待右孩子部分才能访问该节点
     * 访问右孩子的时候需要以后孩子为起点从左开始遍历到最左一直下去
     * 可以访问该节点的要求是没有右孩子或者右孩子部分已经访问完成
     * 具体实现看代码注释
     */
    public static void postOrderTraverseNonRecursion(BiTreeNode root) {
        if (root == null) {
            return;
        }
        BiTreeNode p = root;
        LinkedList<BiTreeNode> stack = new LinkedList<>();
        //flag为false时表示处理左子树,为true时表示开始处理右子树
        boolean flag;
        //标记每一次处理完右子树后最近处理的节点;如果最后处理完的右子树节点为当前节点的右孩子,则证明已经处理完右子树;如果不是则要开始处理右子树
        BiTreeNode latestProcessNode;
        do {
            //往左一直搜索
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            latestProcessNode = null;
            flag = true;
            //左子树已经搜索到尽头,准备开始处理右子树
            while (!stack.isEmpty() && flag) {
                BiTreeNode tmp = stack.getFirst();
                //右孩子等于处理完右子树后最近处理的节点(为NULL时表示没有右孩子;不为NULL时表示右孩子已经访问完成;两种情况都允许访问当前节点)
                if (tmp.right == latestProcessNode) {
                    System.out.print(tmp.data + " ");
                    stack.pop();
                    //最近处理完的右子树节点为当前节点
                    latestProcessNode = tmp;
                } else {
                    //右子树没有处理完,开始处理右孩子
                    p = tmp.right;
                    //以新的右孩子节点为起点重新进入循环
                    flag = false;
                }
            }
        } while (!stack.isEmpty());
    }

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        System.out.print("前序递归遍历:");
        BinaryTree.preOrderTraverse(binaryTree.root);
        System.out.println();
        System.out.print("前序非递归遍历:");
        BinaryTree.preOrderTraverseNonRecursion(binaryTree.root);
        System.out.println();
        System.out.print("中序递归遍历:");
        BinaryTree.inOrderTraverse(binaryTree.root);
        System.out.println();
        System.out.print("中序非递归遍历:");
        BinaryTree.inOrderTraverseNonRecursion(binaryTree.root);
        System.out.println();
        System.out.print("后序递归遍历:");
        BinaryTree.postOrderTraverse(binaryTree.root);
        System.out.println();
        System.out.print("后序非递归遍历:");
        BinaryTree.postOrderTraverseNonRecursion(binaryTree.root);
        System.out.println();
    }
}

class BiTreeNode {
    /**
     * 数据部分
     */
    char data;
    /**
     * 左子树
     */
    BiTreeNode left;
    /**
     * 右子树
     */
    BiTreeNode right;

    public BiTreeNode() {
    }

    public BiTreeNode(char data) {
        this.data = data;
        left = null;
        right = null;
    }
}