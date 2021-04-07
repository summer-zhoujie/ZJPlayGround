package com.example.playground.binarytree;

public class RBTree_3 {
    public static class Node {
        public Node left, right, parent;
        public boolean black = false;
        public int value;

        public Node grandparent() {
            return this.parent == null ? null : this.parent.parent;
        }

        public Node uncle() {
            if (grandparent() == null) {
                return null;
            } else if (parent == grandparent().left) {
                return grandparent().right;
            } else if (parent == grandparent().right) {
                return grandparent().left;
            } else {
                return null;
            }
        }

        public Node sibling() {
            if (parent == null) {
                return null;
            }
            if (parent.left == this) {
                return parent.right;
            } else {
                return parent.left;
            }
        }
    }

    private Node root;

    public void insert(int v) {

        final Node node = new Node();
        node.left = node.right = node.parent = null;
        node.black = false;
        node.value = v;

        // 树为空, 直接插入作为根节点
        if (root == null) {
            root = node;
            root.black = true;
            return;
        } else {
            Node p = findParent(v);
            // 找不到可以插入的父节点, 插入失败
            if (p == null) {
                return;
            }

            node.parent = p;
            if (v > p.value) {
                p.right = node;
            } else {
                p.left = node;
            }

            fixInsert(node);
        }
    }

    // 判断树是否被打破平衡, 是, 则需要重新平衡
    private void fixInsert(Node node) {

        // case 1: parent 和 uncle 都是红色, 直接把 [parent,uncle] 和 [grandparent] 交换颜色
        if (!node.parent.black && node.uncle() != null && !node.uncle().black) {
            node.parent.black = true;
            node.uncle().black = true;
            // 有 uncle, 肯定 grandparent 不会为 null
            node.grandparent().black = false;
            // 递归解决 grandparent 的平衡问题
            fixInsert(node.grandparent());
        } else if (node.uncle()==null) {

        }
    }

    // 找到属于v的parent准备插入
    private Node findParent(int v) {
        Node pre = root;
        Node index = root;
        while (index != null) {

            // 找到相同值直接返回
            if (index.value == v) {
                return null;
            }

            pre = index;
            index = v < index.value ? index.left : index.right;
        }
        return pre;
    }

    private void rotateLeft(Node root) {

        Node p = root.right;

        root.right = p.left;
        if (p.left != null) {
            p.left.parent = root;
        }

        p.parent = root.parent;
        if (root.parent != null) {
            if (root.parent.left == root) {
                root.parent.left = p;
            } else {
                root.parent.right = p;
            }
        }

        p.left = root;
        root.parent = p;
    }



}
