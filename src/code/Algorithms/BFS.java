package code.Algorithms;

import code.Algorithms.Node.Node;
import code.Tools.Bottle;
import code.WaterSortSearch;

import java.util.ArrayDeque;
import java.util.ArrayList;

import static code.Tools.Bottle.printBottles;

public class BFS {
    private Node node;
    private WaterSortSearch waterSortSearch;

    public BFS(ArrayList<Bottle> intialState, WaterSortSearch waterSortSearch) {
        this.node = new Node(intialState, null, null, 0, 0);
        this.waterSortSearch = waterSortSearch;
    }

    public void search() {
        ArrayDeque<Node> children = new ArrayDeque<>();
        children.addLast(node);  // Add the initial node

        while (!children.isEmpty()) {
            Node currentNode = children.poll();  // Dequeue a node
            ArrayList<Bottle> currentNodeState = currentNode.getState();
            int nextDepth=0;
            if (waterSortSearch.isGoal(currentNodeState)) {
                // Goal found, print the solution
                System.out.println("************** BFS Result ********************");
                for (Bottle bottle : currentNodeState) {
                    System.out.println(bottle.getLayers());
                }
                System.out.println("*************************************************");
                System.out.println("Goal Found");
                System.out.println("Depth: " + currentNode.getDepth());
                break;
            } else {

                for (int i = 0; i < currentNodeState.size(); i++) {
                    for (int j = 0; j < currentNodeState.size(); j++) {
                        if (i != j) {
                            Bottle sourceBottle = currentNodeState.get(i);
                            Bottle destinationBottle = currentNodeState.get(j);
                            System.out.println("currentNode.getDepth(): " + (currentNode.getDepth()));
                            System.out.println("currentNode.getDepth() + 1: " + (currentNode.getDepth() + 1));

                            if (waterSortSearch.isPourable(sourceBottle, destinationBottle)) {
                                System.out.println("************** BFS Pouring ********************");
                                System.out.println("Pouring from " + i + " to " + j);
                                waterSortSearch.Pour(sourceBottle, destinationBottle);
                                ArrayList<Bottle> nextState = currentNodeState;
                                System.out.println("************** QUEUE ********************  Queue Size: " + children.size());
                                for (Node x : children) {
                                    System.out.println("BFSPrinting Queue");
                                    printBottles(x.getState());
                                }
                                System.out.println("*************************************************");
                                printBottles(nextState);
                                System.out.println("currentNode.getDepth(): " + (currentNode.getDepth()));
                                System.out.println("currentNode.getDepth() + 1: " + (currentNode.getDepth() + 1));
                                System.out.println("nextDepth: " + nextDepth);
                                Node childNode = new Node(nextState, currentNode, "Pour", nextDepth, 0);
                                System.out.println(childNode.getDepth() +" childNode.getDepth()");
                                children.addLast(childNode);

                            }
                        }
                        System.out.println("************** DONE WITH J LOOP********************"); }
                    nextDepth = currentNode.getDepth() + 1;
                    System.out.println("************** DONE WITH J LOOP********************"); }
            }
        }
    }




}
