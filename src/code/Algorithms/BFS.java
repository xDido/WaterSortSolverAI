package code.Algorithms;

import code.Algorithms.Node.Node;
import code.Tools.Bottle;
import code.WaterSortSearch;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class BFS {
    private Node node;
    private WaterSortSearch waterSortSearch;
    private int nodeCount;
    public BFS(ArrayList<Bottle> intialState, WaterSortSearch waterSortSearch) {
        this.node = new Node(intialState, null, null, 0, 0);
        this.waterSortSearch = waterSortSearch;
    }



    public void search() {
        ArrayDeque<Node> children = new ArrayDeque<>();
        children.addLast(node);  // Add the initial node
        Set<ArrayList<Bottle>> visitedStates = new HashSet<>();  // To track visited states
        nodeCount++;
        while (!children.isEmpty()) {
            System.out.println(children.size() + " nodes in the queue");
            Node currentNode = children.poll();  // Dequeue a node
            ArrayList<Bottle> currentNodeState = currentNode.getState();

            // Check if the current state has been visited
            if (visitedStates.contains(currentNodeState)) {
                continue;  // Skip this state if it has already been visited
            }

            // Mark the current state as visited
            visitedStates.add(currentNodeState);

            // Check if goal is reached
            if (waterSortSearch.isGoal(currentNodeState)) {
                System.out.println("************** GOAL STATE REACHED ********************");
                // Print the solution or process it as needed
                return;
                }


                for (int i = 0; i < currentNodeState.size(); i++) {
                    for (int j = 0; j < currentNodeState.size(); j++) {
                        if (i != j) {
                            Bottle sourceBottle = currentNodeState.get(i);
                            Bottle destinationBottle = currentNodeState.get(j);
                            if (waterSortSearch.isPourable(sourceBottle, destinationBottle)) {
                                ArrayList<Bottle> nextState = deepCopyState(currentNodeState);
                                waterSortSearch.Pour(nextState.get(i), nextState.get(j));
                                Node childNode = new Node(nextState, currentNode, "Pour", currentNode.getDepth() + 1, 0);
                                children.addLast(childNode);
                            }
                        }
                    }
                }
            }
     }

    private ArrayList<Bottle> deepCopyState(ArrayList<Bottle> state) {
        ArrayList<Bottle> newState = new ArrayList<>();
        for (Bottle bottle : state) {
            newState.add(new Bottle(bottle.getCapacity()));
        }
        return newState;
    }

}



