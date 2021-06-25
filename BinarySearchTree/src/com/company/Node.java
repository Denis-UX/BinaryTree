package com.company;

public class Node{
    public int rep;
    public char value;
    public Node left;
    public Node right;
    public int numberOfNodes =0;
    public int distinction = 0;

    public Node(char value) {
        setRep(1);
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

}

