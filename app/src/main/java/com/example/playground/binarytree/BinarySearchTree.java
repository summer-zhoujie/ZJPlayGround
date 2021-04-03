package com.example.playground.binarytree;

import java.util.Random;

public class BinarySearchTree {

    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return show(this);
        }
    }

    private Node root;

    public Node search(int num) {
        return doSearch(root, num);
    }

    private Node doSearch(Node root, int num) {
        if (root == null) {
            return null;
        }

        if (root.data == num) {
            return root;
        } else if (root.data > num) {
            return doSearch(root.left, num);
        } else if (root.data < num) {
            return doSearch(root.right, num);
        }
        return null;
    }

    public void insert(int num) {
        root = doInsert(root, num);
    }

    private Node doInsert(Node parent, int num) {
        if (parent == null) {
            parent = new Node(num);
        } else if (num > parent.data) {
            parent.right = doInsert(parent.right, num);
        } else if (num < parent.data) {
            parent.left = doInsert(parent.left, num);
        }

        return parent;
    }

    public void remove(int num) {
        root = doRemove(root, num);
    }

    private Node doRemove(Node parent, int num) {

        if (parent == null) {
            return null;
        }

        if (num > parent.data) {
            parent.right = doRemove(parent.right, num);
        } else if (num < parent.data) {
            parent.left = doRemove(parent.left, num);
        }
        // 找出左子树最大的值或者右子树最小的值替换, 这里选择前者来实现
        else if (parent.left != null && parent.right != null) {

            // 找到左子树最大值替换
            parent.data = findMax(parent.left).data;
            // 删除左子树中用于替换的节点
            parent.left = doRemove(parent.left, parent.data);
        }
        // 左子树为空, 直接用右子树根节点替换被删除的节点
        else if (parent.left == null) {
            parent = parent.right;
        }
        // 右子树为空, 直接用左子树根节点替换被删除的节点
        else if (parent.right == null) {
            parent = parent.left;
        }

        return parent;
    }

    private Node findMax(Node node) {
        if (node == null) {
            return node;
        }
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    @Override
    public String toString() {
        return root == null ? "空" : root.toString();
    }


    public static void main(String[] args) {
        final BinarySearchTree tree = new BinarySearchTree();
        System.out.println(tree);
        final Random random = new Random();
        final int INIT_SIZE = 10;
        final int[] ints = new int[INIT_SIZE];
        for (int i = 0; i < INIT_SIZE; i++) {
            final int num = random.nextInt(100);
            System.out.println("add=" + num);
            ints[i] = num;
            tree.insert(num);
        }
        System.out.println(tree);
        for (int i = 0; i < 1; i++) {
            final int anInt = ints[random.nextInt(INIT_SIZE)];
            System.out.println("remove=" + anInt);
            tree.remove(anInt);
        }
        System.out.println(tree);

        for (int i = 0; i < 1; i++) {
            final int anInt = ints[random.nextInt(INIT_SIZE)];
            final Node search = tree.search(anInt);
            System.out.println("search=" + anInt + ", result=" + (search != null));
        }
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    // print-utils

    private static int getTreeDepth(Node root) {
        return root == null ? 0 : (1 + Math.max(getTreeDepth(root.left), getTreeDepth(root.right)));
    }


    private static void writeArray(Node currNode, int rowIndex, int columnIndex, String[][] res, int treeDepth) {
        // 保证输入的树不为空
        if (currNode == null) return;
        // 先将当前节点保存到二维数组中
        res[rowIndex][columnIndex] = String.valueOf(currNode.data);

        // 计算当前位于树的第几层
        int currLevel = ((rowIndex + 1) / 2);
        // 若到了最后一层，则返回
        if (currLevel == treeDepth) return;
        // 计算当前行到下一行，每个元素之间的间隔（下一行的列索引与当前元素的列索引之间的间隔）
        int gap = treeDepth - currLevel - 1;

        // 对左儿子进行判断，若有左儿子，则记录相应的"/"与左儿子的值
        if (currNode.left != null) {
            res[rowIndex + 1][columnIndex - gap] = "/";
            writeArray(currNode.left, rowIndex + 2, columnIndex - gap * 2, res, treeDepth);
        }

        // 对右儿子进行判断，若有右儿子，则记录相应的"\"与右儿子的值
        if (currNode.right != null) {
            res[rowIndex + 1][columnIndex + gap] = "\\";
            writeArray(currNode.right, rowIndex + 2, columnIndex + gap * 2, res, treeDepth);
        }
    }


    public static String show(Node root) {
        if (root == null) System.out.println("EMPTY!");
        // 得到树的深度
        int treeDepth = getTreeDepth(root);

        // 最后一行的宽度为2的（n - 1）次方乘3，再加1
        // 作为整个二维数组的宽度
        int arrayHeight = treeDepth * 2 - 1;
        int arrayWidth = (2 << (treeDepth - 2)) * 3 + 1;
        // 用一个字符串数组来存储每个位置应显示的元素
        String[][] res = new String[arrayHeight][arrayWidth];
        // 对数组进行初始化，默认为一个空格
        for (int i = 0; i < arrayHeight; i++) {
            for (int j = 0; j < arrayWidth; j++) {
                res[i][j] = " ";
            }
        }

        // 从根节点开始，递归处理整个树
        // res[0][(arrayWidth + 1)/ 2] = (char)(root.val + '0');
        writeArray(root, 0, arrayWidth / 2, res, treeDepth);

        // 此时，已经将所有需要显示的元素储存到了二维数组中，将其拼接并打印即可
        StringBuilder sb = new StringBuilder();
        for (String[] line : res) {
            for (int i = 0; i < line.length; i++) {
                sb.append(line[i]);
                if (line[i].length() > 1 && i <= line.length - 1) {
                    i += line[i].length() > 4 ? 2 : line[i].length() - 1;
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }


}
