package com.hcs.datastructure.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {
    public static void main(String[] args) {
        int[] arr = {13,7,8,3,29,6,1};

        Node root = createHuffmanTree(arr);

        preOrder(root);


    }

    public static void preOrder(Node root){
        if(root!=null){
            root.preOrder();
        }else {
            System.out.println("空树，无法遍历");
        }
    }

    /**
     *
     * @param arr 需要创建为哈夫曼树的数组
     * @return 哈夫曼树的跟节点
     */
    public static Node createHuffmanTree(int[] arr){

        List<Node> nodes = new ArrayList<>();
        for (int value:arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size()>1){

            Collections.sort(nodes);

            //取出权值最小的节点
            Node leftNode = nodes.get(0);
            //第二小的节点
            Node rightNode = nodes.get(1);
            //构建二叉树
            Node parent = new Node(leftNode.value+rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //删除处理过的
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将parent加入nodes
            nodes.add(parent);
        }

        return nodes.get(0);
    }
}

class Node implements Comparable<Node>{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node node) {
        //从小到大排序
        return this.value - node.value;
    }
}