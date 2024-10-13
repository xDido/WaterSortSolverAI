package code.Algorithms.Node;

import code.Tools.Bottle;

import java.util.ArrayList;

public class Node {
    // Attributes
    private ArrayList<Bottle> state;
    private Node parent;
    private String operator;
    private int depth;
    private int pathCost;


    public Node(ArrayList<Bottle> state, Node parent, String operator, int depth, int pathCost) {
        this.state = state;
        this.parent = parent;
        this.operator = operator;
        this.depth = depth;
        this.pathCost = pathCost;
    }


    public ArrayList<Bottle> getState() {
        return state;
    }

    public Node getParent() {
        return parent;
    }

    public String getOperator() {
        return operator;
    }

    public int getDepth() {
        return depth;
    }

    public int getPathCost() {
        return pathCost;
    }

    // Setters (if needed)
    public void setState(ArrayList<Bottle> state) {
        this.state = state;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void setPathCost(int pathCost) {
        this.pathCost = pathCost;
    }

    // To represent the Node information as a string (useful for debugging)
    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", parent=" + (parent != null ? parent.getState() : "null") +
                ", operator='" + operator + '\'' +
                ", depth=" + depth +
                ", pathCost=" + pathCost +
                '}';
    }
}
