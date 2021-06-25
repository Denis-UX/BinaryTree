package com.company;


import java.io.PrintStream;
import java.util.LinkedList;

public class BinaryTree{

    Node root;
    LinkedList<Node> nodes = new LinkedList<>();

    public void treeCreator(char[] var){
        if(var.length < 1) return;

        for (char c : var) {
            root = nodeConector(root, c);
        }
        chechForBalance(root);

        printNodeValues(System.out);

        printingTree();
    }

    private Node nodeCreator(char value){
        return new Node(value);
    }


    private Node nodeConector(Node node, char value) {

        if (node == null) {
            node = nodeCreator(value);
            nodes.add(node);
            return node;
        }

        int comparValues = Character.compare(node.value, value);

        if (comparValues > 0 ) {
            node.numberOfNodes++;
            node.left = nodeConector(node.left, value);
            return node;
        }

        else if (comparValues < 0) {
            node.numberOfNodes++;
            node.right = nodeConector(node.right, value);
            return node;
        }

        else {
            int rp = node.getRep() + 1;
            node.setRep(rp);
        }
        return node;
    }

    public int nodeCounter(Node root){
        if (root == null) return 0;
        return 1 + nodeCounter(root.right) + nodeCounter(root.left);
    }

    public void NumOfNodes(Node root){
        if(root == null) return;
        root.numberOfNodes = nodeCounter(root);
        if(root.left != null) {
            NumOfNodes(root.left);}
        if(root.right != null) {
            NumOfNodes(root.right);}
    }

    public void chechForBalance(Node root){
        if(root == null) return;
        NumOfNodes(root);
        int a,b;
        if(root.left != null) {
            a = root.left.numberOfNodes;

        }else{a = 1;}

        if (root.right != null) {
            b = root.right.numberOfNodes;

        }else {b = 1;}
        root.distinction = Math.abs(a - b);
        recursionForBalance(root);
    }

    public void recursionForBalance(Node root){
        if(root == null) return;
        if(root.left != null) {
            chechForBalance(root.left);}
        if(root.right != null) {
            chechForBalance(root.right);}
    }

    public void printingTree(){
        int max = nodes.size() - 1, second_max = max;
        for(int i = 0; i < nodes.size(); i++) {
            if(nodes.get(i).distinction > nodes.get(max).distinction){
                max = i;
            }
            else if(nodes.get(i).distinction > nodes.get(second_max).distinction){
                second_max = i;
            }
        }
        System.out.println("Most unbalanced node is: "+ nodes.get(max).value + " || " + nodes.get(max).getRep());
        System.out.println("\nSecond most unbalanced node is: " + nodes.get(second_max).value + " || " + nodes.get(second_max).getRep());
    }

    private void printNodeValues(PrintStream os){
        StringBuilder sb = new StringBuilder();
        traverse(sb, "", "", this.root);
        os.print(sb.toString());
    }

    public void traverse(StringBuilder sb, String padding, String pointer, Node node){
        if (node != null) {
            sb.append(padding);
            sb.append(pointer);
            sb.append(node.value);
            sb.append(", ");
            sb.append(node.getRep());
            sb.append("\n");

            StringBuilder paddingBuilder = new StringBuilder(padding);
            paddingBuilder.append("│  ");

            String paddingForBoth = paddingBuilder.toString();
            String pointerForRight = "└──";
            String pointerForLeft = (node.right != null) ? "├──" : "└──";

            traverse(sb, paddingForBoth, pointerForLeft, node.left);
            traverse(sb, paddingForBoth, pointerForRight, node.right);
        }
    }

}