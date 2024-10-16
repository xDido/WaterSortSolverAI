package code;

import java.util.ArrayList;
import java.util.Objects;

import static code.GenericSearch.initialStateHandler;

public class Node {

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

    public boolean isGoal() {
        for (Bottle bottle : state) {
            if (!bottle.isHomogeneous()) {
                return false;
            }
        }
        return true;
    }

    public boolean isPourable(Bottle source, Bottle destination) {
        return (!source.isEmpty() &&
                !destination.isFull() &&
                (destination.isEmpty() || source.getTopLayer().equals(destination.getTopLayer())));
    }


    public int Pour(Bottle source, Bottle destination) {
        int pourCount = 0;

        while (isPourable(source, destination)) {
            destination.addLayer(source.removeLayer());
            pourCount++;
        }

        return pourCount;
    }

    private static ArrayList<Bottle> deepCopyState(ArrayList<Bottle> state) {
        ArrayList<Bottle> newState = new ArrayList<>();
        for (Bottle bottle : state) {
            newState.add(bottle.deepCopy());
        }
        return newState;
    }

    public ArrayList<Node> expandChildren() {
        ArrayList<Node> children = new ArrayList<>();
        ArrayList<Bottle> nodeState = this.state;
        for (int i = 0; i < nodeState.size(); i++) {
            for (int j = 0; j < nodeState.size(); j++) {
                if (i != j) {
                    Bottle sourceBottle = nodeState.get(i);
                    Bottle destinationBottle = nodeState.get(j);
                    if (isPourable(sourceBottle, destinationBottle)) {
                        ArrayList<Bottle> newState = deepCopyState(nodeState);
                        int pouredLayers = Pour(newState.get(i), newState.get(j));
                        if (pouredLayers > 0) {
                            String operator = "pour_" + i + "_" + j;
                            int newNodePathCost = this.pathCost + pouredLayers;
                            Node newNode = new Node(newState, this, operator, this.depth + 1,
                                    newNodePathCost);
                            children.add(newNode);
                        }
                    }
                }
            }
        }
        return children;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Node other = (Node) obj;
        return Objects.equals(this.state, other.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return "Node{" +
                "state=" + state +
                ", operator='" + operator + '\'' +
                ", depth=" + depth +
                ", pathCost=" + pathCost +
                '}';
    }

}
